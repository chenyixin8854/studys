package com.example.demo.test;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @ Author     ：zhenghaoran.
 * @ Date       ：Created in 14:55 2018/11/30
 * @ Description：
 */
public class DeadLockDetect extends Thread {

    public static void main(String[] args) throws InterruptedException {
        final ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();

        Runnable dlCheck = new Runnable() {
            public void run() {
                long[] deadlockedThreads = threadMXBean.findDeadlockedThreads();
                if (deadlockedThreads != null) {
                    ThreadInfo[] threadInfos = threadMXBean.getThreadInfo(deadlockedThreads);
                    System.out.println("Detected deadlock threads:");
                    for (ThreadInfo threadInfo : threadInfos) {
                        System.out.println(threadInfo.getThreadName());
                    }
                }

            }
        };

        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        //稍等 1 秒，然后每 2 秒进行一次死锁扫描
        scheduler.scheduleAtFixedRate(dlCheck, 1L, 2L, TimeUnit.SECONDS);

        String lockA = "lockA";
        String lockB = "lockB";
        DeadLockSample t1 = new DeadLockSample("Thread1", lockA, lockB);
        DeadLockSample t2 = new DeadLockSample("Thread2", lockB, lockA);
        t1.start();
        t2.start();
        t1.join();
        t2.join();
    }
}