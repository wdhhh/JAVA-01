package com.netty;

import com.netty.inbound.HttpInboundServer;

import java.util.Arrays;
import java.util.List;

/**
 * 服务8801和8802 在 com.server下
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
