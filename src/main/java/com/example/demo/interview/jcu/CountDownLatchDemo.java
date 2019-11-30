package com.example.demo.interview.jcu;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @program: demo
 * @description:
 * @author: chenyixin
 * @create: 2019-10-30 12:58
 **/
public class CountDownLatchDemo {
    static final int THREAD_COUNT = 10;
    static final CountDownLatch end = new CountDownLatch(THREAD_COUNT);

    public static void main(String[] args) throws InterruptedException {
        Runnable demo = new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("检查完成");
                end.countDown();
            }
        };

        //线程池内有5个线程方便看效果
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        for (int i = 0; i < THREAD_COUNT; i++) {
            executorService.submit(demo);
        }

        end.await();
        System.out.println("一切就绪");
        executorService.shutdown();
    }

}
