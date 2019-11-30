package com.example.demo.design.combine.decorator;

/**
 * @program: demo
 * @description:
 * @author: chenyixin
 * @create: 2019-06-21 01:10
 **/
public abstract class ShapeDecorator implements Shape {
    protected Shape decoratedShape;

    public ShapeDecorator(Shape decoratedShape){
        this.decoratedShape = decoratedShape;
    }

    public void draw(){
        decoratedShape.draw();
    }
}