package com.example.demo.rpc.server;


import com.example.demo.rpc.common.IHelloService;

/**
 * @author Dongguabai
 * @date 2018/11/1 15:51
 */
public class HelloServiceImpl implements IHelloService {

    @Override
    public String sayHello(String name) {
        return "你好，" + name;
    }
}