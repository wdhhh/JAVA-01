package com.netty.inbound;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;

import java.util.List;

public class HttpInboundInitializer extends ChannelInitializer<SocketChannel> {
    private List<String> proxyServers;

    public HttpInboundInitializer(List<String> proxyServers){
        this.proxyServers = proxyServers;
    }


    /**
     * 给channel绑定pipeline、handler，把url传给handler
     * @param socketChannel
     * @throws Exception
     */
    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        ChannelPipeline pipeline = socketChannel.pipeline();
        pipeline.addLast(new HttpServerCodec());
        pipeline.addLast(new HttpObjectAggregator(1024 *1024));
        pipeline.addLast(new HttpInboundHandler(this.proxyServers));
    }
}
