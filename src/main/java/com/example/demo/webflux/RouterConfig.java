package com.example.demo.webflux;

import com.example.demo.entity.MyEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.CollectionOptions;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;

/**
 * @program: demo
 * @description:
 * @author: chenyixin
 * @create: 2019-11-21 08:39
 **/
@Configuration
public class RouterConfig {
    @Autowired
    TestHandler testHandler;

    @Bean
    public RouterFunction<ServerResponse> getTime(){
        return RouterFunctions.route(GET("/time"),req->testHandler.sendTimePerSec(req))
                .andRoute(GET("/time1"),testHandler.timeFunction);
    }

    @Bean   // Spring Boot应用程序在启动后，会遍历CommandLineRunner接口的实例并运行它们的run方法。
    public CommandLineRunner initData(MongoOperations mongo) {  // 2
        return (String... args) -> {    // 3
            mongo.dropCollection(MyEvent.class);    //如果有，先删除collection，生产环境慎用这种操作；

            //原因定义的CollectionOptions.empty().size(200).capped()中，size指的是以字节为单位的大小，并且会向上取到256的整倍数，所以我们刚才定义的是256byte大小的collection，所以最多容纳两条记录。
            //maxDocuments限制了记录条数，size限制容量且是必须定义的，因为MongoDB不像关系型数据库有严格的列和字段大小定义，鬼知道会存多大的数据进来，所以容量限制是必要的。
            mongo.createCollection(MyEvent.class, CollectionOptions.empty().maxDocuments(10).size(20000).capped()); // 创建一个记录个数为10的capped的collection，容量满了之后，新增的记录会覆盖最旧的。
        };
    }
}
