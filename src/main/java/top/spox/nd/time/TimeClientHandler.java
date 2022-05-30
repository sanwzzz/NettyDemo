package top.spox.nd.time;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.Date;

/**
 * @author shn
 * @version 1.0
 * @description: TODO
 * @date 2022/5/27 14:36
 */
public class TimeClientHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf time = (ByteBuf) msg;
        long currentTime = (time.readUnsignedInt()-2208988800l)*1000L;
        System.out.println(new Date(currentTime));
        ctx.close();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
