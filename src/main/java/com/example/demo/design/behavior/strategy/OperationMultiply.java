package com.example.demo.design.behavior.strategy;

/**
 * @program: demo
 * @description:
 * @author: chenyixin
 * @create: 2019-06-21 09:44
 **/
public class OperationMultiply implements Strategy{
    @Override
    public int doOperation(int num1, int num2) {
        return num1 * num2;
    }
}