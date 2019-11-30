package com.example.demo.interview.blockqueue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @program: demo
 * @description: 阻塞队列
 * @author: chenyixin
 * @create: 2019-10-16 00:18
 **/
public class TestBlockQueue {

    private volatile boolean FLAG = true;//默认开启，进行生产和消费
    AtomicInteger atomicInteger = new AtomicInteger();
    BlockingQueue<String> blockingQueue = null;

    public TestBlockQueue(BlockingQueue<String> blockingQueue) {
        this.blockingQueue = blockingQueue;
        System.out.println(blockingQueue.getClass().getName());
    }

    public void myProd() throws Exception {
        String data = null;
        boolean result;
        while (FLAG) {
            data = String.valueOf(atomicInteger.incrementAndGet());
            result = blockingQueue.offer(data, 2L, TimeUnit.MICROSECONDS);
            if (result) {
                System.out.println(Thread.currentThread().getName() + "\t 插入队列data:" + data + "成功");
            } else {
                System.out.println(Thread.currentThread().getName() + "\t 插入队列data:" + data + "失败");
            }
            //一秒生产一个
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


        }
        System.out.println(Thread.currentThread().getName() + "\t 生产被叫停，Flag为false");

    }

    public void myConsumer() throws Exception {
        String value = null;
        while (FLAG) {
            value = blockingQueue.poll(1, TimeUnit.SECONDS);
            if (value == null || value.equalsIgnoreCase("")) {
//                FLAG = false;
                System.out.println(Thread.currentThread().getName() + "\t 超过两秒没收到消息");
//                return;
            }
            System.out.println(Thread.currentThread().getName() + "\t 消费队列value：" + value + "成功");
        }

    }

    public void stop() {
        FLAG = false;
    }
}
