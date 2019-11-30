package com.example.demo.webflux;

import com.example.demo.entity.MyEvent;
import com.example.demo.mongoDB.MyEventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @program: demo
 * @description:
 * @author: chenyixin
 * @create: 2019-11-21 13:15
 **/
@RestController
@RequestMapping("/events")
public class MyEventController {
    @Autowired
    private MyEventRepository myEventRepository;

    @PostMapping(path = "", consumes = MediaType.APPLICATION_STREAM_JSON_VALUE) // 1
    public Mono<Void> loadEvents(@RequestBody Flux<MyEvent> events) {
        return this.myEventRepository.insert(events).then();    // 2使用then方法表示“忽略数据元素，只返回一个完成信号”。
    }

    @GetMapping(path = "", produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
    public Flux<MyEvent> getEvents() {  // 2
        return this.myEventRepository.findBy();
    }
}
