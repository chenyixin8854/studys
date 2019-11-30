package com.example.demo.rpc.server.no_register;


import com.example.demo.rpc.server.no_register.ProcessorHandler;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author Dongguabai
 * @date 2018/11/1 15:53
 */
public class RpcServer {
    //不建议通过Executors创建线程池，这里为了方便
    private static final ExecutorService executor = Executors.newCachedThreadPool();

    public void publisher(final Object service, int port) {
        //启动一个服务监听
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            while (true){
                //通过ServerSocket获取请求
                Socket socket = serverSocket.accept();
                executor.execute(new ProcessorHandler(socket,service));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}