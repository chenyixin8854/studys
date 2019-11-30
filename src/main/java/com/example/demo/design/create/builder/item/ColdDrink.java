package com.example.demo.design.create.builder.item;

import com.example.demo.design.create.builder.pack.Bottle;
import com.example.demo.design.create.builder.pack.Packing;

/**
 * @program: demo
 * @description:
 * @author: chenyixin
 * @create: 2019-06-21 00:39
 **/
public abstract class ColdDrink implements Item {

    @Override
    public Packing packing() {
        return new Bottle();
    }

    @Override
    public abstract float price();
}