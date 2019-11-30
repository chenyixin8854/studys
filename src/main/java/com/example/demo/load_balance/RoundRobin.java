package com.example.demo.load_balance;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @program: demo
 * @description:
 * @author: chenyixin
 * @create: 2019-10-27 15:55
 **/
public class RoundRobin {
    private static Map<String, Integer> serviceWeightMap = new ConcurrentHashMap<>();

    static {
        serviceWeightMap.put("192.168.1.100", 1);
        serviceWeightMap.put("192.168.1.101", 1);
        serviceWeightMap.put("192.168.1.102", 4);
        serviceWeightMap.put("192.168.1.103", 1);
        serviceWeightMap.put("192.168.1.104", 1);
        serviceWeightMap.put("192.168.1.105", 3);
        serviceWeightMap.put("192.168.1.106", 1);
        serviceWeightMap.put("192.168.1.107", 2);
        serviceWeightMap.put("192.168.1.108", 1);
        serviceWeightMap.put("192.168.1.109", 1);
        serviceWeightMap.put("192.168.1.110", 1);
    }

    private static Integer pos = 0;

    /**
     * 轮询
     * @return
     */
    public static String testRoundRobin() {
        Set<String> keySet = serviceWeightMap.keySet();
        ArrayList<String> keyList = new ArrayList<String>();//为了保证线程安全，做一个类似快照的东西
        keyList.addAll(keySet);

        String server = null;
        synchronized (pos) {
            if (pos > keySet.size()) {
                pos = 0;
            }
            server = keyList.get(pos);
            pos++;
        }
        return server;
    }


    /**
     * 随机
     * @return
     */
    public static String testRandom() {
        Set<String> keySet = serviceWeightMap.keySet();
        ArrayList<String> keyList = new ArrayList<String>();
        keyList.addAll(keySet);

        Random random = new Random();
        int randomPos = random.nextInt(keyList.size());
        String server = keyList.get(randomPos);
        return server;
    }

    /**
     * 源地址hash
     * @param remoteIp
     * @return
     */
    public static String testConsumerHash(String remoteIp) {
        Set<String> keySet = serviceWeightMap.keySet();
        ArrayList<String> keyList = new ArrayList<String>();
        keyList.addAll(keySet);

        int hashCode = remoteIp.hashCode();
        int pos = hashCode % keyList.size();

        return keyList.get(pos);
    }


    /**
     * 加权轮询法
     * @return
     */
    public static String testWeightRoundRobin() {
        Set<String> keySet = serviceWeightMap.keySet();
        Iterator<String> it = keySet.iterator();
        List<String> serverList = new ArrayList<String>();

        while (it.hasNext()) {
            String server = it.next();
            Integer weight = serviceWeightMap.get(server);
            for (int i=0; i<weight; i++) {
                serverList.add(server);
            }
            Collections.shuffle(serverList);
        }

        String server = null;
        synchronized (pos) {
            if (pos > serverList.size()) {
                pos = 0;
            }
            server = serverList.get(pos);
            pos++;
        }
        return server;
    }

}
