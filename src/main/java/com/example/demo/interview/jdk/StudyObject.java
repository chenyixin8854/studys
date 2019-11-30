package com.example.demo.interview.jdk;

import lombok.extern.slf4j.Slf4j;

/**
 * @program: demo
 * @description:
 * @author: chenyixin
 * @create: 2019-10-17 15:04
 **/
@Slf4j
public class StudyObject {
    private String packet;

    // True if receiver should wait
    // False if sender should wait
    private boolean transfer = true;

    public synchronized void send(String packet) {
        while (!transfer) {
            try {
                wait();
            } catch (InterruptedException e)  {
                Thread.currentThread().interrupt();
                log.error("Thread interrupted", e);
            }
        }
        transfer = false;

        this.packet = packet;
        notifyAll();
    }

    public synchronized String receive() {
        while (transfer) {
            try {
                wait();
            } catch (InterruptedException e)  {
                Thread.currentThread().interrupt();
                log.error("Thread interrupted", e);
            }
        }
        transfer = true;

        notifyAll();
        return packet;
    }
}
