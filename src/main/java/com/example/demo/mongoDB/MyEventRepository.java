package com.example.demo.mongoDB;

import com.example.demo.entity.MyEvent;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.mongodb.repository.Tailable;
import reactor.core.publisher.Flux;

/**
 * @program: demo
 * @description:
 * @author: chenyixin
 * @create: 2019-11-21 13:14
 **/
public interface MyEventRepository extends ReactiveMongoRepository<MyEvent, Long> { // 1

    @Tailable// 被注解的方法将发送无限流，需要注解在返回值为Flux这样的多个元素的Publisher的方法上；@Tailable起作用的前提是至少有一条记录
    Flux<MyEvent> findBy(); // 2
}
