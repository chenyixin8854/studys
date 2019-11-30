package com.example.demo.jvm.objstr;

import org.openjdk.jol.info.ClassLayout;

/**
 * @program: demo
 * @description:
 * @author: chenyixin
 * @create: 2019-11-02 12:14
 **/
public class Demo {
    private Integer num=1;
    public static final String num2="123";

    public static void main(String[] args) {
        Demo demo=new Demo();
        System.out.println(ClassLayout.parseInstance(demo).toPrintable());
        System.out.println(Integer.toHexString(demo.hashCode()));
        //计算Hashcode后才会体现，注意大小端排序
        System.out.println(ClassLayout.parseInstance(demo).toPrintable());
        System.out.println("");
        System.out.println(ClassLayout.parseClass(Demo.class).toPrintable());
    }
}
