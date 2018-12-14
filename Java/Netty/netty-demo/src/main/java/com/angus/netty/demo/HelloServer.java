package com.angus.netty.demo;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * 实现客户端发送一个请求，服务器返回“Hello Netty”
 *
 * @author Angus
 * @date 2018/12/13
 */
public class HelloServer {

    public static void main(String[] args) throws Exception {
        // 主线程组，用于接受客户端的连接
        EventLoopGroup parentGroup = new NioEventLoopGroup();
        // 从线程组，用于任务执行
        EventLoopGroup childGroup = new NioEventLoopGroup();
        try {
            // ServerBootstrap 是一个启动类，用于 netty 服务器的创建
            ServerBootstrap serverBootstrap = new ServerBootstrap()
                    // 设置主从线程组
                    .group(parentGroup, childGroup)
                    // 设置 nio 双向通道
                    .channel(NioServerSocketChannel.class)
                    // 子处理器，用于处理 childGroup
                    .childHandler(new HelloServerInitializer());

            // 启动 server，设置端口号为 8088，方式为同步
            ChannelFuture channelFuture = serverBootstrap.bind(8088).sync();
            // 监听关闭的 channel，方式为同步
            channelFuture.channel().closeFuture().sync();
        } finally {
            // 优雅地关闭线程组
            parentGroup.shutdownGracefully();
            childGroup.shutdownGracefully();
        }
    }

}
