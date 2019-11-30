package com.example.demo.function;

import lombok.extern.slf4j.Slf4j;

import java.util.function.Function;

/**
 * @program: demo
 * @description:
 * @author: chenyixin
 * @create: 2019-11-18 18:28
 **/
@Slf4j
public class FunctionDemo {
    public static void main(String[] args) {
        log.info(String.valueOf(compute(5,value -> value * value)));//25 计算平方
        log.info(String.valueOf(compute(5,value -> value + value)));//10 求和
        log.info(String.valueOf(compute(5,value -> value - 2)));//3

        log.info(String.valueOf(compute(2, value -> value * 3, value -> value * value)));
        log.info(String.valueOf(compute2(2, value -> value * 3, value -> value * value)));
    }

    public static int compute(int a, Function<Integer, Integer> function) {
        int result = function.apply(a);
        return result;
    }

    public static int compute(int a, Function<Integer, Integer> function1, Function<Integer, Integer> function2) {
        return function1.compose(function2).apply(a);
    }

    public static int compute2(int a, Function<Integer, Integer> function1, Function<Integer, Integer> function2) {
        return function1.andThen(function2).apply(a);
    }
}
