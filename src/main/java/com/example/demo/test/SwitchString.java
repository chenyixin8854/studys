package com.example.demo.test;

/**
 * @program: demo
 * @description:
 * @author: chenyixin
 * @create: 2019-06-26 21:40
 **/
public class SwitchString {
    public static void main(String[] args) {
        method(null);
    }

    public static void method(String param) {
        switch (param) {
            // 肯定不是进入这里
            case "sth":
                System.out.println("it's sth");
                break;
            case "null":
                System.out.println("it's null");
                break;
            default:
                System.out.println("default");
        }
    }
}