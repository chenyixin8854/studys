package com.example.demo.rpc.client.no_register;


import com.example.demo.rpc.common.RpcRequest;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author Dongguabai
 * @date 2018/11/1 16:20
 */
public class RemoteInvocationHandler implements InvocationHandler{

    private String host;
    private int port;

    /**
     *发起客户端和服务端的远程调用。调用客户端的信息进行传输
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        RpcRequest rpcRequest = new RpcRequest();
        rpcRequest.setClassName(method.getDeclaringClass().getName());
        rpcRequest.setMethodName(method.getName());
        rpcRequest.setParameters(args);
        TcpTransport tcpTransport = new TcpTransport(host,port);
        return tcpTransport.send(rpcRequest);
    }

    public RemoteInvocationHandler(String host, int port) {
        this.host = host;
        this.port = port;
    }

}
