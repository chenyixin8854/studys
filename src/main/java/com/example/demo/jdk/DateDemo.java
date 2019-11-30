package com.example.demo.jdk;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @program: demo
 * @description:
 * @author: chenyixin
 * @create: 2019-11-30 14:10
 **/
public class DateDemo {
    public static void main(String[] args) {

//除以1000为了获取精确到秒的时间戳，不除以1000得到毫秒的时间戳

        String timestamp = String.valueOf(new Date().getTime() / 1000);
        System.out.println(timestamp);
        timestamp="1577721599";
        Date date=new Date(Long.parseLong(timestamp)*1000);
        System.out.println(new SimpleDateFormat("YYYY-MMM-dd yyyy-MM-dd").format(date));

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//一般网上的转换是没有中间new Long（timeStamp）,因为他们都是精确到毫秒的时间戳，不用再乘以1000进行转换
        long longTimeStamp = new Long(new Long(timestamp) * 1000);
         date = new Date(longTimeStamp);
        String dareString = simpleDateFormat.format(date);
        System.out.println(dareString);
    }
}
