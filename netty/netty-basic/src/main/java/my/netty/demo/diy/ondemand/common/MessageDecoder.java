package my.netty.demo.diy.ondemand.common;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.io.UnsupportedEncodingException;
import java.util.List;

public class MessageDecoder extends ByteToMessageDecoder {

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) {
        Message msg = new Message();

        msg.setVersion(in.readByte());
        msg.setType(in.readByte());
        msg.setLength(in.readInt());

        byte[] payload = new byte[msg.getLength()];
        in.readBytes(payload);
        try {
            msg.setPayload(new String(payload, "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        out.add(msg);
    }
}
