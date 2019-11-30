package com.example.demo.interview.jdk;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @program: demo
 * @description: 学习Hashmap
 * @author: chenyixin
 * @create: 2019-10-15 23:42
 **/
public class StudyHashMap {

    public static void main(String[] args) {
        HashMap map=new HashMap();
        map.put(null,2);
//
//        Hashtable hashtable=new Hashtable();

        ConcurrentHashMap concurrentHashMap=new ConcurrentHashMap();
        concurrentHashMap.put(null,"");
    }

    //HashMap不是线程安全的1.put方法会导致数据丢失和环形链表的问题 2.get方法遇到环形链表会发生死锁

    //hashMap 默认容量是16 0.75

    //HashMap遇到hash碰撞时会选择联表和红黑树存储 ，当联表长度>=8 和 map的容量大于64时才会使用红黑树，否则仅仅是执行扩容
}
