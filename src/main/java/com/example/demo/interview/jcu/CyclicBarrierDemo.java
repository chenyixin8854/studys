package com.example.demo.interview.jcu;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @program: demo
 * @description: 循环栅栏，可以看做CountDownLatch的重复利用。当满足一定的条件时候，才开始执行某线程。
 * @author: chenyixin
 * @create: 2019-10-30 12:56
 **/
public class CyclicBarrierDemo {
    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(4, new Runnable() {
            @Override
            public void run() {
                System.out.println("一切就绪，准备出发");
            }
        });

        Runnable task = new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                    System.out.println(Thread.currentThread().getId() + "：就绪");
                    cyclicBarrier.await();
                    System.out.println(Thread.currentThread().getId() + "：前进");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }
        };

        ExecutorService executorService = Executors.newFixedThreadPool(4);
        for (int i = 0; i < 8; i++) {
            executorService.submit(task);
        }


        executorService.shutdown();

    }

}
