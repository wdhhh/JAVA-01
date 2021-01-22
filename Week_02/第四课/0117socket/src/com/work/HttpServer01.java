package com.work;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *  8801端口的服务
 */
public class HttpServer01 {

    public static void main(String[] args) throws IOException {
        // 创建一个ServerSocket，绑定8801端口
        ServerSocket serverSocket = new ServerSocket(8801);
        while (true){
            try {
                // 当有客户请求时，通过accept方法拿到socket，进而可以进行处理
                Socket socket = serverSocket.accept();
                service(socket);
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }

    private static void service(Socket socket){
        try{
            //sleep 20 模拟业务操作
            Thread.sleep(20);
            //模拟输出HTTP报文头和hello
            PrintWriter printWriter = new PrintWriter(socket.getOutputStream(),true);
            printWriter.println("HTTP/1.1 200 OK");
            printWriter.println("Content-Type:text/html;charset=utf-8");
            String body = "hello,nio";
            printWriter.println("Content-Length:" + body.length());
            printWriter.println();
            printWriter.write(body);
            printWriter.close();
            //关闭socket
            socket.close();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
