package com.example.demo.bit;


//因为BitSet中可以存true/false,而且是按位存储，所以在数据量很大的时候，合理的使用BitSet可以节省很大的内存空间，
//提高程序的运算效率。

// 下面是我使用Bitset和Arrays工具类进行排序的测试类

import org.apache.commons.lang3.RandomUtils;

import java.util.Arrays;
import java.util.BitSet;

public class BitSetSort {

    public static void main(String[] args) {
        int[] nums=generateNumber(10000);
        System.out.println("nums:"+Arrays.toString(nums));
		int[] sortNums = sortNums(nums);
        int[] sortNums1=sortNums1(nums);
        System.out.println(sortNums.length);
        System.out.println("sortNums:"+Arrays.toString(sortNums));
        System.out.println(sortNums1.length);
        System.out.println("sortNums1:"+Arrays.toString(sortNums1));
//		System.out.println(sortNums);
    }

    // 初始化一千万整数
    private static int[] generateNumber(int size){
        long start = System.currentTimeMillis();
        System.out.println("开始生成数据");
        int[] nums = new int[size];
        for(int i=0;i<size;i++){
            nums[i] = RandomUtils.nextInt(0, size);
        }
        System.out.println("生成数据完成,耗时:"+(System.currentTimeMillis()-start)+"毫秒");
        return nums;
    }


    // 使用BitSet进行排序
    private static int[] sortNums(int[] nums){

        long start = System.currentTimeMillis();
        System.out.println("开始排序");
        int len = nums.length;
        BitSet bitSet = new BitSet(len);
        int[] newArray=new int[len];
        bitSet.set(0, len, false);
        for(int i=0;i<len;i++){
            bitSet.set(nums[i], true);
        }
        for(int j=0, i=0;i<len;i++){
            if(bitSet.get(i)){
                newArray[j]=i;
                j++;
            }
        }
        System.out.println("排序完成,耗时:"+(System.currentTimeMillis()-start)+"毫秒");
        return newArray;
    }


    // 使用Arrays工具类进行排序
    private static int[] sortNums1(int[] nums){
        long start = System.currentTimeMillis();
        System.out.println("开始排序");
        Arrays.sort(nums);
        System.out.println("排序完成,耗时:"+(System.currentTimeMillis()-start)+"毫秒");
        return nums;
    }

}