package my.netty.demo.diy.ondemand.client;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class OnDemandTestHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, java.lang.Object msg) {
        ByteBuf buf = (ByteBuf) msg;

        while(buf.isReadable()) {
            System.err.print(buf.readByte() + " ");
        }

        buf.release();
    }
}
