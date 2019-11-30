package com.example.demo.design.create.abstactFactory;

import com.example.demo.design.create.abstactFactory.color.Blue;
import com.example.demo.design.create.abstactFactory.color.Color;
import com.example.demo.design.create.abstactFactory.color.Green;
import com.example.demo.design.create.abstactFactory.color.Red;
import com.example.demo.design.create.abstactFactory.shape.Shape;

/**
 * @program: demo
 * @description:
 * @author: chenyixin
 * @create: 2019-06-21 00:26
 **/
public class ColorFactory extends AbstractFactory {

    @Override
    public Shape getShape(String shapeType){
        return null;
    }

    @Override
    public Color getColor(String color) {
        if(color == null){
            return null;
        }
        if(color.equalsIgnoreCase("RED")){
            return new Red();
        } else if(color.equalsIgnoreCase("GREEN")){
            return new Green();
        } else if(color.equalsIgnoreCase("BLUE")){
            return new Blue();
        }
        return null;
    }
}
