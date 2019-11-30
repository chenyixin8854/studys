package com.example.demo.webflux;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

/**
 * @program: demo
 * @description:
 * @author: chenyixin
 * @create: 2019-11-21 08:53
 **/
@RestController
public class TestController {

    @GetMapping("/hello1")
    public Mono<String> hello() {   // 【改】返回类型为Mono<String>
        return Mono.just("Welcome to reactive world ~");     // 【改】使用Mono.just生成响应式数据
    }
}
