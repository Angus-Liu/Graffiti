package chapter10.encoder;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * @author Angus
 * @date 2019/5/5
 */
public class ShortToByteEncoder extends MessageToByteEncoder<Short> {
    @Override
    protected void encode(ChannelHandlerContext ctx, Short msg, ByteBuf out) throws Exception {
        // 将 Short 写入 ByteBuf 中
        out.writeShort(msg);
    }
}
