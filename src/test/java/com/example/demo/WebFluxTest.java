package com.example.demo;

import com.example.demo.entity.MyEvent;
import com.example.demo.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

/**
 * @program: demo
 * @description:
 * @author: chenyixin
 * @create: 2019-11-21 11:19
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class WebFluxTest {
    @Test
    public void webClientTest1() throws InterruptedException {
        WebClient webClient = WebClient.create("http://localhost:8080");   // 1
        Mono<String> resp = webClient
                .get().uri("/hello") // 2
                .retrieve() // 异步地获取response信息
                .bodyToMono(String.class);  // 4
        resp.subscribe(System.out::println);    // 5
        TimeUnit.SECONDS.sleep(1);  // 6
    }


    @Test
    public void webClientTest2() throws InterruptedException {
        WebClient webClient = WebClient.builder().baseUrl("http://localhost:8080").build(); // 1
        webClient
                .get().uri("/user")
                .accept(MediaType.APPLICATION_STREAM_JSON) // 2
                .exchange() // 获取response信息，返回值为ClientResponse，retrive()可以看做是exchange()方法的“快捷版”
                .flatMapMany(response -> response.bodyToFlux(User.class))   // 4
                .doOnNext(System.out::println)  // 5
                .blockLast();   // 6
    }

    @Test
    public void webClientTest3() throws InterruptedException {
        WebClient webClient = WebClient.create("http://localhost:8080");
        webClient
                .get().uri("/time")
                .accept(MediaType.TEXT_EVENT_STREAM)    // 1
                .retrieve()
                .bodyToFlux(String.class)
                .log()  //代替doOnNext(System.out::println)来查看每个元素
                .take(10)   // 由于/times是一个无限流，这里取前10个，会导致流被取消
                .blockLast();
    }


    @Test
    public void webClientTest4() {
        Flux<MyEvent> eventFlux = Flux.interval(Duration.ofSeconds(1))
                .map(l -> new MyEvent(System.currentTimeMillis(), "message-" + l+12)).take(12); // 1
        WebClient webClient = WebClient.create("http://localhost:8080");
        webClient
                .post().uri("/events")
                .contentType(MediaType.APPLICATION_STREAM_JSON) // 2
                .body(eventFlux, MyEvent.class) // body方法设置请求体的数据。
                .retrieve()
                .bodyToMono(Void.class)
                .block();
    }
}
