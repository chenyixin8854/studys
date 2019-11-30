package com.example.demo.interview.leetcode;

import java.util.HashSet;
import java.util.LinkedList;

/**
 * @program: demo
 * @description:
 * 标签：滑动窗口
 * 暴力解法时间复杂度较高，会达到 O(n^2)O(n
 * 2
 *  )，故而采取滑动窗口的方法降低时间复杂度
 * 定义一个 map 数据结构存储 (k, v)，其中 key 值为字符，value 值为字符位置 +1，加 1 表示从字符位置后一个才开始不重复
 * 我们定义不重复子串的开始位置为 start，结束位置为 end
 * 随着 end 不断遍历向后，会遇到与 [start, end] 区间内字符相同的情况，此时将字符作为 key 值，获取其 value 值，并更新 start，此时 [start, end] 区间内不存在重复字符
 * 无论是否更新 start，都会更新其 map 数据结构和结果 ans。
 * 时间复杂度：O(n)O(n)
 *
 * @author: chenyixin
 * @create: 2019-11-05 21:53
 **/
public class lengthOfLongestSubstring_3 {

    public static void main(String[] args) {
        String s="kadfkasdfaks";
        System.out.println(getLongestSubString(s));
    }


    public static int getLongestSubString1(String s){
        LinkedList linkedList=new LinkedList();
        int res=0;
        int index=0;
        while (index<s.length()){
            char albet=s.charAt(index);
            if (linkedList.contains(albet)){
                linkedList.remove(linkedList.indexOf(albet));
            }
            linkedList.add(albet);
            res=res>linkedList.size()?res:linkedList.size();
            index++;
        }

        return res;
    }






    public static int getLongestSubString(String s){
        int maxSize = 0;
        int [] dict = new int[256]; //记录ASCII 码字符出现的位置，以字符作为下标
        int base = 0;
        int key = 0;
        int i=0;
        while(key < s.length()){
            i = s.charAt(key);

            if(dict[i] > base){
                base = dict[i];
            }
            dict[i] = key+1; //以1作为起始位置，下标加1
            maxSize = (maxSize>key-base+1)?maxSize:key-base+1;
            key++;
        }
        return maxSize;
        }
}
