package my.netty.demo.diy.streaming.server;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioDatagramChannel;

public class StreamingServer {

    static final int PORT = Integer.parseInt(System.getProperty("port", "8083"));
    static final String HOSTNAME = System.getProperty("hostname", "127.0.0.1");

    public static void main(String[] args) throws Exception {
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap b = new Bootstrap();
            b.group(group)
                    .channel(NioDatagramChannel.class)
                    //.option(ChannelOption.SO_BROADCAST, true)
                    .handler(new StreamingServerHandler());

            b.bind(HOSTNAME, PORT).sync().channel().closeFuture().await();
        } finally {
            group.shutdownGracefully();
        }
    }

}
