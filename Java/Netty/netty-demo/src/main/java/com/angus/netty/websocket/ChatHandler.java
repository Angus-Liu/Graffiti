package com.angus.netty.websocket;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.util.concurrent.GlobalEventExecutor;

import java.time.LocalDateTime;

/**
 * 自定义处理消息的 Handler
 * TextWebSocketFrame 在 netty 中专门用于为 websocket 处理文本的对象，frame 是消息的载体
 *
 * @author Angus
 * @date 2018/12/13
 */
public class ChatHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {

    /**
     * 用于记录和管理所有客户端的 channel
     */
    private static ChannelGroup clients = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame msg) throws Exception {
        Channel client = ctx.channel();
        // 获取客户端传输过来的消息
        String content = msg.text();
        System.out.println("客户端 [" + client.remoteAddress() + "] 发送的消息：" + content);
        // 将消息发送给所有客户端
        clients.writeAndFlush(
                new TextWebSocketFrame(LocalDateTime.now() + " 客户端 [" + client.remoteAddress() + "] 发送的消息：" + content));
    }

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        // 当客户端连接服务端之后，获取客户端的 channel，放到 ChannelGroup 中进行管理
        clients.add(ctx.channel());
        System.out.println("客户端连接，long id = " + ctx.channel().id().asLongText());
        System.out.println("客户端连接：short id = " + ctx.channel().id().asShortText());
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        // 当触发 handlerRemoved 方法时，从 ChannelGroup 移除对应的客户端的 channel
        clients.remove(ctx.channel());
        System.out.println("客户端断开，long id = " + ctx.channel().id().asLongText());
        System.out.println("客户端断开：short id = " + ctx.channel().id().asShortText());
    }
}
