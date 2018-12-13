package my.netty.demo.tcpserver;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

/**
 * http://tutorials.jenkov.com/netty/netty-tcp-server.html
 */
public class HelloServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf inBuffer = (ByteBuf) msg;

        String received = inBuffer.toString(CharsetUtil.UTF_8);
        System.out.println("Server received: " + received);

        // write方法把待发送的消息放到发送缓冲数组中，此时还没有直接将消息写入SocketChannel（这样可以防止频繁地唤醒Selector进行消息发送）
        ctx.write(Unpooled.copiedBuffer("Hello " + received, CharsetUtil.UTF_8));
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        // ChannelHandlerContext的flush方法的作用是将消息发送队列中的消息写到SocketChannel中发送给对方
        ctx.writeAndFlush(Unpooled.EMPTY_BUFFER) // writeAndFlush方法是write和flush方法的快捷方式
                .addListener(ChannelFutureListener.CLOSE); //所指定的Listner在Future完成的时候会被通知，ChannelFutureListener.CLOSE的作用是关闭ChannelFuture
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
