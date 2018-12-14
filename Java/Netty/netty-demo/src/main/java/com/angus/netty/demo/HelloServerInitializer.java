package com.angus.netty.demo;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpServerCodec;

/**
 * 初始化器，channel 注册后，会执行里面相应的初始化方法
 *
 * @author Angus
 * @date 2018/12/13
 */
public class HelloServerInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel channel) throws Exception {
        // 通过 SocketChannel 获得相应的管道
        ChannelPipeline pipeline = channel.pipeline();
        // 通过管道，添加 Handler
        // HttpServeCodec 为 Netty 提供的助手类。当请求到服务端时，进行解码，响应给客户端时，进行编码
        pipeline.addLast("HttpServerCodec",new HttpServerCodec());
        // 添加自定义 Handler，返回“Hello Netty”
        pipeline.addLast("CustomHandler", new CustomHandler());
    }

}
