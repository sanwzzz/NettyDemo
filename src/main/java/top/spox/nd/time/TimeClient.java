package top.spox.nd.time;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * @author shn
 * @version 1.0
 * @description: TODO
 * @date 2022/5/27 14:36
 */
public class TimeClient {


    public static void main(String[] args) throws InterruptedException {
        new TimeClient("localhost",18888).run();
    }

    private final String host;
    private final int port;

    public TimeClient(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public void run() throws InterruptedException {

        NioEventLoopGroup workGroup = new NioEventLoopGroup();

        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(workGroup);
            bootstrap.channel(NioSocketChannel.class);
            bootstrap.option(ChannelOption.SO_KEEPALIVE, true);
            bootstrap.handler(new ChannelInitializer<SocketChannel>() {
                protected void initChannel(SocketChannel ch) throws Exception {
                    ch.pipeline().addLast(new TimeClientHandler());
                }
            });
            System.out.println(1);
            ChannelFuture channelFuture = bootstrap.connect(host, port).sync();
            System.out.println(2);
            channelFuture.channel().closeFuture().sync();
            System.out.println(3);
        }finally {
            workGroup.shutdownGracefully();
        }
        System.out.println(4);

    }
}
