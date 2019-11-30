package com.example.demo.reactor.reactor_demo;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

/**
 * @program: demo
 * @description:
 * @author: chenyixin
 * @create: 2019-11-23 17:10
 **/
public class DemoSubscriber implements Subscriber<Integer> {
    @Override
    public void onSubscribe(Subscription s) {
        System.out.println("onSubscribe");
        s.request(1);   // 2
    }

    @Override
    public void onNext(Integer integer) {
        System.out.println(Thread.currentThread().getName()+"  onNext:" + integer);
    }

    @Override
    public void onError(Throwable t) {

    }

    @Override
    public void onComplete() {
        System.out.println("onComplete");
    }
}
