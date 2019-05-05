package chapter09;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.embedded.EmbeddedChannel;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author Angus
 * @date 2019/5/5
 */
public class FixedLengthFrameDecoderTest {

    @Test
    public void testFrameDecoded() {
        ByteBuf buf = Unpooled.buffer();
        for (int i = 0; i < 9; i++) {
            buf.writeByte(i);
        }
        ByteBuf input = buf.duplicate();
        // 创建一个 EmbeddedChannel，并添加一个 FixedLengthFrameDecoder，并将以 3 字节的帧长度被测试
        EmbeddedChannel channel = new EmbeddedChannel(new FixedLengthFrameDecoder(3));
        // 将数据写入 EmbeddedChannel
        assertTrue(channel.writeInbound(input.retain()));
        assertTrue(channel.finish());

        // 读取所生成的消息，并且验证是否有 3 帧（切片），其中每帧（切片）都为 3 字节
        ByteBuf read = channel.readInbound();
        assertEquals(buf.readSlice(3), read);
        read.release();

        read = channel.readInbound();
        assertEquals(buf.readSlice(3), read);
        read.release();

        read = channel.readInbound();
        assertEquals(buf.readSlice(3), read);
        read.release();

        assertNull(channel.readInbound());
        buf.release();
    }

    @Test
    public void testFrameDecoded2() {
        ByteBuf buf = Unpooled.buffer();
        for (int i = 0; i < 9; i++) {
            buf.writeByte(i);
        }
        ByteBuf input = buf.duplicate();
        // 创建一个 EmbeddedChannel，并添加一个 FixedLengthFrameDecoder，并将以 3 字节的帧长度被测试
        EmbeddedChannel channel = new EmbeddedChannel(new FixedLengthFrameDecoder(3));
        // 将数据写入 EmbeddedChannel
        assertFalse(channel.writeInbound(input.readBytes(2)));
        assertTrue(channel.writeInbound(input.readBytes(7)));
        assertTrue(channel.finish());

        // 读取所生成的消息，并且验证是否有 3 帧（切片），其中每帧（切片）都为 3 字节
        ByteBuf read = channel.readInbound();
        assertEquals(buf.readSlice(3), read);
        read.release();

        read = channel.readInbound();
        assertEquals(buf.readSlice(3), read);
        read.release();

        read = channel.readInbound();
        assertEquals(buf.readSlice(3), read);
        read.release();

        assertNull(channel.readInbound());
        buf.release();
    }

}