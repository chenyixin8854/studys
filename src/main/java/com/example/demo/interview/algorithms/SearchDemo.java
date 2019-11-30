package com.example.demo.interview.algorithms;

/**
 * @program: demo
 * @description: 二分法查找
 * @author: chenyixin
 * @create: 2019-11-05 10:42
 **/
public class SearchDemo {

    public static void main(String[] args) {
        int[] array=new int[]{1,4,6,33,39,42,53,59,70,71,73,96,98,101,109,117,127,146,148,177,195,195,196,225,232,232,255,256,266,271,276,304,306,308,327,331,346,362,363,369,378,385,397,400,406,419,426,439,441,464,465,470,476,484,498,504,507,513,551,601,604,616,631,632,684,699,701,704,705,720,721,734,736,744,770,794,804,805,820,829,833,835,861,872,876,882,887,889,891,892,896,924,926,932,936,936,938,965,970,982,986};
        int index=searchIndex(array,225);
        System.out.println(index);
        System.out.println(array[index]);
    }

    private static int searchIndex(int[] array,int number) {
        int start=0;
        int end=array.length;
        return binarySearch(start,end,number,array);
    }

    private static int binarySearch(int start,int end,int number,int[] array){
        int middle=start+(end-start)/2;
        int indexNumber=array[middle];
        if (start!=middle){
            if (indexNumber==number){
                return middle;
            }else if (indexNumber>number){
                return binarySearch(start,middle,number,array);
            }else if (indexNumber<number){
                return binarySearch(middle,end,number,array);
            }
        }
        return -1;
    }
}
