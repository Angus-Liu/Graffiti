package chapter09;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import io.netty.handler.codec.TooLongFrameException;

import java.util.List;

/**
 * @author Angus
 * @date 2019/5/5
 */
public class FrameChunkDecoder extends ByteToMessageDecoder {

    private final int maxFrameSize;

    public FrameChunkDecoder(int maxFrameSize) {
        this.maxFrameSize = maxFrameSize;
    }

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        int readableBytes = in.readableBytes();
        if (readableBytes > maxFrameSize) {
            in.clear();
            // 如果该帧太大，则丢弃，同时抛异常
            throw new TooLongFrameException();
        }
        // 从 ByteBuf 中读取一个新帧
        ByteBuf buf = in.readBytes(readableBytes);
        // 将该帧添加到解码消息的 List 中
        out.add(buf);
    }
}
