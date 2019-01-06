package my.netty.demo.diy.ondemand.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

public class OnDemandClient {

    static final String HOST = System.getProperty("host", "127.0.0.1");
    static final int PORT = Integer.parseInt(System.getProperty("port", "8082"));

    static final String TEST_QUERY = "1";

    public static void main(String[] args) throws Exception {
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap b = new Bootstrap();
            b.group(group)
                    .channel(NioSocketChannel.class)
                    .handler(new OnDemandClientInitializer(TEST_QUERY));

            b.connect(HOST, PORT).sync();
        } finally {
            group.shutdownGracefully();
        }

    }

}
