package com.example.demo.design.create.builder.item;

import com.example.demo.design.create.builder.pack.Packing;

/**
 * @program: demo
 * @description:
 * @author: chenyixin
 * @create: 2019-06-21 00:35
 **/
public interface Item {
    public String name();
    public Packing packing();
    public float price();
}