package com.example.demo.interview.algorithms;

import org.springframework.util.StopWatch;

import java.util.BitSet;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * @program: demo
 * @description:
 * @author: chenyixin
 * @create: 2019-11-05 09:07
 **/
public class SortDemo {
    public static void main(String[] args) {
//        int[] array=new Random().ints(0, 100).limit(12).toArray();
        int[] array=new int[]{720,882,470,507,504,400,146,876,127,73,513,616,936,704,346,195,6,276,932,464,101,439,363,196,926,986,308,4,225,419,362,924,117,476,441,53,699,177,632,938,833,982,484,936,378,266,71,255,498,965,551,33,744,601,770,805,684,39,271,232,327,426,385,42,970,304,397,736,631,98,306,331,604,465,872,109,406,59,232,891,369,889,148,835,829,734,256,195,701,70,861,804,705,96,887,721,820,794,892,896};
        printArray(array);

        StopWatch stopWatch=new StopWatch();
        stopWatch.start();
//        bubbleSort(array);
//        selectSort(array);
//        insertSort(array);
//        insertSort2(array);
//        insertSortShell(array);
//        collectSort(array,0,array.length-1);
//        fastSort(array,0,array.length-1);
//        heapSort(array);
//        heapify(array,array.length,0);
//        buildHeap(array);
        heapSort(array);
        stopWatch.stop();
        System.out.println(stopWatch.getTotalTimeMillis());
        printArray(array);
    }

    /**
     * mo冒泡排序 稳定排序
     * 依次判断前一个元素和当前元素大小，大的往右放，每个步骤后右侧为最最大值
     * @param array
     */
    private static void bubbleSort(int[] array){
        //依次循环，小的在右边的话和大的交换位置
        for (int j=array.length-1;j>0;j--){
            //最大移动到最后
            for (int i=1;i<=j;i++){
                int current=array[i];
                int pre=array[i-1];
                if (current<pre){
                    array[i]=pre;
                    array[i-1]=current;
                }
            }
        }
    }

    /**
     * 选择排序 单重复元素占据第一个位置时 会出现排序不稳定
     * 选择最小的元素，交换到对应的有序位置
     * @param array
     */
    private static void selectSort(int[] array){
        for (int j=0,z=array.length;j<z;j++){
            //找出最小
            int min=array[j];
            int index=j;
            for (int i=j+1;i<z;i++){
                int current=array[i];
                if (current<min){
                    min=current;
                    index=i;
                }
            }
            //交换
            array[index]=array[j];
            array[j]=min;
        }
    }

    /**
     * 插入排序
     * 逐步增加有序区 新元素和有序区元素比较并交换或者插入
     * @param array
     */
    private static void insertSort(int[] array){
        //扩大候选区
        for (int j=1,z=array.length;j<z;j++){

            //重排有序区
            for (int i=j;i>0;i--){
                int current=array[i];
                int pre=array[i-1];
                if (current<pre){
                    array[i]=pre;
                    array[i-1]=current;
                }
            }

        }
    }

    /**
     * 插入排序优化（挪动坑位）
     * 逐步增加有序区 新元素和有序区元素比较并交换或者插入
     * @param array
     */
    private static void insertSort2(int[] array){
        //扩大候选区
        for (int j=1,z=array.length;j<z;j++){
            int current=array[j];
            int i=j;
            //重排有序区
            while (i>0){
                int pre=array[i-1];
                if (current<pre){
                    array[i]=pre;
                    i--;
                }else{
                    break;
                }
            }
            array[i]=current;
        }
    }

    /**
     * 希尔排序
     * 首先按照步长分成若干组数据，对划分的数组进行排序
     * 降低步长继续分组（步长长，分组多元素少，步长短，分组少元素多）
     * @param array
     */
    private static void insertSortShell(int[] array){
        int d=array.length;
        while (d>1){
            d=d>>1;
            for (int x=0;x<d;x++){
                for (int i=x+d;i<array.length;i=i+d){
                    int temp=array[i];
                    int j;
                    for (j=i-d;j>=0&&array[j]>temp;j=j-d){
                        array[j+d]=array[j];
                    }
                    array[j+d]=temp;
                }
            }
        }
    }

