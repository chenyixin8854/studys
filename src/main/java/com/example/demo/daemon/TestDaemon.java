package com.example.demo.daemon;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * @program: demo
 * @description: 守护进程
 * @author: chenyixin
 * @create: 2019-10-15 14:00
 **/
public class TestDaemon {
    static CountDownLatch latch=new CountDownLatch(4000);

        public static void main(String[] args) {
            int count = 2000;

            List<MyThread> threads = new ArrayList<>(count);
            for (int i = 0; i < count; i ++) {
                MyThread userThread = new MyThread();
                userThread.setDaemon(false);
                threads.add(userThread);

                MyThread daemonThread = new MyThread();
                daemonThread.setDaemon(true);
                threads.add(daemonThread);

            }

            for (int i = 0; i < 4000; i++) {
                threads.get(i).start();
                latch.countDown();
            }
            System.out.println("main over");
            try {
                Thread.sleep(20000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("main stop");

        }

        static class MyThread extends Thread {
            @Override
            public void run() {
                try {
                    latch.await();
                    if (this.isDaemon()) {
                        System.out.println("daemon");
                    } else {
                        System.out.println("not daemon");
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }
}
