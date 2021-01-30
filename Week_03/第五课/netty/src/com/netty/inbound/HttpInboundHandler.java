package com.netty.inbound;

import com.netty.filter.HeaderHttpRequestFilter;
import com.netty.filter.HttpRequestFilter;
import com.netty.outbound.HttpOutboundHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.FullHttpRequest;

import java.util.List;

public class HttpInboundHandler extends ChannelInboundHandlerAdapter {

    private List<String> proxyServers;
    private HttpOutboundHandler httpOutboundHandler;

    private HttpRequestFilter httpRequestFilter = new HeaderHttpRequestFilter();

    public HttpInboundHandler(List<String> proxyServers){
        this.proxyServers = proxyServers;
        this.httpOutboundHandler = new HttpOutboundHandler(proxyServers);
    }

    /**
     * 接收request请求，调用出站方法
     * @param ctx
     * @param msg
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg){
        try{

            FullHttpRequest fullHttpRequest = (FullHttpRequest) msg;
            httpOutboundHandler.handler(fullHttpRequest,ctx,httpRequestFilter);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
