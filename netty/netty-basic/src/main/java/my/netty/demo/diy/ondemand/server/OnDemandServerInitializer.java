package my.netty.demo.diy.ondemand.server;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import my.netty.demo.diy.ondemand.common.MessageDecoder;
import my.netty.demo.diy.ondemand.common.MessageEncoder;

public class OnDemandServerInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    public void initChannel(SocketChannel ch) {
        ChannelPipeline pipeline = ch.pipeline();

        pipeline.addLast(new LengthFieldBasedFrameDecoder(Integer.MAX_VALUE, 2, 4, 0, 0));

        pipeline.addLast(new MessageEncoder());
        pipeline.addLast(new MessageDecoder());

        pipeline.addLast(new OnDemandServerHandler());

    }

}
