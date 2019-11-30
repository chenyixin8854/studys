package com.example.demo.data_stract;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @program: demo
 * @description:
 * @author: chenyixin
 * @create: 2019-10-17 11:00
 **/
public class HashMapDemo {
    public static void main(String[] args) {
        HashMap<String,String> map=new HashMap();
        map.put("","");
        map.get("");

        ConcurrentHashMap<String,String> safeMap=new ConcurrentHashMap<>();
        safeMap.put("","");
        safeMap.get("");

        ThreadLocal threadLocal=new ThreadLocal();
        threadLocal.set("");
        threadLocal.get();
    }
}
