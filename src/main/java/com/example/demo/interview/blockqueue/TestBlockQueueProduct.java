package com.example.demo.interview.blockqueue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * @program: demo
 * @description:
 * @author: chenyixin
 * @create: 2019-10-16 00:19
 **/
public class TestBlockQueueProduct {
    public static void main(String[] args) {
        TestBlockQueue myShare = new TestBlockQueue(new ArrayBlockingQueue<>(10));
        new Thread(() -> {
            System.out.println("生产线程启动");
            try {
                myShare.myProd();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "product").start();

        new Thread(() -> {
            System.out.println("消费线程启动");
            try {
                myShare.myConsumer();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }, "consumer").start();
        //休眠五秒
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        myShare.stop();
    }
}
