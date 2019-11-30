package com.example.demo;

import com.example.demo.cons.MessageTag;
import com.example.demo.cons.MqProperties;
import com.example.demo.cons.MyMessage;
import com.example.demo.rabbitmq.MqSendService;
import com.example.demo.rabbitmq.Order;
import com.example.demo.rabbitmq.RabbitSender;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @program: demo
 * @description:
 * @author: chenyixin
 * @create: 2019-09-23 10:28
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class RabbitmqSpringbootProductApplicationTests {

    @Autowired
    private MqSendService mqSendService;

    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

//    @Test
//    public void testSender1() throws Exception {
//        Order order = new Order("001", "第一个订单");
//        Map<String, Object> properties = new HashMap<>();
//        properties.put("number", "12345");
//        properties.put("send_time", simpleDateFormat.format(new Date()));
//        rabbitSender.send(order, properties);
//    }
//
//    @Test
//    public void testSender2() throws Exception {
//        Order order = new Order("001", "第一个订单");
//        rabbitSender.sendOrder(order);
//    }

    @Test
    public void testSendToMerchant() throws Exception {
        MyMessage myMessage=new MyMessage(1L);
        MqProperties mqProperties=MqProperties.builder().merchantId(1l).messageTag(MessageTag.CUSTOMER_ORDER_CANCEL.getTag()).build();
        mqSendService.sendToMerchant(myMessage,mqProperties);
    }

}