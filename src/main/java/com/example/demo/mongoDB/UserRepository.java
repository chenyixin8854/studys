package com.example.demo.mongoDB;

import com.example.demo.entity.User;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

/**
 * @program: demo
 * @description:
 * @author: chenyixin
 * @create: 2019-11-21 10:12
 **/
public interface UserRepository extends ReactiveCrudRepository<User, String> {  // 1
    Mono<User> findByUsername(String username);     // 2
    Mono<Long> deleteByUsername(String username);
}