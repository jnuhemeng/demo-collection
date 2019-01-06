package my.netty.demo.diy.streaming.server;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.socket.DatagramPacket;

import java.io.FileInputStream;
import java.net.InetSocketAddress;

import static my.netty.demo.diy.streaming.server.StreamingServer.HOSTNAME;
import static my.netty.demo.diy.streaming.server.StreamingServer.PORT;

public class StreamingServerHandler extends SimpleChannelInboundHandler<DatagramPacket> {

    private final static String streamFilePath = "/etc/services";
    private final static int BUFF_SIZE = 1024;

    private FileInputStream fileInputStream;

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        fileInputStream = new FileInputStream(streamFilePath);
    }

    @Override
    public void channelRead0(ChannelHandlerContext ctx, DatagramPacket packet) throws Exception {
        System.err.println(packet);

        boolean lastPacketHasBeenSent = false;
        for(;;) {
            byte[] data = next();

            sendPacket(ctx, data, packet.sender());

            if(data.length < BUFF_SIZE) {
                System.err.println("Transmit finished");
                break;
            }
        }

    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) {
        ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        // We don't close the channel because we can keep serving requests.
    }


    private byte[] next() throws Exception {
        byte[] buf = new byte[BUFF_SIZE];
        int totalRead = 0, read = 0;

        while((read = fileInputStream.read(buf, totalRead, BUFF_SIZE - totalRead)) != -1) {
            totalRead += read;
            if(totalRead == BUFF_SIZE) {
                break;
            }
        }

        return buf;
    }

    private void sendPacket(ChannelHandlerContext ctx, byte[] data, InetSocketAddress recipient) {
        ByteBuf byteBuf = Unpooled.directBuffer();
        byteBuf.writeLong(data.length);
        byteBuf.writeBytes(data);

        DatagramPacket packet = new DatagramPacket(byteBuf, recipient, new InetSocketAddress(HOSTNAME, PORT));

        ctx.writeAndFlush(packet);
    }

}
