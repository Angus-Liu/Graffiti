package com.angus.demo.rocketmq.quickstart;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;

import static com.angus.demo.rocketmq.constants.CommonConstants.*;

/**
 * TODO: angus on 2020/9/1 添加注释
 */
public class ProducerQuickStart {
    public static void main(String[] args) throws Exception {
        DefaultMQProducer producer = new DefaultMQProducer("default_mq_producer_group");
        producer.setInstanceName("instance_1");
        producer.setRetryTimesWhenSendFailed(3);
        producer.setNamesrvAddr(NAMESRV_ADDR);
        producer.start();

        for (int i = 0; i < 100; i++) {
            try {
                Message message = new Message(TOPIC, TAGS, ("Hello, RocketMQ " + i).getBytes(RemotingHelper.DEFAULT_CHARSET));
                producer.send(message, new SendCallback() {
                    @Override
                    public void onSuccess(SendResult sendResult) {
                        System.out.println(sendResult);
                    }

                    @Override
                    public void onException(Throwable e) {
                        e.printStackTrace();
                    }
                }, 20 * 1000);
            } catch (Exception e) {
                e.printStackTrace();
                Thread.sleep(1000);
            }
        }

//        producer.shutdown();
    }
}
