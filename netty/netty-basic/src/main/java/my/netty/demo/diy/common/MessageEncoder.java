package my.netty.demo.diy.common;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

import java.io.UnsupportedEncodingException;

public class MessageEncoder extends MessageToByteEncoder<Message> {

    @Override
    protected void encode(ChannelHandlerContext ctx, Message msg, ByteBuf out) {
        out.writeByte(msg.getVersion());
        out.writeByte(msg.getType());

        byte[] payload = null;
        try {
            payload = msg.getPayload().getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        if(payload == null) {
            payload = new byte[1];
        }

        int length = payload.length;
        out.writeInt(length);
        out.writeBytes(payload);
    }
}
