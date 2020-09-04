package com.angus.demo.rocketmq.quickstart;

import org.apache.rocketmq.client.producer.MessageQueueSelector;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageQueue;

import java.util.List;

/**
 * MessageQueueSelector 用于控制消息发往指定的 Message Queue
 */
public class OrderMessageQueueSelector implements MessageQueueSelector {
    @Override
    public MessageQueue select(List<MessageQueue> mqs, Message msg, Object arg) {
        return null;
    }
}
