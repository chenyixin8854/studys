package com.example.demo.interview.jcu;

import java.util.concurrent.locks.LockSupport;

public class LockSupportDemo implements Runnable{
    public static Object sObject = new Object();

    @Override
    public void run() {
        synchronized (sObject){
            System.out.println("当前线程名称：" + Thread.currentThread().getName());
            LockSupport.park();

            if (Thread.currentThread().isInterrupted()){
                System.out.println( Thread.currentThread().getName() +  "被中断了");
            }
            System.out.println(Thread.currentThread().getName() + "执行结束");
        }
    }

    public static void main(String[] args) throws InterruptedException {
        LockSupportDemo demo = new LockSupportDemo();
        Thread t1 = new Thread(demo,"t1");
        Thread t2 = new Thread(demo,"t2");
        t1.start();
        Thread.sleep(3000);
        t2.start();
        t1.interrupt();
        LockSupport.unpark(t2);
    }
}