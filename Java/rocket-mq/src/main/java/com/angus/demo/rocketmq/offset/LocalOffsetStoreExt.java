package com.angus.demo.rocketmq.offset;

import org.apache.rocketmq.client.consumer.store.OffsetSerializeWrapper;
import org.apache.rocketmq.common.MixAll;
import org.apache.rocketmq.common.message.MessageQueue;

import java.io.IOException;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicLong;

/**
 * 自定义持久存储 OffsetStore
 */
public class LocalOffsetStoreExt {
    private final String groupName;
    private final String storePath;
    private ConcurrentMap<MessageQueue, AtomicLong> offsetTable = new ConcurrentHashMap<>();

    public LocalOffsetStoreExt(String groupName, String storePath) {
        this.groupName = groupName;
        this.storePath = storePath;
    }

    public void load() {
        OffsetSerializeWrapper offsetSerializeWrapper = this.readLocalOffset();
        if (offsetSerializeWrapper != null && offsetSerializeWrapper.getOffsetTable() != null) {
            ConcurrentMap<MessageQueue, AtomicLong> oT = offsetSerializeWrapper.getOffsetTable();
            offsetTable.putAll(oT);
            for (MessageQueue mq : oT.keySet()) {
                AtomicLong offset = oT.get(mq);
                System.out.printf("Load consumer's offset: %s, %s, %s\n", this.groupName, mq, offset.get());
            }
        }
    }

    public void updateOffset(MessageQueue mq, long offset) {
        if (mq != null) {
            AtomicLong offsetOld = offsetTable.get(mq);
            if (offsetOld == null) {
                offsetTable.putIfAbsent(mq, new AtomicLong(offset));
            } else {
                offsetOld.set(offset);
            }
        }
    }

    public long readOffset(final MessageQueue mq) {
        if (mq != null) {
            AtomicLong offset = offsetTable.get(mq);
            if (offset != null) {
                return offset.get();
            }
        }
        return 0;
    }

    public void persistAll(Set<MessageQueue> mqs) {
        if (mqs == null || mqs.isEmpty()) return;
        OffsetSerializeWrapper offsetSerializeWrapper = new OffsetSerializeWrapper();
        offsetTable.forEach((mq, offset) -> {
            if (mqs.contains(mq)) {
                offsetSerializeWrapper.getOffsetTable().put(mq, offset);
            }
        });
        String jsonString = offsetSerializeWrapper.toJson(true);
        if (jsonString != null) {
            try {
                MixAll.string2File(jsonString, storePath);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private OffsetSerializeWrapper readLocalOffset() {
        String content = null;
        try {
            content = MixAll.file2String(this.storePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (content == null || content.length() == 0) return null;
        return OffsetSerializeWrapper.fromJson(content, OffsetSerializeWrapper.class);
    }
}
