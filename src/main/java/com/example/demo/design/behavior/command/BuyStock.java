package com.example.demo.design.behavior.command;

/**
 * @program: demo
 * @description:
 * @author: chenyixin
 * @create: 2019-06-21 08:56
 **/
public class BuyStock implements Order {
    private Stock abcStock;

    public BuyStock(Stock abcStock){
        this.abcStock = abcStock;
    }

    public void execute() {
        abcStock.buy();
    }
}