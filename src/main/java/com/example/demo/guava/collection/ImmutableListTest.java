package com.example.demo.guava.collection;

import com.google.common.collect.ImmutableList;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @program: demo
 * @description: 使用collection的unmodifiableList方法  使用guava
 * @author: chenyixin
 * @create: 2019-10-30 18:11
 **/
public class ImmutableListTest {
    public static void main(String [] args){
        //只读设置
        List ls=new ArrayList();
        ls.add("a");
        ls.add("b");
        ls.add("c");
        //不使用guava的类库
        List <String > readList= Collections.unmodifiableList(ls);
        System.out.println(readList.hashCode());
//        readList.add("d"); //报错
        //使用guava的类库
        List<String> imutableList= ImmutableList.of("a","b","c");
        System.out.println(imutableList.hashCode());
        imutableList.add("a"); //运行报错
    }
}
