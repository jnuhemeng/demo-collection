package my.netty.demo;

import com.alibaba.dubbo.rpc.RpcContext;
import io.netty.buffer.ByteBuf;

import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.util.concurrent.Future;

public class HelloWorld {

    public static void main(String[] args) {
        Future<Object> fooFuture = RpcContext.getContext().getFuture();
        ByteBuffer byteBuffer;
        ByteBuf byteBuf;
    }
}
