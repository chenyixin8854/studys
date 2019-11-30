package com.example.demo.rabbitmq;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * @program: demo
 * @description:
 * @author: chenyixin
 * @create: 2019-09-23 11:04
 **/
@Data
@AllArgsConstructor
public class Order implements Serializable {
    private String id;
    private String name;
}
