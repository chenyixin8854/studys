package com.example.demo.design.behavior.strategy;

/**
 * @program: demo
 * @description:
 * @author: chenyixin
 * @create: 2019-06-21 09:46
 **/
public class Context {
    private Strategy strategy;

    public Context(Strategy strategy){
        this.strategy = strategy;
    }

    public int executeStrategy(int num1, int num2){
        return strategy.doOperation(num1, num2);
    }
}