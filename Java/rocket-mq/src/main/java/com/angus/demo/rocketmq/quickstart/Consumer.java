package com.angus.demo.rocketmq.quickstart;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;

import static com.angus.demo.rocketmq.constants.CommonConstants.*;

/**
 * 消息消费者
 */
public class Consumer {

    public static void main(String[] args) throws Exception {
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("default_mq_push_consumer_group");
        consumer.setNamesrvAddr(NAMESRV_ADDR);

        // 指定从哪里开始
        consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);
        // 订阅待消费消息的主题和标签
        consumer.subscribe(TOPIC, TAGS);

        // 注册监听器处理从 broker 获取到的消息
        consumer.registerMessageListener((MessageListenerConcurrently) (msgs, context) -> {
            System.out.println(Thread.currentThread().getName() + " Receive new message: " + msgs);
            return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
        });

        consumer.start();
    }

}
