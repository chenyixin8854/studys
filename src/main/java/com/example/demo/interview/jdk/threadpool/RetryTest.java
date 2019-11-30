package com.example.demo.interview.jdk.threadpool;

/**
 * @program: demo
 * @description:
 * @author: chenyixin
 * @create: 2019-10-23 15:09
 **/
public class RetryTest {
    public static void testContinue()
    {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 5; j++)
            {
                System.out.print(j + ", ");
                if (j == 3) {
                    break;
                }
            }
        }
        System.out.print(" >>> OK");
    }

    public static void testBreak()
    {
        label59:
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 5; j++)
            {
                System.out.print(j + ", ");
                if (j == 3) {
                    break label59;
                }
            }
        }

        System.out.print(" >>> OK");
    }

    public static void main(String[] args) {
        testContinue();
        System.out.println(" ");
        testBreak();
    }
}
