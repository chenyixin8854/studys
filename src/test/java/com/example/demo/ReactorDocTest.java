package com.example.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import reactor.core.publisher.Flux;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @program: demo
 * @description:
 * @author: chenyixin
 * @create: 2019-11-18 18:09
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class ReactorDocTest {

    /**
     * 这是一种 同步地， 逐个地 产生值的方法，意味着 sink 是一个 SynchronousSink 而且其 next()
     * 方法在每次回调的时候最多只能被调用一次。你也可以调用 error(Throwable) 或者 complete()，不过是可选的。
     */
    @Test
    public void test1() {
//        Flux<String> flux = Flux.generate(
//                () -> 0,
//                (state, sink) -> {
//                    sink.next("3 x " + state + " = " + 3*state);
//                    if (state == 10) sink.complete();
//                    return state + 1;
//                });


        Flux<String> flux = Flux.generate(
                AtomicLong::new,
                (state, sink) -> {
                    long i = state.getAndIncrement();
                    sink.next("3 x " + i + " = " + 3 * i);
                    if (i == 10) sink.complete();
                    return state;
                }, (state) -> System.out.println("state: " + state));

        flux.subscribe(System.out::println);
    }

    /**
     * 作为一个更高级的创建 Flux 的方式， create 方法的生成方式既可以是同步，
     * 也可以是异步的，并且还可以每次发出多个元素。
     */
//    @Test
//    public void test2() {
//        Flux<String> bridge = Flux.create(sink -> {
//            //所有这些都是在 myEventProcessor 执行时异步执行的。
//            myEventProcessor.register(
//                    new MyEventListener<String>() {
//
//                        //每一个 chunk 的数据转化为 Flux 中的一个元素
//                        public void onDataChunk(List<String> chunk) {
//                            for (String s : chunk) {
//                                sink.next(s);
//                            }
//                        }
//
//                        //processComplete 事件转换为 onComplete。
//                        public void processComplete() {
//                            sink.complete();
//                        }
//                    });
//        });
//    }

    /**
     * create 的一个变体是 push，适合生成事件流。与 create`类似，`push 也可以是异步地， 并且能够使用以上各种溢出策略（overflow strategies）
     * 管理背压。每次只有一个生成线程可以调用 next，complete 或 error。
     * <p>
     * IGNORE： 完全忽略下游背压请求，这可能会在下游队列积满的时候导致 IllegalStateException。
     * <p>
     * ERROR： 当下游跟不上节奏的时候发出一个 IllegalStateException 的错误信号。
     * <p>
     * DROP：当下游没有准备好接收新的元素的时候抛弃这个元素。
     * <p>
     * LATEST：让下游只得到上游最新的元素。
     * <p>
     * BUFFER：（默认的）缓存所有下游没有来得及处理的元素（这个不限大小的缓存可能导致 OutOfMemoryError）。
     */
//    @Test
//    public void test3() {
//        Flux<String> bridge = Flux.push(sink -> {
//            myEventProcessor.register(
//                    new SingleThreadEventListener<String>() {
//
//                        public void onDataChunk(List<String> chunk) {
//                            for (String s : chunk) {
//                                sink.next(s);
//                            }
//                        }
//
//                        public void processComplete() {
//                            sink.complete();
//                        }
//
//                        public void processError(Throwable e) {
//                            sink.error(e);
//                        }
//                    });
//        });
//    }

    /**
     * 不像 push，create 可以用于 push 或 pull 模式，因此适合桥接监听器的 的 API，因为事件消息会随时异步地到来。
     * 回调方法 onRequest 可以被注册到 FluxSink 以便跟踪请求。这个回调可以被用于从源头请求更多数据，或者通过在
     * 下游请求到来 的时候传递数据给 sink 以实现背压管理。这是一种推送/拉取混合的模式， 因为下游可以从上游拉取
     * 已经就绪的数据，上游也可以在数据就绪的时候将其推送到下游。
     */
//    @Test
//    public void test4() {
//        Flux<String> bridge = Flux.create(sink -> {
//            myMessageProcessor.register(
//                    new MyMessageListener<String>() {
//
//                        public void onMessage(List<String> messages) {
//                            for (String s : messages) {
//                                sink.next(s);
//                            }
//                        }
//                    });
//            sink.onRequest(n -> {
//                List<String> messages = myMessageProcessor.request(n);
//                for (String s : message) {
//                    sink.next(s);
//                }
//            });
//        });


//    }


    /***
     * onDispose 和 onCancel 这两个回调用于在被取消和终止后进行清理工作。 onDispose 可用于在 Flux 完成，
     * 有错误出现或被取消的时候执行清理。 onCancel 只用于针对“取消”信号执行相关操作，会先于 onDispose 执行。
     */
//    @Test
//    public void test4(){
//        Flux<String> bridge = Flux.create(sink -> {
//            sink.onRequest(n -> channel.poll(n))
//                    .onCancel(() -> channel.cancel())
//                    .onDispose(() -> channel.close());
//        });
//    }

    @Test
    public void test5(){
        Flux<String> alphabet = Flux.just(-1, 30, 13, 9, 20)
                .handle((i, sink) -> {
                    String letter = "a"+i;
                    if (letter != null)
                        sink.next(letter);
                });

        alphabet.subscribe(System.out::println);
    }



    interface MyEventListener<T> {
        void onDataChunk(List<T> chunk);

        void processComplete();
    }

}
