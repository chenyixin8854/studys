package com.example.demo.design.behavior.command;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: demo
 * @description:
 * @author: chenyixin
 * @create: 2019-06-21 08:57
 **/
public class Broker {
    private List<Order> orderList = new ArrayList<Order>();

    public void takeOrder(Order order){
        orderList.add(order);
    }

    public void placeOrders(){
        for (Order order : orderList) {
            order.execute();
        }
        orderList.clear();
    }
}
