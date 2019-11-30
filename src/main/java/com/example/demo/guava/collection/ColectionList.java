package com.example.demo.guava.collection;

import com.google.common.collect.Sets;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @program: demo
 * @description:
 * @author: chenyixin
 * @create: 2019-10-30 18:26
 **/
public class ColectionList {
    public static void main(String [] args){
        Set<Integer> sets= Sets.newHashSet(1,2,3,4,5,6);
        Set<Integer> set2=Sets.newHashSet(3,4,5,6,7,8,9);

        Sets.SetView<Integer> intersection =Sets.intersection(sets, set2);
        for(Integer in:intersection){
            System.out.print(in+"  ");
        }
        System.out.println("");
        //差集
        Sets.SetView<Integer> intersection2=Sets.difference(sets,set2);
        for(Integer in:intersection2){
            System.out.print(in+"  ");
        }
        System.out.println("");
        //并集
        Sets.SetView<Integer> intersection3=Sets.union(sets,set2);
        for(Integer in:intersection3){
            System.out.print(in+"  ");
        }

        List list=new ArrayList(6);
        list.add(new Object());

    }
}
