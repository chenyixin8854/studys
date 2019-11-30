package com.example.demo.interview.jdk;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ThreadLocalRandom;

/**
 * @program: demo
 * @description:
 * @author: chenyixin
 * @create: 2019-10-17 15:13
 **/
@Slf4j
@AllArgsConstructor
public class StudyObjectReceiver implements Runnable {
    private StudyObject load;

    // standard constructors

    public void run() {
        for(String receivedMessage = load.receive();  !"End".equals(receivedMessage); receivedMessage = load.receive()) {

            System.out.println(receivedMessage);

            // ...
            try {
                Thread.sleep(ThreadLocalRandom.current().nextInt(1000, 5000));
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                log.error("Thread interrupted", e);
            }
        }
    }
}
