package com.example.demo.webflux;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.HandlerFunction;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * @program: demo
 * @description:
 * @author: chenyixin
 * @create: 2019-11-20 15:44
 **/
@Component
public class TestHandler {

    public Mono<ServerResponse> sendTimePerSec(ServerRequest serverRequest){
        return ServerResponse.ok().contentType(MediaType.TEXT_EVENT_STREAM)
                .body(Flux.interval(Duration.ofSeconds(1)).map(l-> LocalDateTime.now().toString()),String.class);
    }

    // 返回包含时间字符串的ServerResponse
    HandlerFunction<ServerResponse> timeFunction =
            request -> ServerResponse.ok().contentType(MediaType.TEXT_PLAIN).body(
                    Mono.just("Now is " + new SimpleDateFormat("HH:mm:ss").format(new Date())), String.class);
}
