package com.example.demo.design.create.abstactFactory;

import com.example.demo.design.create.abstactFactory.color.Color;
import com.example.demo.design.create.abstactFactory.shape.Shape;

/**
 * @program: demo
 * @description:
 * @author: chenyixin
 * @create: 2019-06-21 00:25
 **/
public abstract class AbstractFactory {
    public abstract Color getColor(String color);
    public abstract Shape getShape(String shape) ;
}