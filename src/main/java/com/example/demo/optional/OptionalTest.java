package com.example.demo.optional;

import java.util.Optional;

public class OptionalTest {
    public static void main(String args[]){

        OptionalTest java8Tester = new OptionalTest();
        Integer value1 = null;
        Integer value2 = new Integer(10);

        // optional.ofNullable - 允许传递为 null 参数
        Optional<Integer> a = Optional.ofNullable(value1);

        // optional.of - 如果传递的参数是 null，抛出异常 NullPointerException
        Optional<Integer> b = Optional.of(value2);
        System.out.println(java8Tester.sum(a,b));
    }

    public Integer sum(Optional<Integer> a, Optional<Integer> b){

        // optional.isPresent - 判断值是否存在

        System.out.println("第一个参数值存在: " + a.isPresent());
        System.out.println("第二个参数值存在: " + b.isPresent());

        // optional.orElse - 如果值存在，返回它，否则返回默认值
        Integer value1 = a.orElse(new Integer(0));

        //optional.get - 获取值，值需要存在
        Integer value2 = b.get();
        return value1 + value2;
    }
}
