package com.example.demo.interview.jdk.threadpool;

import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * @program: demo
 * @description:
 * @author: chenyixin
 * @create: 2019-10-23 11:34
 **/
public class ThreadPoolTest {
    private static final int threads = 100;
    // 用于计数线程是否执行完成
    static CountDownLatch countDownLatch = new CountDownLatch(threads);
    public void test(){
        Executors.newScheduledThreadPool(1);

    }

    /**
     * newFixedThreadPool execute
     *
     * @throws ExecutionException
     * @throws InterruptedException
     */
    public static void test1() throws ExecutionException, InterruptedException {
        System.out.println("---- start ----");
        ExecutorService executorService = Executors.newWorkStealingPool();
        for (int i = 0; i < threads; i++) {
            executorService.execute(() -> {
                try {
                    System.out.println(Thread.currentThread().getName());
                } catch (Exception e) {
                    System.out.println(e);
                } finally {
                    countDownLatch.countDown();
                }
            });
        }
        countDownLatch.await();
        System.out.println("---- end ----");
    }

    /**
     * newFixedThreadPool submit submit
     */
    public static void test2() throws InterruptedException {
        System.out.println("---- start ----");
        ExecutorService executorService = Executors.newWorkStealingPool();
        for (int i = 0; i < threads; i++) {
//            Callable 带返回值
            executorService.submit(new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(200L);
                        System.out.println(Thread.currentThread().getName());
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        countDownLatch.countDown();
                    }
                }
            }));
        }
        countDownLatch.await();
        System.out.println("---- end ----");
    }
    /**
     * newFixedThreadPool submit Callable
     *
     * @throws ExecutionException
     * @throws InterruptedException
     */
    public static void test3() throws ExecutionException, InterruptedException, IOException {
        System.out.println("---- start ----");
        List<FutureTask> list=new ArrayList<>();
        ExecutorService executorService = Executors.newWorkStealingPool();
        for (int i = 0; i < threads; i++) {
//          Runnable 带返回值
            FutureTask<?> futureTask = new FutureTask<>(new Callable<String>() {
                /**
                 * call
                 * @return currentThreadName
                 */
                @Override
                public String call() throws InterruptedException {
                    Thread.sleep(200L);
                    return Thread.currentThread().getName();
                }
            });
            executorService.submit(new Thread(futureTask));
            list.add(futureTask);

        }

        list.stream().forEach(futureTask -> {
            try {
                System.out.println(futureTask.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        });
        System.out.println("---- end ----");
    }

    public static void main(String[] args) {
        try {
            System.out.println(Runtime.getRuntime().availableProcessors());
            test1();
            test2();
            test3();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
