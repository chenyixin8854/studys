package com.example.demo.interview.jdk.spi;

import java.util.ServiceLoader;

/**
 * 虽然ServiceLoader也算是使用的延迟加载，但是基本只能通过遍历全部获取，也就是接口的实现类全部加载并实例化一遍。如果你并不想用某些实现类，它也被加载并实例化了，这就造成了浪费。
 * 获取某个实现类的方式不够灵活，只能通过Iterator形式获取，不能根据某个参数来获取对应的实现类。
 *
 */
public class SPIMain {
    public static void main(String[] args) {
        ServiceLoader<Cmand> loader = ServiceLoader.load(Cmand.class);
        System.out.println(loader);

        for (Cmand Cmand : loader) {
            System.out.println("--");
            Cmand.execute();
        }
    }
}
