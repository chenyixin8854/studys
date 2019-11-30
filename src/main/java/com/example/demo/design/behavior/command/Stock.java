package com.example.demo.design.behavior.command;

/**
 * @program: demo
 * @description:
 * @author: chenyixin
 * @create: 2019-06-21 08:54
 **/
public class Stock {

    private String name = "ABC";
    private int quantity = 10;

    public void buy(){
        System.out.println("Stock [ Name: "+name+", Quantity: " + quantity +" ] bought");
    }
    public void sell(){
        System.out.println("Stock [ Name: "+name+", Quantity: " + quantity +" ] sold");
    }
}
