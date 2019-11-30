package com.example.demo.interview.jdk.spi;

public class ShutdownCommand implements Cmand {
    public void execute() {
        System.out.println("shutdown....");
    }
}