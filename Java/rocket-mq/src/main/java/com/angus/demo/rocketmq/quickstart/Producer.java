package com.angus.demo.rocketmq.quickstart;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;

import static com.angus.demo.rocketmq.constants.CommonConstants.*;

/**
 * 消息生产者
 */
public class Producer {
    public static void main(String[] args) throws Exception {
        DefaultMQProducer producer = new DefaultMQProducer("default_mq_producer_group");
        producer.setNamesrvAddr(NAMESRV_ADDR);

        producer.start();
        for (int i = 0; i < 100; i++) {
            Message message = new Message(TOPIC, TAGS, "Hello, Rocket MQ!".getBytes(RemotingHelper.DEFAULT_CHARSET));
            SendResult sendResult = producer.send(message, 2000 * 1000);
            System.out.println(sendResult);
        }
        // 不再使用时需要关闭
        producer.shutdown();
    }
}
