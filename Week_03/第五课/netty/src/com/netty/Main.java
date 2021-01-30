package com.netty;

import com.netty.inbound.HttpInboundServer;

import java.util.Arrays;
import java.util.List;

/**
 *  1.服务8801和8802 在 com.server下
 *  2.作业：整合上次作业的 httpclient/okhttp，在com.netty.outbound.HttpOutboundHandler#getUrl()
 *  3.作业：实现过滤器，在com.netty.filter
 */
public class Main {

    public static void main(String[] args) {
        String proxyPort = System.getProperty("proxyPort","8888");
        String proxyServerStr = System.getProperty("proxyServers","http://localhost:8801,http://localhost:8802");

        List<String> proxyServers = Arrays.asList(proxyServerStr.split(","));
        int port = Integer.parseInt(proxyPort);
        HttpInboundServer server = new HttpInboundServer(proxyServers,port);
        try{
            server.run();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
