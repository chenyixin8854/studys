package com.example.demo;

import org.hibernate.validator.internal.constraintvalidators.bv.time.futureorpresent.FutureOrPresentValidatorForYear;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.reactivestreams.Subscription;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import reactor.core.publisher.BaseSubscriber;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.publisher.SignalType;
import reactor.core.scheduler.Schedulers;
import reactor.test.StepVerifier;

import java.time.Duration;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.LongAdder;

/**
 * @program: demo
 * @description:
 * @author: chenyixin
 * @create: 2019-11-18 12:47
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class ReactorTest {
    private Flux<Integer> generateFluxFrom1To6() {
        return Flux.just(1, 2, 3, 4, 5, 6);
    }
    private Mono<Integer> generateMonoWithError() {
        return Mono.error(new Exception("some error"));
    }
    @Test
    public void testViaStepVerifier() {
        StepVerifier.create(generateFluxFrom1To6())
                .expectNext(1, 2, 3, 4, 5, 6)
                .expectComplete()
                .verify();

        StepVerifier.create(generateMonoWithError())
                .expectErrorMessage("some error")
                .verify();
    }

    @Test
    public void test1(){
        StepVerifier.create(Flux.range(1, 6)    // 1
                .map(i -> i * i))   // 2
                .expectNext(1, 4, 9, 16, 25, 36)    //3
                .expectComplete();  // verifyComplete()相当于expectComplete().verify()
    }

    @Test
    public void test2(){
        Flux.just("flux", "mono")
                .flatMap(s -> Flux.fromArray(s.split("\\s*")).doOnNext(o->System.out.println(Thread.currentThread().getName())).delayElements(Duration.ofMillis(100))) // 2
                .subscribe(s->{
                    System.out.println(Thread.currentThread().getName()+"============="+s);
                });
    }

    @Test
    public void test3(){
        StepVerifier.create(Flux.range(1, 6)
                .filter(i -> i % 2 == 1)    // 1
                .map(i -> i * i))
                .expectNext(1, 9, 25)   // 2
                .verifyComplete();
    }


    private Flux<String> getZipDescFlux() {
        String desc = "Zip two sources together, that is to say wait for all the sources to emit one element and combine these elements once into a Tuple2.";
        return Flux.fromArray(desc.split("\\s+"));  // 1
    }

    @Test
    public void testSimpleOperators() throws InterruptedException {
        System.out.println(Thread.currentThread().getName()+"=============");
        CountDownLatch countDownLatch = new CountDownLatch(1);  // 2
        Flux.zip(
                getZipDescFlux(),
                Flux.interval(Duration.ofMillis(200)))  // 3
                .subscribe(t -> System.out.println(Thread.currentThread().getName()+"---------"+t.getT1()), null, countDownLatch::countDown);    // 4
        countDownLatch.await(10, TimeUnit.SECONDS);     // 5
    }

    //同步阻塞的方法
    private String getStringSync() {
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "Hello, Reactor!";
    }

    @Test
    public void testSyncToAsync() throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(1);
        Mono.fromCallable(() -> getStringSync())    // 1
                .subscribeOn(Schedulers.elastic())  // 2
                .subscribe(s->System.out.println(Thread.currentThread().getName()), null, countDownLatch::countDown);
        System.out.println(Thread.currentThread().getName()+"=============");
        countDownLatch.await(10, TimeUnit.SECONDS);
        System.out.println(Thread.currentThread().getName()+"=============");
    }

    @Test
    public void testError(){
        Flux.range(1, 6) .doOnNext(s->System.out.println(Thread.currentThread().getName()+"=="+s)).subscribe(System.out::println);
        Flux.range(1, 6)
                .doOnNext(s->System.out.println(Thread.currentThread().getName()+"=="+s))
                .map(i -> 10/(i-3))
                .onErrorReturn(0)   // 1
                .map(i -> i*i)
                .subscribe(System.out::println, System.err::println);
    }


    @Test
    public void testFinally(){
        LongAdder statsCancel = new LongAdder();    // 1

        Flux<String> flux =
                Flux.just("foo", "bar")
                        .doFinally(type -> {
                            if (type == SignalType.CANCEL)  // 2
                                statsCancel.increment();  // 3
                        })
                        .take(1);   // 4
    }

    @Test
    public void restRetry() throws InterruptedException {
        Flux.range(1, 6)
                .map(i -> 10 / (3 - i))
                .retry(1)
                .subscribe(System.out::println, System.err::println);
        Thread.sleep(100);  // 确保序列执行完
    }


    @Test
    public void testBackpressure() throws InterruptedException {
        Flux.range(1, 6)    // 1
                .doOnRequest(n -> System.out.println("Request " + n + " values..."))    // 2
                .subscribe(new BaseSubscriber<Integer>() {  // 3
                    @Override
                    protected void hookOnSubscribe(Subscription subscription) { // 4
                        System.out.println("Subscribed and make a request...");
                        request(2); // 5
                    }

                    @Override
                    protected void hookOnNext(Integer value) {  // 6
                        try {
                            TimeUnit.SECONDS.sleep(1);  // 7
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        System.out.println("Get value [" + value + "]");    // 8
                        request(1); // 9
                    }
                });
        Thread.sleep(10000);  // 确保序列执行完
    }

}
