package com.example.demo.function;

import lombok.extern.slf4j.Slf4j;

import java.util.function.BiFunction;

/**
 * @program: demo
 * @description:
 * @author: chenyixin
 * @create: 2019-11-18 19:34
 **/
@Slf4j
public class BiFunctionDemo {

    public static void main(String[] args) {
        compute3(2, 3, (v1, v2) -> v1 + v2);//5
        compute3(2, 3, (v1, v2) -> v1 - v2);//-1
        compute3(2, 3, (v1, v2) -> v1 * v2);//6
    }


    public static int compute3(int a, int b, BiFunction<Integer, Integer, Integer> biFunction) {
        return biFunction.apply(a, b);
    }
}
