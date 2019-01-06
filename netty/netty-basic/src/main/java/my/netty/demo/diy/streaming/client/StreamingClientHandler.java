package my.netty.demo.diy.streaming.client;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.socket.DatagramPacket;

import java.util.PriorityQueue;

public class StreamingClientHandler extends SimpleChannelInboundHandler<DatagramPacket> {

    private PriorityQueue<DatagramPacketWrapper> bufQueue = new PriorityQueue<>();

    @Override
    public void channelRead0(ChannelHandlerContext ctx, DatagramPacket msg) throws Exception {

    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }

}