    /**
     * 快速排序
     * 寻找基准元素，而后拆分两部分小的放左边，大的放右边
     *
     */
    private static int fastSortPartition(int[] array,int p1,int p2){
        int index=p1+((p2-p1)>>1);
        int standardNumber=array[index];
        int temp;
        while (p1<p2){
            if (array[p1]<=standardNumber){
                p1++;
            }
            if (array[p2]>=standardNumber){
                p2--;
            }
            //到达交换点
            if (array[p1]>standardNumber && array[p2]<standardNumber){
                temp=array[p2];
                array[p2]=array[p1];
                array[p1]=temp;
            }
        }
        array[index]=array[p1];
        array[p1]=standardNumber;
        return p1;
    }

    /**
     * 快速排序
     */
    private static void fastSort(int[] array,int start,int end){
        if (start<end){
           int partition=fastSortPartition(array,start,end);
           fastSort(array,start,partition-1);
           fastSort(array,partition+1,end);
        }
    }



    /**
     * 归并排序
     * 对原数组进行两两切分，而后各自排序后聚合
     * @param array
     */
    private static void collectSort(int[] array,int start,int end){
        System.out.println(start+"="+end);
        if (start<end){
            //分组
            int middle=start+((end-start)>>1);
            collectSort(array,start,middle);
            collectSort(array,middle+1,end);
            //归并
            merge(array,start,middle,end);
        }
    }

    private static void merge(int[] array,int start,int middle,int end){
        System.out.println(start+";"+middle+";"+end);
        int[] temp = new int[end -start+1];
        int i=0;
        int p1=start;
        int p2=middle+1;
        // 比较左右两部分的元素，哪个小，把那个元素填入temp中
        while (p1<=middle && p2<=end){
            temp[i++]=array[p1] <array[p2]?array[p1++]:array[p2++];
        }
        // 上面的循环退出后，把剩余的元素依次填入到temp中
        // 以下两个while只有一个会执行
        while (p1<=middle){
            temp[i++]=array[p1++];
        }
        while (p2<=end){
            temp[i++]=array[p2++];
        }


        // 把最终的排序的结果复制给原数组
        for(i = 0; i <temp.length; i++) {
            array[start + i] = temp[i];
        }

    }

//    /**
//     * 堆排序
//     */
//    private static void heapSort(int[] array){
//        int len=array.length;
//        buildMaxHeap(array,len);
//        for (int i=len-1;i>0;i--){
//            swap(array,0,i);
//            len--;
//            heapIt(array,0,len);
//        }
//    }
//
//    private static void buildMaxHeap(int[] arr,int len){
//        for (int i = len>>1; i>=0; i--){
//            heapIt(arr,i,len);
//        }
//    }
//
//    private static void heapIt(int[] arr,int i ,int len){
//        int left=2*i+1;
//        int right=2*i+2;
//        int largest=i;
//        if (left<len && arr[left] >arr[largest]){
//            largest=left;
//        }
//        if (right<len && arr[right] > arr[largest]){
//            largest=right;
//        }
//        if (largest!=i){
//            swap(arr,i,largest);
//            heapIt(arr,largest,len);
//        }
//    }

    private static void swap(int[] array,int i,int j){
        int temp=array[i];
        array[i]=array[j];
        array[j]=temp;
    }


    /**
     * 计数排序
     */
    private static void statisticSort(int[] array){
        int arrayMax=0;
        int[] sortArray=new int[arrayMax];
        //太浪费空间,除非数之间连续，重复数据累加
    }

    /**
     * bitmap排序
     * @param array
     */
    private static void bitSort(int[] array){
        int arrayMax=0;
        BitSet bitSet=new BitSet(arrayMax);
        //最好连续，丢失重复数据
    }





    private static void printArray(int[] array){
        for (int i:array){
            System.out.print(","+i);
        }
        System.out.println("");
    }




    private static void heapify(int[] array,int length,int n){
        if (n>=length){
            return;
        }
        int c1=2*n+1;
        int c2=2*n+2;
        int currentP=n;
        if (c1<length && array[c1]>array[currentP]){
            currentP=c1;
        }
        if (c2<length && array[c2]>array[currentP]){
            currentP=c2;
        }
        if (currentP!=n){
            System.out.println("currentP:"+currentP+"  n:"+n);
            swap(array,currentP,n);
            heapify(array,length,currentP);
        }
    }


    /**
     * 从最后一个节点的父节点开始构建
     * @param array
     */
    private static void buildHeap(int[] array){
        int lastNode=array.length-1;
        int lastNodeP=(lastNode-1)>>1;
        for (int i=lastNodeP;i>=0;i--){
            heapify(array,array.length,i);
        }

    }



    private static void heapSort(int[] array){
        buildHeap(array);
        int length=array.length;
        for (int i=length-1;i>=0;i--){
            swap(array,0,i);
            heapify(array,i,0);
        }
    }
}
