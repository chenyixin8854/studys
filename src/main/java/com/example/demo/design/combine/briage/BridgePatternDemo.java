package com.example.demo.design.combine.briage;

/**
 * @program: demo
 * @description:
 * @author: chenyixin
 * @create: 2019-06-21 01:01
 **/
public class BridgePatternDemo {
    public static void main(String[] args) {
        Shape redCircle = new Circle(100,100, 10, new RedCircle());
        Shape greenCircle = new Circle(100,100, 10, new GreenCircle());

        redCircle.draw();
        greenCircle.draw();
    }
}