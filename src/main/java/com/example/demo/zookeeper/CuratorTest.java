package com.example.demo.zookeeper;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.api.CuratorWatcher;
import org.apache.curator.retry.RetryNTimes;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.ZooDefs;

import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * @program: demo
 * @description:
 * @author: chenyixin
 * @create: 2019-10-31 00:09
 **/
public class CuratorTest {
    public static void main(String[] args) throws Exception{
        CuratorFramework client = CuratorFrameworkFactory.newClient("62.234.86.166:2181", new RetryNTimes(10, 5000));
        client.start();// 连接
        // 获取子节点，顺便监控子节点
        List<String> children = client.getChildren().usingWatcher(new CuratorWatcher() {
            public void process(WatchedEvent event) throws Exception
            {
                System.out.println("监控： " + event);
            }
        }).forPath("/");
        System.out.println(children);
        // 创建节点
//        String result = client.create().withMode(CreateMode.PERSISTENT).withACL(ZooDefs.Ids.OPEN_ACL_UNSAFE).forPath("/test1", "Data".getBytes());
//        System.out.println(result);
        // 设置节点数据
        client.setData().forPath("/test", "111".getBytes());
        client.setData().forPath("/test", "222".getBytes());
        Iterator<String> paths=client.getChildren().forPath("/").listIterator();
        while (paths.hasNext()){
            System.out.println("目录"+paths.next());
        }
        Iterator<String> paths1=client.getChildren().forPath("/zookeeper").listIterator();
        while (paths1.hasNext()){
            System.out.println("目录"+paths1.next());
        }

        // 删除节点
        //System.out.println(client.checkExists().forPath("/test"));
        /*client.delete().withVersion(-1).forPath("/test");
        System.out.println(client.checkExists().forPath("/test"));*/
        client.close();
        System.out.println("OK！");
    }
}