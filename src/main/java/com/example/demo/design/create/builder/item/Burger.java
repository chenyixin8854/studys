package com.example.demo.design.create.builder.item;

import com.example.demo.design.create.builder.pack.Packing;
import com.example.demo.design.create.builder.pack.Wrapper;

/**
 * @program: demo
 * @description:
 * @author: chenyixin
 * @create: 2019-06-21 00:39
 **/
public abstract class Burger implements Item {

    @Override
    public Packing packing() {
        return new Wrapper();
    }

    @Override
    public abstract float price();
}
