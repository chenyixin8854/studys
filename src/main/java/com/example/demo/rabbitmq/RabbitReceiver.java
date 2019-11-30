package com.example.demo.rabbitmq;

import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @program: demo
 * @description:
 * @author: chenyixin
 * @create: 2019-09-23 10:22
 **/
@Slf4j
@Component
public class RabbitReceiver {

    @RabbitListener(bindings = @QueueBinding(
            exchange = @Exchange(value = "exchange-1", durable="true", type= "topic", ignoreDeclarationExceptions = "true"), key = "springboot.*" ,
            value = @Queue(value = "queue-1", durable="true")
    )
    )
    @RabbitHandler
    public void onMessage(@Payload Order order, Channel channel, @Headers Map<String, Object> headers) throws Exception {
        log.info("--------------------------------------");
        log.info("消费端Payload: " + order);
        Long deliveryTag = (Long)headers.get(AmqpHeaders.DELIVERY_TAG);
        log.info("消费端deliveryTag: " + deliveryTag);
        log.info("消费端header: " + headers.get("send_time"));
        //手工ACK
        channel.basicAck(deliveryTag, false);
    }

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = "queue-2", durable="true"),
            exchange = @Exchange(value = "exchange-2", durable="true", type= "topic", ignoreDeclarationExceptions = "true"), key = "springboot.*")
    )
    @RabbitHandler
    public void onOrderMessage(@Payload Order order, Channel channel, @Headers Map<String, Object> headers) throws Exception {
        log.info("--------------------------------------");
        log.info("消费端order:{} " + order);
        Long deliveryTag = (Long)headers.get(AmqpHeaders.DELIVERY_TAG);
        //手工ACK
        channel.basicAck(deliveryTag, false);
    }

}