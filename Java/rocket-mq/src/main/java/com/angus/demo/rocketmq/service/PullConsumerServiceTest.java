package com.angus.demo.rocketmq.service;

import org.apache.rocketmq.client.consumer.*;
import org.apache.rocketmq.common.message.MessageQueue;

import static com.angus.demo.rocketmq.constants.CommonConstants.NAMESRV_ADDR;
import static com.angus.demo.rocketmq.constants.CommonConstants.TOPIC;

/**
 * Pull Consumer Service Test
 */
public class PullConsumerServiceTest {
    public static void main(String[] args) {
        final MQPullConsumerScheduleService scheduleService = new MQPullConsumerScheduleService("PullConsumerService1");
        scheduleService.getDefaultMQPullConsumer().setNamesrvAddr(NAMESRV_ADDR);

        scheduleService.registerPullTaskCallback(TOPIC, (mq, context) -> {
            MQPullConsumer consumer = context.getPullConsumer();

            try {
                long offset = consumer.fetchConsumeOffset(mq, false);
                if(offset < 0) offset = 0;

                PullResult pullResult = consumer.pull(mq, "*", offset, 32);
                System.out.println(offset + "\t" + mq + "\t" + pullResult);
                switch (pullResult.getPullStatus()) {
                    case FOUND:
                        break;
                    case NO_NEW_MSG:
                        break;
                    case NO_MATCHED_MSG:
                        break;
                    case OFFSET_ILLEGAL:
                        break;
                    default:
                        break;
                }

                consumer.updateConsumeOffset(mq, pullResult.getNextBeginOffset());
                context.setPullNextDelayTimeMillis(100);
            } catch (Exception e) {
               e.printStackTrace();
            }
        });
    }
}
