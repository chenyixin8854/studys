package com.example.demo.bit;

import org.springframework.boot.origin.SystemEnvironmentOrigin;

/**
 * @program: demo
 * @description:
 * @author: chenyixin
 * @create: 2019-10-16 19:30
 **/
public class BitTest {
    public static void main(String[] args) {
//        System.out.println(1L<<0);
//        System.out.println(1L<<1);
//        System.out.println(1L<<2);
//        System.out.println(1L<<3);
//        System.out.println(1L<<4);
//        System.out.println(1L<<5);
//        System.out.println(1L<<6);
//        System.out.println(1L<<7);
        int [] array={0,1,3,4,5};
        System.out.println("缺少数为"+missingNumberInByBitSet(array));
    }

    public static int missingNumberInByBitSet(int[] array) {
        int bitSet = 0;
        for (int element : array) {
            bitSet |= 1 << element;
            System.out.println("bitset:"+bitSet);
        }

        for (int i = 0; i < array.length; i++) {
            int temp=bitSet & 1 << i;
            System.out.println(1 << i);
            System.out.println("temp:"+temp);
            if ((bitSet & 1 << i) == 0) {
                return i;
            }
        }

        return 0;
    }
}
