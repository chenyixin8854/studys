package com.example.demo.reactor.reactor_demo;

/**
 * @program: demo
 * @description:
 * @author: chenyixin
 * @create: 2019-11-23 17:11
 **/
public class Client {
    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getName());
        Flux.just(1,2,3,4,5,6,7,8).map(t->2*t).subscribe(new DemoSubscriber());
    }
}
