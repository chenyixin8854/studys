package com.example.demo.interview.jdk;

/**
 * @program: demo
 * @description:
 * @author: chenyixin
 * @create: 2019-10-17 15:14
 **/
public class StudyObjectTest {
    public static void main(String[] args) {
        StudyObject data = new StudyObject();
        Thread sender = new Thread(new StudyObjectSender(data));
        Thread receiver = new Thread(new StudyObjectReceiver(data));

        sender.start();
        receiver.start();
    }
}
