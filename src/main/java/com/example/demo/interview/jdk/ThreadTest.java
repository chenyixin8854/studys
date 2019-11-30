package com.example.demo.interview.jdk;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @program: demo
 * @description:
 * @author: chenyixin
 * @create: 2019-10-22 09:23
 **/
public class ThreadTest {
    public static void main(String[] args) throws IOException {
        Object object = new Object();
        ReentrantLock lock = new ReentrantLock();
        Condition condition = lock.newCondition();
        new Thread(() -> {
            synchronized (object) {
                try {
                    System.out.println("thread1 waiting");
                    object.wait();
// object.wait(5000);
                    System.out.println("thread1 after waiting");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "Thread1").start();
        new Thread(() -> {
            synchronized (object) {
                try {
                    System.out.println("thread2 notify");
                    // 打开或关闭这段注释，观察Thread1的状态
// object.notify();【本文由公从号“彤哥读源码”原创】
                    // notify之后当前线程并不会释放锁，只是被notify的线程从等待队列进入同步队列
                    // sleep也不会释放锁
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "Thread2").start();
        new Thread(() -> {
            lock.lock();
            System.out.println("thread3 waiting");
            try {
//                condition.await();
                condition.await(20, (TimeUnit.SECONDS));
                System.out.println("thread3 after waiting");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }, "Thread3").start();
        new Thread(() -> {
            lock.lock();
            System.out.println("thread4");
            // 打开或关闭这段注释，观察Thread3的状态
// condition.signal();【本文由公从号“彤哥读源码”原创】
            // signal之后当前线程并不会释放锁，只是被signal的线程从等待队列进入同步队列
            // sleep也不会释放锁
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }, "Thread4").start();

//        LockSupport lockSupport=new LockSupport();
        LockSupport.park();
        LockSupport.park(new Object());
    }


}
