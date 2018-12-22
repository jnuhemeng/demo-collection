package my.netty.demo.usage;

import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

public class ChannelUsage {

    public static void main(String[] args) {
        NioSocketChannel nioSocketChannel = new NioSocketChannel();
        NioServerSocketChannel nioServerSocketChannel = new NioServerSocketChannel();

    }

}
