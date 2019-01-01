package my.netty.demo.diy.client;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import my.netty.demo.diy.common.MessageDecoder;
import my.netty.demo.diy.common.MessageEncoder;

public class OnDemandClientInitializer extends ChannelInitializer<SocketChannel> {

    private String query = "0";

    public OnDemandClientInitializer() {}

    public OnDemandClientInitializer(String query) {
        this.query = query;
    }



    @Override
    public void initChannel(SocketChannel ch) {
        ChannelPipeline pipeline = ch.pipeline();

        pipeline.addLast(new LengthFieldBasedFrameDecoder(Integer.MAX_VALUE, 2, 4, 0, 0));

        pipeline.addLast(new MessageEncoder());
        pipeline.addLast(new MessageDecoder());

        pipeline.addLast(new OnDemandClientHandler(query));
    }

}
