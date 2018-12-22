package my.netty.demo.guide.packaging.v1;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class TimeClientHandler extends ChannelInboundHandlerAdapter {

    private int counter;
    private byte[] req;

    public TimeClientHandler() {
        req = ("QUERY TIME ORDER" + System.getProperty("line.separator")).getBytes();
    }

    public void channelActive(ChannelHandlerContext ctx) {
        ByteBuf message = null;
        for(int i = 0; i < 100; i++) {
            message = Unpooled.buffer(req.length);
            message.writeBytes(req);
            ctx.writeAndFlush(message);
        }
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf buf = (ByteBuf)msg;

        byte[] rsp = new byte[buf.readableBytes()];
        buf.readBytes(rsp);

        String body = new String(rsp, "UTF-8");
        System.out.println("Now is: " + body + "; the counter is : " + ++counter);
    }

    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }

}
