package my.netty.usage;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

import java.util.Random;

public class ByteBufUsage {
    public static void main(String[] args) {
        /*//Creates a new big-endian Java heap buffer with the specified capacity,
        // which expands its capacity boundlessly on demand.
        ByteBuf heapBuffer = Unpooled.buffer(128);
        for (int i = 0; i < heapBuffer.capacity(); i ++) {
            byte b = heapBuffer.getByte(i);
            //System.out.println((char) b);
            System.out.println(b);
        }*/

        //reates a new big-endian Java heap buffer with the specified initialCapacity,
        // that may grow up to maxCapacity The new buffer's readerIndex and writerIndex are 0
        ByteBuf heapBuffer = Unpooled.buffer(16, 32);
        Random random = new Random();
        while (heapBuffer.maxWritableBytes() >= 4) {
            heapBuffer.writeInt(random.nextInt());
        }

        //Output: readableBytes(this.writerIndex - this.readerIndex)=32
        System.out.println("readableBytes(this.writerIndex - this.readerIndex)=" + heapBuffer.readableBytes());

        while (heapBuffer.isReadable()) {
            System.out.println(heapBuffer.readByte());
        }

    }
}
