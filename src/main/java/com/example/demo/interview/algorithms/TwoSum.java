package com.example.demo.interview.algorithms;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * @program: demo
 * @description:
 * @author: chenyixin
 * @create: 2019-11-05 20:57
 **/
public class TwoSum {
    public static void main(String[] args) {
        int targe=12;
        int[] array=new Random().ints(0, 20).limit(10).toArray();
        printArray(array);
        Map<Integer,Integer> valueMap=new HashMap<>();
        for (int i=0,z=array.length;i<z;i++){
            int require=targe-array[i];
            if (valueMap.containsKey(Integer.valueOf(require))){
                System.out.println(i+"  "+valueMap.get(Integer.valueOf(require)));
            }else {
                valueMap.put(Integer.valueOf(array[i]),Integer.valueOf(i));
            }
        }

    }


    private static void printArray(int[] array){
        for (int i:array){
            System.out.print(","+i);
        }
        System.out.println("");
    }
}
