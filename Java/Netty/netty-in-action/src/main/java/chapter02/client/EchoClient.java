package chapter02.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.net.InetSocketAddress;

/**
 * @author Angus
 * @date 2019/5/3
 */
public class EchoClient {

    private final String HOST = "127.0.0.1";
    private final int PORT = 8888;

    private void start() throws Exception {
        // 指定 EventLoopGroup，进行事件处理
        EventLoopGroup group = new NioEventLoopGroup();
        // 初始化客户端
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(group)
                .channel(NioSocketChannel.class)
                .remoteAddress(new InetSocketAddress(HOST, PORT))
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        // 连接被建立时，一个 EchoClientHandler 实例会被安装到 ChannelPipeline 上
                        ch.pipeline().addLast(new EchoClientHandler());
                    }
                });

        try {
            // 连接到远程节点，阻塞等待直到连接完成
            ChannelFuture future = bootstrap.connect().sync();
            future.channel().closeFuture().sync();
        } finally {
            // 关闭 group，释放所有资源
            group.shutdownGracefully().sync();
        }
    }

    public static void main(String[] args) throws Exception {
        new EchoClient().start();
    }
}
