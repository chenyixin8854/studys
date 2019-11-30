package com.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * 下面我们实现一个这样两个Endpoint：
 * * POST方法的/events，“源源不断”地收集数据，并存入数据库；
 * * GET方法的/events，“源源不断”将数据库中的记录发出来。
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "event") // 1
public class MyEvent {
    @Id
    private Long id;    // 2
    private String message;
}
