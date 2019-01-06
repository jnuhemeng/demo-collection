package my.netty.demo.diy.ondemand.client;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import my.netty.demo.diy.ondemand.common.Message;

public class OnDemandClientHandler extends SimpleChannelInboundHandler<Message> {

    private static final byte VERSION = 1;
    private static final byte REQ_TYPE = 0;

    private String query = "0";

    public OnDemandClientHandler() {}

    public OnDemandClientHandler(String query) {
        this.query = query;
    }


    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        Message req = new Message();
        req.setVersion(VERSION);
        req.setType(REQ_TYPE);
        req.setPayload(query);

        ctx.writeAndFlush(req);
    }


    @Override
    public void channelRead0(ChannelHandlerContext ctx, Message rsp) throws InterruptedException {
        System.err.println("Response from server: " + rsp);
        //ctx.channel().close().sync();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }

}
