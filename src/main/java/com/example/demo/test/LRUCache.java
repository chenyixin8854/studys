package com.example.demo.test;

import java.util.HashMap;

/**
 * @program: demo
 * @description:
 * @author: chenyixin
 * @create: 2019-10-21 02:17
 **/
public class LRUCache {

    int capacity;
    HashMap<Integer, Node> map = new HashMap<Integer, Node>();

    Node head = null;
    Node end = null;

    public LRUCache(int capacity) {
        this.capacity = capacity;
    }

    public int get(int key) {
        if (map.containsKey(key)) {
            Node n = map.get(key);
            remove(n);
            setHead(n);
            printNodes("get");
            return n.value;
        }
        printNodes("get");
        return -1;
    }

    public void remove(Node n) {
        if (n.pre != null) {
            n.pre.next = n.next;
        } else {
            head = n.next;
        }

        if (n.next != null) {
            n.next.pre = n.pre;
        } else {
            end = n.pre;
        }

    }

    public void setHead(Node n) {
        n.next = head;
        n.pre = null;

        if (head != null)
            head.pre = n;

        head = n;

        if (end == null)
            end = head;
    }

    public void set(int key, int value) {
        if (map.containsKey(key)) {
            Node old = map.get(key);
            old.value = value;
            remove(old);
            setHead(old);
        } else {
            Node created = new Node(key, value);
            if (map.size() >= capacity) {
                map.remove(end.key);
                remove(end);
                setHead(created);

            } else {
                setHead(created);
            }

            map.put(key, created);
        }
        printNodes("set");
    }

    public void printNodes(String explain) {

        System.out.print(explain + ":" + head.toString());
        Node node = head.next;
        while (node != null) {
            System.out.print(node.toString());
            node = node.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        LRUCache lruCacheTest = new LRUCache(5);
        lruCacheTest.set(1, 1);
        lruCacheTest.set(2, 2);
        lruCacheTest.set(3, 3);
        lruCacheTest.set(4, 4);
        lruCacheTest.set(5, 5);
        System.out.println("lruCacheTest.get(1):" + lruCacheTest.get(1));
        lruCacheTest.set(6, 6);
        System.out.println("lruCacheTest.get(2):" + lruCacheTest.get(2));
    }

}

class Node {
    int key;
    int value;
    Node pre;
    Node next;

    public Node(int key, int value) {
        this.key = key;
        this.value = value;
    }

    @Override
    public String toString() {
        return this.key + "-" + this.value + " ";
    }
}