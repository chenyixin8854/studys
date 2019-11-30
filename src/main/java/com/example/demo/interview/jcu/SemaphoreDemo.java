package com.example.demo.interview.jcu;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * Semaphore，也是控制线程的一种手段，可以控制并发线程的数量，某些时候我们线程数过多，在访问有限的资源时候，可以使用Semaphore来控制线程的数量。
 */
public class SemaphoreDemo implements Runnable {
    Semaphore mSemaphore = new Semaphore(5);

    @Override
    public void run() {
        try {
            mSemaphore.acquire();
            Thread.sleep(2000);
            System.out.println(Thread.currentThread().getId() + " done!");
            mSemaphore.release();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(20);
        SemaphoreDemo demo = new SemaphoreDemo();
        for (int i = 0; i < 20; i++) {
            executorService.submit(demo);
        }
        executorService.shutdown();
        ;
    }
}