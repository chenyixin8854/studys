package com.example.demo.lock;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

import static java.lang.Thread.currentThread;
import static java.lang.Thread.sleep;
import static java.util.concurrent.locks.LockSupport.park;
import static java.util.concurrent.locks.LockSupport.unpark;

/**
 * @program: demo
 * @description: 耗费CPU资源
 * @author: chenyixin
 * @create: 2019-11-02 14:46
 **/
public class SpinLock implements Lock {
    private volatile AtomicInteger state=new AtomicInteger(0);
    private ArrayBlockingQueue arrayBlockingQueue=new ArrayBlockingQueue(100);
    @Override
    public void lock() {
        while (!state.compareAndSet(0,1)){
            //获取锁失败，自旋
// 1           yield();//优化，减少CPU占用
            try {
                sleep(10);//2优化，自动唤醒，缺点时间不好把控
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        //3 park加自旋
        park();
        arrayBlockingQueue.add(currentThread());
        //执行
//        unpark();

        System.out.println("获取锁成功");
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {

    }

    @Override
    public boolean tryLock() {
        return false;
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return false;
    }

    @Override
    public void unlock() {
        if (state.intValue()==1){
            state.compareAndSet(1,0);
        }
    }

    @Override
    public Condition newCondition() {
        return null;
    }
}
