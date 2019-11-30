package com.example.demo.reactor.reactor_demo;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.function.Function;

/**
 * @program: demo
 * @description:
 * @author: chenyixin
 * @create: 2019-11-23 22:26
 **/
public class FluxMap<T, R> extends Flux<R> {

    private final Flux<? extends T> source;
    private final Function<? super T, ? extends R> mapper;

    public FluxMap(Flux<? extends T> source, Function<? super T, ? extends R> mapper) {
        this.source = source;
        this.mapper = mapper;
    }

    @Override
    public void subscribe(Subscriber<? super R> actual) {
        source.subscribe(new MapSubscriber<>(actual, mapper));
    }


    static final class MapSubscription<T, R> implements Subscription {
        private final Subscriber<? super R> actual;
        private final Function<? super T, ? extends R> mapper;

        MapSubscription(Subscriber<? super R> actual, Function<? super T, ? extends R> mapper) {
            this.actual = actual;
            this.mapper = mapper;
        }

        @Override
        public void request(long n) {   // 1
            // TODO 收到请求，发出元素
        }

        @Override
        public void cancel() {
            // TODO 取消订阅
        }
    }

    static final class MapSubscriber<T, R> implements Subscriber<T>, Subscription {
        private final Subscriber<? super R> actual;
        private final Function<? super T, ? extends R> mapper;

        boolean done;

        Subscription subscriptionOfUpstream;

        MapSubscriber(Subscriber<? super R> actual, Function<? super T, ? extends R> mapper) {
            this.actual = actual;
            this.mapper = mapper;
        }

        @Override
        public void onSubscribe(Subscription s) {
            this.subscriptionOfUpstream = s;    // 1
            actual.onSubscribe(this);           // 2
            System.out.println("onsuscriber");
        }

        @Override
        public void onNext(T t) {
            if (done) {
                return;
            }
            actual.onNext(mapper.apply(t));     // 3
        }

        @Override
        public void onError(Throwable t) {
            if (done) {
                return;
            }
            done = true;
            actual.onError(t);                  // 4
        }

        @Override
        public void onComplete() {
            if (done) {
                return;
            }
            done = true;
            actual.onComplete();                // 5
        }

        @Override
        public void request(long n) {
            this.subscriptionOfUpstream.request(n);     // 6
        }

        @Override
        public void cancel() {
            this.subscriptionOfUpstream.cancel();       // 7
        }
    }
}