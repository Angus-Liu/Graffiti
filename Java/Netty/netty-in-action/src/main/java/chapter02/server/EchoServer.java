package chapter02.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.net.InetSocketAddress;

/**
 * @author Angus
 * @date 2019/5/3
 */
public class EchoServer {

    private final int PORT = 8888;

    public static void main(String[] args) throws Exception {
        new EchoServer().start();
    }

    public void start() throws Exception {
        final EchoServerHandler serverHandler = new EchoServerHandler();
        // 创建 EventLoopGroup
        EventLoopGroup group = new NioEventLoopGroup();
        // 创建 ServerBootstrap
        ServerBootstrap bootstrap = new ServerBootstrap();
        bootstrap.group(group)
                // 指定所使用的 NIO 传输 Channel
                .channel(NioServerSocketChannel.class)
                // 使用指定的端口设置套接字
                .localAddress(new InetSocketAddress(PORT))
                // 添加一个 EchoServerHandler 到子 Channel 的 ChannelPipeline
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        // EchoServerHandler 被标注为 @Shareable，所以可以总是使用同样的实例
                        ch.pipeline().addLast(serverHandler);
                    }
                });
        try {
            // 异步绑定服务器，调用 sync() 方法知等待直到绑定完成
            ChannelFuture future = bootstrap.bind().sync();
            // 获取 Channel 的 CloseFuture，并且阻塞当前线程直到它完成
            future.channel().closeFuture().sync();
        } finally {
            // 关闭 EventLoopGroup，释放所有资源
            group.shutdownGracefully().sync();
        }

    }
}
