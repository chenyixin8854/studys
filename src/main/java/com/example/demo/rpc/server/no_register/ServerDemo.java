package com.example.demo.rpc.server.no_register;

import com.example.demo.rpc.server.HelloServiceImpl;
import com.example.demo.rpc.server.no_register.RpcServer;

/**
 * @author Dongguabai
 * @date 2018/11/1 18:07
 */
public class ServerDemo {

    public static void main(String[] args) {
        RpcServer rpcServer = new RpcServer();
        rpcServer.publisher(new HelloServiceImpl(),12345);
    }
}