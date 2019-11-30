package com.example.demo.reactor.reactor_demo;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

/**
 * @program: demo
 * @description:
 * @author: chenyixin
 * @create: 2019-11-23 16:55
 **/
public class FluxArray<T> extends Flux<T> {
    private T[] array;  // 1

    public FluxArray(T[] data) {
        this.array = data;
    }

    @Override
    public void subscribe(Subscriber<? super T> actual) {
        actual.onSubscribe(new ArraySubscription<>(actual, array)); // 2
    }

    static class ArraySubscription<T> implements Subscription { // 1
        final Subscriber<? super T> actual;
        final T[] array;    // 2可见在Subscription内也有一份数据；
        int index;
        boolean canceled;

        public ArraySubscription(Subscriber<? super T> actual, T[] array) {
            this.actual = actual;
            this.array = array;
        }

        @Override
        public void request(long n) {
            if (canceled) {
                return;
            }
            long length = array.length;
            for (int i = 0; i < n && index < length; i++) {
                actual.onNext(array[index++]);  // 3
            }
            if (index == length) {
                actual.onComplete();    // 4
            }
        }

        @Override
        public void cancel() {  // 5订阅者可以使用Subscription取消订阅
            this.canceled = true;
        }
    }
}