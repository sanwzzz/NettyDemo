package top.spox.nd.discard;
import cn.hutool.core.util.StrUtil;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.ReferenceCountUtil;

/**
 * @author shn
 * @version 1.0
 * @description: TODO
 * @date 2022/5/27 11:04
 */
public class DiscardServerHandler extends ChannelInboundHandlerAdapter {



    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
//
//        ReferenceCountUtil.release(msg);

        ByteBuf in = (ByteBuf) msg;
        try {
            while (in.isReadable()){
                System.out.print((char) in.readByte());
            }

        }finally {
            ReferenceCountUtil.release(msg);
        }

        // 存在写入操作不用再次释放
//        ctx.write(msg);
//        ctx.flush();

    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
        cause.printStackTrace();
        ctx.close();
    }
}
