package com.example.demo.design.combine.facade;

/**
 * @program: demo
 * @description:
 * @author: chenyixin
 * @create: 2019-06-21 01:14
 **/
public class Rectangle implements Shape {

    @Override
    public void draw() {
        System.out.println("Rectangle::draw()");
    }
}