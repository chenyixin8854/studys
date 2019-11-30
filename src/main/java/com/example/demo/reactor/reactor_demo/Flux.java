package com.example.demo.reactor.reactor_demo;

import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;

import java.util.function.Function;

/**
 * @program: demo
 * @description:
 * @author: chenyixin
 * @create: 2019-11-23 16:53
 **/
public abstract class Flux<T> implements Publisher<T> {
    public abstract void subscribe(Subscriber<? super T> s);

    public static <T> Flux<T> just(T... data) {
        return new FluxArray<>(data);
    }

    public <V> Flux<V> map(Function<? super T, ? extends V> mapper) {   // 1
        return new FluxMap<>(this, mapper); // 2
    }
}
