package com.example.demo.design.behavior.interpreter;

/**
 * @program: demo
 * @description:
 * @author: chenyixin
 * @create: 2019-06-21 09:15
 **/
public class TerminalExpression implements Expression {

    private String data;

    public TerminalExpression(String data){
        this.data = data;
    }

    @Override
    public boolean interpret(String context) {
        if(context.contains(data)){
            return true;
        }
        return false;
    }
}