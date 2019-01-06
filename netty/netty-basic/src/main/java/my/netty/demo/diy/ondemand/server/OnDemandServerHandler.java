package my.netty.demo.diy.ondemand.server;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import my.netty.demo.diy.ondemand.common.Message;

import java.util.*;

public class OnDemandServerHandler extends SimpleChannelInboundHandler<Message> {

    private static final byte VERSION = 1;
    private static final byte RSP_TYPE = 1;

    private static Map<Integer,ServiceProvider> providerMap = new HashMap<>();

    static {
        providerMap.put(1, new HelloServiceProvider());
        providerMap.put(2, new DateServiceProvider());
    }



    @Override
    public void channelRead0(ChannelHandlerContext ctx, Message req) throws Exception {
        System.err.println("Incoming request: query=" + req.getPayload());

        if(!validate(req)) {
            return;
        }
        List<Integer> query = parseQuery(req);

        List<String> data = new ArrayList<>();
        Set<Integer> providerKeys = providerMap.keySet();
        for(Integer code : query) {
            if(providerKeys.contains(code)) {
                data.add(providerMap.get(code).provide());
            }
        }

        Message rsp = new Message();
        rsp.setVersion(VERSION);
        rsp.setType(RSP_TYPE);
        rsp.setPayload(data.toString());

        ctx.writeAndFlush(rsp);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) {
        ctx.writeAndFlush(Unpooled.EMPTY_BUFFER)
                .addListener(ChannelFutureListener.CLOSE);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }

    private boolean validate(Message req) {
        //TODO
        return true;
    }

    private List<Integer> parseQuery(Message req) {
        //TODO more stable
        List<Integer> query = new ArrayList<>();
        for(String value : req.getPayload().split(",")) {
            query.add(Integer.valueOf(value));
        }
        return query;
    }

}
