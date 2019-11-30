package com.example.demo.design.combine.facade;

/**
 * @program: demo
 * @description:
 * @author: chenyixin
 * @create: 2019-06-21 01:15
 **/
public class FacadePatternDemo {
    public static void main(String[] args) {
        ShapeMaker shapeMaker = new ShapeMaker();

        shapeMaker.drawCircle();
        shapeMaker.drawRectangle();
        shapeMaker.drawSquare();
    }
}
