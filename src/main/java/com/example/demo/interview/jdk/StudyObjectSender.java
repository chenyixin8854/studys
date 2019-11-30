package com.example.demo.interview.jdk;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ThreadLocalRandom;

/**
 * @program: demo
 * @description:
 * @author: chenyixin
 * @create: 2019-10-17 15:09
 **/
@Slf4j
@AllArgsConstructor
public class StudyObjectSender implements Runnable {
    private StudyObject data;

    // standard constructors

    public void run() {
        String packets[] = {
                "First packet",
                "Second packet",
                "Third packet",
                "Fourth packet",
                "End"
        };

        for (String packet : packets) {
            data.send(packet);

            // Thread.sleep() to mimic heavy server-side processing
            try {
                Thread.sleep(ThreadLocalRandom.current().nextInt(1000, 5000));
            } catch (InterruptedException e)  {
                Thread.currentThread().interrupt();
                log.error("Thread interrupted", e);
            }
        }
    }
}
