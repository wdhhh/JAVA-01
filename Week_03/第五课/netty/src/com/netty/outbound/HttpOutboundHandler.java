package com.netty.outbound;

import com.netty.filter.HeaderHttpResponseFilter;
import com.netty.filter.HttpRequestFilter;
import com.netty.filter.HttpResponseFilter;
import com.netty.router.HttpEndpointRouter;
import com.netty.router.RandomHttpEndpointRouter;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.*;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.concurrent.FutureCallback;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.apache.http.impl.nio.client.HttpAsyncClients;
import org.apache.http.impl.nio.reactor.IOReactorConfig;
import org.apache.http.nio.client.HttpAsyncClient;
import org.apache.http.util.EntityUtils;

import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;

import static io.netty.handler.codec.http.HttpHeaderValues.KEEP_ALIVE;
import static io.netty.handler.codec.http.HttpResponseStatus.NO_CONTENT;
import static io.netty.handler.codec.http.HttpResponseStatus.OK;
import static org.apache.http.HttpHeaders.CONNECTION;

public class HttpOutboundHandler {
    private List<String> urls;
    private ExecutorService proxyService;
    private CloseableHttpAsyncClient httpClient;

    private HttpResponseFilter httpResponseFilter = new HeaderHttpResponseFilter();
    private HttpEndpointRouter httpEndpointRouter = new RandomHttpEndpointRouter();

    public HttpOutboundHandler(List<String> urls){
        // 1.真实的请求地址
        this.urls = urls.stream().map(this::formatUrl).collect(Collectors.toList());
        // 2. 初始化线程池
        // 获取cpu核心线程数，也就是计算资源
        int cores = Runtime.getRuntime().availableProcessors();
        long keepAliveTime = 1000;
        int queueSize = 2048;
        // 拒绝策略
        // rejection-policy:当pool已经达到max size的时候，如何处理新任务
        // CALLER_RUNS：不在新线程中执行任务，而是由调用者所在的线程来执行
        RejectedExecutionHandler handler = new ThreadPoolExecutor.CallerRunsPolicy();
        proxyService = new ThreadPoolExecutor(cores,
                cores,
                keepAliveTime,
                TimeUnit.MILLISECONDS,
                new ArrayBlockingQueue<>(queueSize),
                new ProxyThreadFactory("proxyService"),
                handler);

        //3.初始化httpclient
        IOReactorConfig ioReactorConfig = IOReactorConfig.custom()
                .setConnectTimeout(1000)
                .setSoTimeout(1000)
                .setIoThreadCount(cores)
                .setRcvBufSize(32 * 1024)
                .build();

        httpClient = HttpAsyncClients.custom().setMaxConnTotal(40)
                .setMaxConnPerRoute(8)
                .setDefaultIOReactorConfig(ioReactorConfig)
                .setKeepAliveStrategy(((httpResponse, httpContext) -> 6000))
                .build();
        httpClient.start();
    }

    private String formatUrl(String backend) {
        return backend.endsWith("/")?backend.substring(0,backend.length()-1):backend;
    }

    public void handler(final FullHttpRequest fullHttpRequest, final ChannelHandlerContext ctx, HttpRequestFilter httpRequestFilter){
        String url = httpEndpointRouter.route(urls);
        httpRequestFilter.filter(fullHttpRequest);
        proxyService.submit(()->getUrl(fullHttpRequest,ctx,url));
    }

    /**
     *  作业：整合httpClient
     * @param fullHttpRequest
     * @param ctx
     * @param url
     */
    public void getUrl(final FullHttpRequest fullHttpRequest,final ChannelHandlerContext ctx,final String url){
        HttpGet httpGet = new HttpGet(url);
        httpClient.execute(httpGet, new FutureCallback<HttpResponse>() {
            @Override
            public void completed(final HttpResponse httpResponse) {
                try{
                    handlerResponse(fullHttpRequest,ctx,httpResponse);
                }catch (Exception e){
                    e.printStackTrace();
                }

            }

            @Override
            public void failed(final Exception e) {
                httpGet.abort();
            }

            @Override
            public void cancelled() {
                httpGet.abort();
            }
        });
    }

    public void handlerResponse(final FullHttpRequest fullHttpRequest,final ChannelHandlerContext ctx,final HttpResponse httpResponse){
        FullHttpResponse response = null;
        try{
            byte[] bytes = EntityUtils.toByteArray(httpResponse.getEntity());
            response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, OK, Unpooled.wrappedBuffer(bytes));
            response.headers().set("Content_Type","application/json");
            response.headers().setInt("Content-Length",response.content().readableBytes());
            httpResponseFilter.filter(response);
        }catch (final Exception e){
            e.printStackTrace();
            System.out.println("处理出错："+e.getMessage());
            response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1,NO_CONTENT);
            exceptionCaught(ctx,e);
        }finally {
            if (null != response){
                if (!HttpUtil.isKeepAlive(fullHttpRequest)){
                    ctx.write(response).addListener(ChannelFutureListener.CLOSE);
                }else{
                    response.headers().set(CONNECTION,KEEP_ALIVE);
                    ctx.write(response);
                }
            }
            ctx.flush();
        }
    }

    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }
}
