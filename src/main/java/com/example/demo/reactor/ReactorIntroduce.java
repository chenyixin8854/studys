package com.example.demo.reactor;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static javafx.scene.input.KeyCode.T;

/**
 * @program: demo
 * @description:
 * @author: chenyixin
 * @create: 2019-11-18 12:24
 **/
public class ReactorIntroduce {
    public static void main(String[] args) {
        Flux.just(1, 2, 3, 4, 5, 6);
        Mono.just(1);

        Integer[] array = new Integer[]{1,2,3,4,5,6};
        Flux.fromArray(array);
        List<Integer> list = Arrays.asList(array);
        Flux.fromIterable(list);
        Stream<Integer> stream = list.stream();
        Flux.fromStream(stream);

        // 只有完成信号的空数据流
        Flux.just();
        Flux.empty();
        Mono.empty();
        Mono.justOrEmpty(Optional.empty());
        // 只有错误信号的数据流
        Flux.error(new Exception("some error"));
        Mono.error(new Exception("some error"));


        Flux.just(1, 2, 3, 4, 5, 6).subscribe(System.out::print);
        System.out.println();
        Mono.just(1).subscribe(System.out::println);


//        // 订阅并触发数据流
//        Flux.just(1, 2, 3, 4, 5, 6).subscribe();
//        // 订阅并指定对正常数据元素如何处理
//        Flux.just(1, 2, 3, 4, 5, 6).subscribe(Consumer<? super T> consumer);
//        // 订阅并定义对正常数据元素和错误信号的处理
//        Flux.just(1, 2, 3, 4, 5, 6).subscribe(Consumer<? super T> consumer,Consumer<? super Throwable> errorConsumer);
//        // 订阅并定义对正常数据元素、错误信号和完成信号的处理
//        Flux.just(1, 2, 3, 4, 5, 6).subscribe(Consumer<? super T> consumer,Consumer<? super Throwable> errorConsumer,Runnable completeConsumer);
//        // 订阅并定义对正常数据元素、错误信号和完成信号的处理，以及订阅发生时的处理逻辑
//        Flux.just(1, 2, 3, 4, 5, 6).subscribe(Consumer<? super T> consumer,Consumer<? super Throwable> errorConsumer,Runnable completeConsumer,Consumer<? super Subscription> subscriptionConsumer);


        Flux.just(1, 2, 3, 4, 5, 6).subscribe(
                System.out::println,
                System.err::println,
                () -> System.out.println("Completed!"));


//        Reactor中提供了非常丰富的操作符，除了以上几个常见的，还有：
//
//        用于编程方式自定义生成数据流的create和generate等及其变体方法；
//        用于“无副作用的peek”场景的doOnNext、doOnError、doOncomplete、doOnSubscribe、doOnCancel等及其变体方法；
//        用于数据流转换的when、and/or、merge、concat、collect、count、repeat等及其变体方法；
//        用于过滤/拣选的take、first、last、sample、skip、limitRequest等及其变体方法；
//        用于错误处理的timeout、onErrorReturn、onErrorResume、doFinally、retryWhen等及其变体方法；
//        用于分批的window、buffer、group等及其变体方法；
//        用于线程调度的publishOn和subscribeOn方法。

        SampleSubscriber<Integer> ss = new SampleSubscriber<Integer>();
        Flux<Integer> ints = Flux.range(1, 4);
        ints.subscribe(i -> System.out.println(i),
                error -> System.err.println("Error " + error),
                () -> {System.out.println("Done");},
                s -> ss.request(10));
        ints.subscribe(ss);

    }

}
