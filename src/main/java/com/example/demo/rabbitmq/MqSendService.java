package com.example.demo.rabbitmq;


import com.example.demo.cons.MqConst;
import com.example.demo.cons.MqProperties;
import com.example.demo.utils.BeanUtil;
import com.example.demo.utils.IdUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

/**
 * @program: parent
 * @description: 消息队列发送
 * @author: chenyixin
 * @create: 2019-09-23 12:57
 **/
@Slf4j
@Service
public class MqSendService implements RabbitTemplate.ReturnCallback, RabbitTemplate.ConfirmCallback{
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        log.info("correlationData: " + correlationData);
        log.info("ack: " + ack);
        if(!ack){
            log.info("异常处理....");
        }
    }

    @Override
    public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {
        log.info("return exchange: {}, routingKey: {}, replyCode: {}, replyText: {}", exchange, routingKey, replyCode, replyText);
    }


    /**
     * 发送到merchant的消息
     * @param message
     * @param mqProperties
     * @throws Exception
     */
    public void sendToMerchant(Object message, MqProperties mqProperties)  {
        try {
            Map<String, Object> properties= BeanUtil.objectToMap(mqProperties);
            MessageHeaders mhs = new MessageHeaders(properties);
            org.springframework.messaging.Message<Object> msg = MessageBuilder.createMessage(message, mhs);
            rabbitTemplate.setConfirmCallback(this::confirm);
            rabbitTemplate.setReturnCallback(this::returnedMessage);
            CorrelationData correlationData = new CorrelationData(String.valueOf(IdUtil.nextId()));
            if (mqProperties.getDelay()!=null){
                sendWithDelay(MqConst.CUSTOMER_CLUSTER_EXCHANGER, "merchant", msg, correlationData,mqProperties.getDelay());
            }else{
                rabbitTemplate.convertAndSend(MqConst.CUSTOMER_CLUSTER_EXCHANGER, "merchant", msg, correlationData);
            }
        }catch (Exception e){
            log.error("MQ发送消息失败",e);
        }

    }

    /**
     * 发送到admin的消息
     * @param message
     * @param mqProperties
     * @throws Exception
     */
    public void sendToAdmin(Object message,  MqProperties mqProperties){
        try {
            Map<String, Object> properties= BeanUtil.objectToMap(mqProperties);
            MessageHeaders mhs = new MessageHeaders(properties);
            org.springframework.messaging.Message<Object> msg = MessageBuilder.createMessage(message, mhs);
            rabbitTemplate.setConfirmCallback(this::confirm);
            rabbitTemplate.setReturnCallback(this::returnedMessage);
            CorrelationData correlationData = new CorrelationData(String.valueOf(IdUtil.nextId()));
            if (mqProperties.getDelay()!=null){
                sendWithDelay(MqConst.CUSTOMER_CLUSTER_EXCHANGER, "admin", msg, correlationData, (Integer) mqProperties.getDelay());
            }else{
                rabbitTemplate.convertAndSend(MqConst.CUSTOMER_CLUSTER_EXCHANGER, "admin", msg, correlationData);
            }
        }catch (Exception e){
            log.error("MQ发送消息失败",e);
        }
    }


    /**
     * 发送到merchant的消息
     * @param message
     * @param mqProperties
     * @throws Exception
     */
    public void sendToCustomer1(Object message, MqProperties mqProperties){
        try {
            Map<String, Object> properties= BeanUtil.objectToMap(mqProperties);
            MessageHeaders mhs = new MessageHeaders(properties);
            org.springframework.messaging.Message<Object> msg = MessageBuilder.createMessage(message, mhs);
            rabbitTemplate.setConfirmCallback(this::confirm);
            rabbitTemplate.setReturnCallback(this::returnedMessage);
            CorrelationData correlationData = new CorrelationData(String.valueOf(IdUtil.nextId()));
            if (mqProperties.getDelay()!=null){
                sendWithDelay(MqConst.CUSTOMER_CLUSTER_EXCHANGER, "merchant", msg, correlationData,mqProperties.getDelay());
            }else{
                rabbitTemplate.convertAndSend(MqConst.CUSTOMER_CLUSTER_EXCHANGER, "merchant", msg, correlationData);
            }
        }catch (Exception e){
            log.error("发送MQ消息失败",e);
        }

    }

    /**
     * 发送到admin的消息
     * @param message
     * @param mqProperties
     * @throws Exception
     */
    public void sendToAdmin1(Object message,  MqProperties mqProperties) throws Exception {
        try {
            Map<String, Object> properties= BeanUtil.objectToMap(mqProperties);
            MessageHeaders mhs = new MessageHeaders(properties);
            org.springframework.messaging.Message<Object> msg = MessageBuilder.createMessage(message, mhs);
            rabbitTemplate.setConfirmCallback(this::confirm);
            rabbitTemplate.setReturnCallback(this::returnedMessage);
            CorrelationData correlationData = new CorrelationData(String.valueOf(IdUtil.nextId()));
            if (mqProperties.getDelay()!=null){
                sendWithDelay(MqConst.CUSTOMER_CLUSTER_EXCHANGER, "admin", msg, correlationData, (Integer) mqProperties.getDelay());
            }else{
                rabbitTemplate.convertAndSend(MqConst.CUSTOMER_CLUSTER_EXCHANGER, "admin", msg, correlationData);
            }
        }catch (Exception e){
            log.error("发送MQ消息失败",e);
        }}


    /**
     * 延迟发送
     * @param exchange
     * @param routingKey
     * @param object
     * @param correlationData
     * @param seconds
     */
    private void sendWithDelay(String exchange, String routingKey, Object object,CorrelationData correlationData,Integer seconds){
        Timer timer = new Timer();// 实例化Timer类
        timer.schedule(new TimerTask() {
            public void run() {
                rabbitTemplate.convertAndSend(exchange, routingKey, object, correlationData);
            }
        }, seconds*1000);// 毫秒
    }


}
