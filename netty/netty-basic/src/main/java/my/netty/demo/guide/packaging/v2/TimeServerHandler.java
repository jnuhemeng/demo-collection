package my.netty.demo.guide.packaging.v2;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.Date;

public class TimeServerHandler extends ChannelInboundHandlerAdapter {

    private int counter;

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        String body = (String) msg;

        System.out.println("The time server receive order : " + body + "; the counter is :" + ++counter);

        String currentTime = "QUERY TIME ORDER".equalsIgnoreCase(body) ? new Date(System.currentTimeMillis()).toString() : "BAD ORDER";
        //currentTime = currentTime + System.getProperty("line.separator");
        currentTime = currentTime + "\r\n";
        ByteBuf resp = Unpooled.copiedBuffer(currentTime.getBytes());

        ctx.writeAndFlush(resp);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        ctx.close();
    }

    public static void main(String[] args) {
        System.out.println(System.getProperty("line.separator"));
    }
}