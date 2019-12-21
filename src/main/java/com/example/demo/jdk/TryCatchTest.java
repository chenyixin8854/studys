package com.example.demo.jdk;

public class TryCatchTest {
    public static void main(String[] args) {

        String a = null;
        try {
            a = a();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("主方法返回 ：" + a);
    }
    public static String a() throws Exception {
        String a="123";
        try {

            System.out.println("执行 try");
            int i = 1 / 0;
        }catch (Exception e){
            System.out.println("执行 catch");
            throw new Exception();
        }finally {
            System.out.println("执行finally");
        }

        return a;
    }

}
