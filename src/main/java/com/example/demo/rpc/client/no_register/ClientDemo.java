package com.example.demo.rpc.client.no_register;

import com.example.demo.rpc.client.no_register.RpcClientProxy;
import com.example.demo.rpc.common.IHelloService;

/**
 * @author Dongguabai
 * @date 2018/11/1 18:10
 */
public class ClientDemo {

    public static void main(String[] args) {
        RpcClientProxy proxy = new RpcClientProxy();
        IHelloService helloService = proxy.clientProxy(IHelloService.class, "127.0.0.1", 12345);
        String name = helloService.sayHello("张三");
        System.out.println(name);
    }
}