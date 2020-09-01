package com.angus.demo.rocketmq.simple;

import org.apache.rocketmq.client.consumer.DefaultMQPullConsumer;
import org.apache.rocketmq.client.consumer.PullResult;
import org.apache.rocketmq.common.message.MessageQueue;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import static com.angus.demo.rocketmq.constants.CommonConstants.*;

/**
 * Pull 方式 Consumer
 */
public class PullConsumer {

    private static final Map<MessageQueue, Long> OFFSET_TABLE = new HashMap<>();

    public static void main(String[] args) throws Exception {
        DefaultMQPullConsumer consumer = new DefaultMQPullConsumer("default_mq_pull_consumer_group");
        consumer.setNamesrvAddr(NAMESRV_ADDR);
        consumer.start();

        // 获取 Message Queue 并遍历
        Collection<MessageQueue> mqs = consumer.fetchSubscribeMessageQueues(TOPIC);
        for (MessageQueue mq : mqs) {
            System.out.println("Consumer from the Queue: " + mq);

            SINGLE_MQ:
            while (true) {
                try {
                    PullResult pullResult = consumer.pullBlockIfNotFound(mq, null, getMessageQueueOffset(mq), 32);
                    System.out.println(pullResult);
                    // 维护 offset
                    putMessageQueueOffset(mq, pullResult.getNextBeginOffset());
                    // 根据消息的不同状态进行处理
                    switch (pullResult.getPullStatus()) {
                        // 获取到消息
                        case FOUND:
                            break;
                        // 没有新消息
                        case NO_NEW_MSG:
                            break;
                        case NO_MATCHED_MSG:
                            break SINGLE_MQ;
                        case OFFSET_ILLEGAL:
                            break;
                        default:
                            break;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        consumer.shutdown();
    }

    private static long getMessageQueueOffset(MessageQueue mq) {
        Long offset = OFFSET_TABLE.get(mq);
        return offset != null ? offset : 0;
    }

    private static void putMessageQueueOffset(MessageQueue mq, long offset) {
        OFFSET_TABLE.put(mq, offset);
    }
}
