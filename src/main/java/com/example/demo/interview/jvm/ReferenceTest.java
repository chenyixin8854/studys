package com.example.demo.interview.jvm;

import java.lang.ref.*;
import java.lang.reflect.Field;

/**
 * @program: demo
 * @description:
 * @author: chenyixin
 * @create: 2019-10-22 16:29
 **/
public class ReferenceTest {
    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
        //强引用
        String strong=new String("hello");
        System.out.println(strong);
        //软引用 内存空间不足时回收
        SoftReference<String> sr = new SoftReference<String>(new String("hello"));
        System.out.println(sr.get());
        //弱引用  垃圾回收时立即回收
        WeakReference<String> sr1 = new WeakReference<String>(new String("hello"));
        System.out.println(sr1.get());
        //虚引用 它唯一的目的就是为一个对象设置虚引用关联的唯一目的就是能在这个对象被收集器回收时收到一个系统通知
        ReferenceQueue<String> queue = new ReferenceQueue<String>();
        PhantomReference<String> pr = new PhantomReference<String>(new String("hello"), queue);
        System.out.println(pr.get());

        System.gc();//通知JVM的gc进行垃圾回收
        System.out.println(strong);
        System.out.println(sr.get());
        System.out.println(sr1.get());
        System.out.println(pr.get());
        //queue会在垃圾回收后收添加head列表元素
        Object obj =queue.poll();
        Field rereferent = Reference.class.getDeclaredField("referent");
        rereferent.setAccessible(true);
        Object result = rereferent.get(obj);
        System.out.println("gc will collect：" + result.getClass() + "@" + result.hashCode() + "\t" + (String) result);

    }
}
