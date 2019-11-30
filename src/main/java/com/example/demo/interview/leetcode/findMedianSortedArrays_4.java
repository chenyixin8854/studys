package com.example.demo.interview.leetcode;

/**
 * @program: demo
 * @description:
 * @author: chenyixin
 * @create: 2019-11-05 23:38
 **/
public class findMedianSortedArrays_4 {
    public static void main(String[] args) {
        int[] array1=new int[]{2,6,9,11,12,19,23};
        int[] array2=new int[]{1,4,5,8,11};

        double median=findMedianSortedArrays(array1,array2);
    }

    private static double findMedianSortedArrays(int[] array1, int[] array2) {
        double delta=0;//补偿
        if (array1.length%2==1){
            delta+=0.5d;
        }
        if (array2.length%2==1){
            delta+=0.5d;
        }
        int medianIndex1=array1.length-1>>1;
        int medianIndex2=array2.length-1>>1;
        System.out.printf("medianIndex1:"+medianIndex1+"   medianIndex2:"+medianIndex2);

            int p=array1[medianIndex1]-array2[medianIndex2];
            if (p==0){
                if (delta==0){
                    //说明中位数相等且合并后为偶数位
                    return array1[medianIndex1];
                }else if (delta==0.5d){
                    //说明合并后为奇数位此时应该向代价最小的高位进0.5
                    p=array1[medianIndex1+1]-array2[medianIndex2+1];
                    if (p==0){
                        return array1[medianIndex1];
                    }else if(p<0){
                        return array1[medianIndex1+1];
                    }else{
                        return array2[medianIndex2+1];
                    }

                }else{
                    //说明合并后为偶数
                    p=array1[medianIndex1+1]-array2[medianIndex2+1];
                    if (p==0){
                        return array1[medianIndex1];
                    }else if(p<0){
                        return array1[medianIndex1+1];
                    }else{
                        return array2[medianIndex2+1];
                    }

                }
            }else{

            }

        return 0;
    }


}
