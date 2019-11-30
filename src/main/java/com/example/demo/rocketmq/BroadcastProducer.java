package com.example.demo.rocketmq;


import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;

/**
 * @program: demo
 * @description:
 * @author: chenyixin
 * @create: 2019-07-25 09:21
 **/
public class BroadcastProducer {
    public static void main(String[] args) throws Exception {
        DefaultMQProducer producer = new DefaultMQProducer("ProducerGroupName");
        producer.setNamesrvAddr("192.144.140.27:9876;154.8.237.30:9876");
        producer.start();

        for (int i = 0; i < 2; i++){
            Message msg = new Message("dev",
                    "test",
                    "1",
                    "{\"orderId\":1}".getBytes(RemotingHelper.DEFAULT_CHARSET));
            SendResult sendResult = producer.send(msg);
            System.out.printf("%s%n", sendResult);
        }
        producer.shutdown();
    }
}