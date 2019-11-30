package com.example.demo.reactor.partten;

import com.example.demo.reactor.partten.event.EventType;
import com.example.demo.reactor.partten.handler.AcceptEventHandler;

public class Server {
    Selector selector = new Selector();
    Dispatcher eventLooper = new Dispatcher(selector);
    Acceptor acceptor;

    Server(int port) {
        acceptor = new Acceptor(selector, port);
    }

    public void start() {
        eventLooper.registEventHandler(EventType.ACCEPT, new AcceptEventHandler(selector));
        new Thread(acceptor, "Acceptor-" + acceptor.getPort()).start();
        eventLooper.handleEvents();
    }
}
