package my.netty.demo.tcpserver;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.net.InetSocketAddress;

/**
 * http://tutorials.jenkov.com/netty/netty-tcp-server.html
 */
public class TcpServer {

    public static void main(String[] args) throws InterruptedException {
        // NioEventLoopGroup是一个线程组，包含了一组NIO线程，专门用于网络事件的处理，实际上它们就是Reactor线程组
        EventLoopGroup group = new NioEventLoopGroup();

        try {
            //用于启动NIO服务端的辅助启动类，目的是降低服务端的开发复杂度
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            // 这里只是用一个线程组。也可以使用两个线程组，一个用于接受客户端的连接，另一个用于进行网络读写操作
            serverBootstrap.group(group);
            // channel方法用于指定服务端Channel的类型，Netty会通过工厂类利用反射创建相应的Channel实例
            serverBootstrap.channel(NioServerSocketChannel.class);
            serverBootstrap.localAddress(new InetSocketAddress("localhost", 8081));

            serverBootstrap.childHandler(new ChannelInitializer<SocketChannel>() {
                protected void initChannel(SocketChannel socketChannel) throws Exception {
                    socketChannel.pipeline().addLast(new HelloServerHandler());
                }
            });
            // 调用同步阻塞方法sync等待绑定操作完成，完成之后返回一个其功能类似于JDK的java.util.concurrent.Future的ChannelFuture，主要用于异步操作的通知回调
            ChannelFuture channelFuture = serverBootstrap.bind().sync();
            // 使用sync方法进行阻塞，等待服务端链路关闭之后main函数才退出
            channelFuture.channel().closeFuture().sync();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 优雅退出，释放线程池资源
            group.shutdownGracefully().sync();
        }
    }
}
