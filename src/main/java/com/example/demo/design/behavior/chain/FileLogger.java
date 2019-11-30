package com.example.demo.design.behavior.chain;

/**
 * @program: demo
 * @description:
 * @author: chenyixin
 * @create: 2019-06-21 01:23
 **/
public class FileLogger extends AbstractLogger {

    public FileLogger(int level){
        this.level = level;
    }

    @Override
    protected void write(String message) {
        System.out.println("File::Logger: " + message);
    }
}