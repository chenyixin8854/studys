package com.example.demo.design.create.factory;

/**
 * @program: demo
 * @description:
 * @author: chenyixin
 * @create: 2019-06-21 00:12
 **/
public class Square implements Shape {

    @Override
    public void draw() {
        System.out.println("Inside Square::draw() method.");
    }
}
