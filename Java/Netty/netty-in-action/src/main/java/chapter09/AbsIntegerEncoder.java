package chapter09;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageEncoder;

import java.util.List;

/**
 * 扩展 MessageToMessageEncoder，将消息编码成另一种格式
 *
 * @author Angus
 * @date 2019/5/5
 */
public class AbsIntegerEncoder extends MessageToMessageEncoder<ByteBuf> {

    @Override
    protected void encode(ChannelHandlerContext ctx, ByteBuf msg, List<Object> out) throws Exception {
        // 检查是否有足够的字节用来编码
        while(msg.readableBytes() >= 4) {
            // 从输入的 ByteBuf 中读取下一个整数
            int value = Math.abs(msg.readInt());
            // 将该整数写入到编码消息的 List 中
            out.add(value);
        }
    }
}
