package com.example.demo.string;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StopWatch;

import java.util.concurrent.CountDownLatch;

/**
 * @program: demo
 * String 直接进行连接操作时 1.创建不经常使用的字符串常量，占用内存空间 2.JVM优化时使用StringBuilder,会频繁创建对象  3.多线程下不可用，因为String是不可变对象
 * StringBuilder字符串连接快，但是线程共用时不安全（由于扩容时未进行同步操作）
 * StringBuffer字符串连接慢，使用synchorize关键字
 * @description:
 * @author: chenyixin
 * @create: 2019-10-10 09:07
 **/
@Slf4j
public class StringDemo {


    public static void main(String[] args) throws InterruptedException {
       testStringBuffer();
       testStringBuffer();
    }


    private static void testStringBuiler() throws InterruptedException {

        StringBuilder stringBuilder = new StringBuilder();
        CountDownLatch latch=new CountDownLatch(10);
        for (int i = 0; i < 10; i++){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    StopWatch stopWatch=new StopWatch();
                    stopWatch.start();
                    try {
                        latch.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    for (int j = 0; j < 1000; j++){
                        stringBuilder.append("a");
                    }
                    stopWatch.stop();
                    log.info("用时{}ms",stopWatch.getTotalTimeMillis());
                }
            }).start();
            latch.countDown();
        }
        System.out.println(stringBuilder.length());

        Thread.sleep(5000);

    }

    private static void testStringBuffer() throws InterruptedException {
        StringBuffer stringBuffer = new StringBuffer();
        CountDownLatch latch=new CountDownLatch(10);
        for (int i = 0; i < 10; i++){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    StopWatch stopWatch=new StopWatch();
                    stopWatch.start();
                    try {
                        latch.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    for (int j = 0; j < 1000; j++){
                        stringBuffer.append("a");
                    }
                    stopWatch.stop();
                    log.info("用时{}ms",stopWatch.getTotalTimeMillis());
                }
            }).start();
            latch.countDown();
        }

        System.out.println(stringBuffer.length());
        Thread.sleep(5000);
    }


}
