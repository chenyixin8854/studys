package com.example.demo.design.create.factory;

/**
 * @program: demo
 * @description:
 * @author: chenyixin
 * @create: 2019-06-21 00:13
 **/
public class Circle implements Shape {

    @Override
    public void draw() {
        System.out.println("Inside Circle::draw() method.");
    }
}