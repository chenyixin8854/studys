package com.example.demo.design.create.abstactFactory;

import com.example.demo.design.create.abstactFactory.color.Color;
import com.example.demo.design.create.abstactFactory.shape.Circle;
import com.example.demo.design.create.abstactFactory.shape.Rectangle;
import com.example.demo.design.create.abstactFactory.shape.Shape;
import com.example.demo.design.create.abstactFactory.shape.Square;

/**
 * @program: demo
 * @description:
 * @author: chenyixin
 * @create: 2019-06-21 00:25
 **/
public class ShapeFactory extends AbstractFactory {

    @Override
    public Shape getShape(String shapeType){
        if(shapeType == null){
            return null;
        }
        if(shapeType.equalsIgnoreCase("CIRCLE")){
            return new Circle();
        } else if(shapeType.equalsIgnoreCase("RECTANGLE")){
            return new Rectangle();
        } else if(shapeType.equalsIgnoreCase("SQUARE")){
            return new Square();
        }
        return null;
    }

    @Override
    public Color getColor(String color) {
        return null;
    }
}