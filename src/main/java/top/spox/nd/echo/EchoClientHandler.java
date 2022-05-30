package top.spox.nd.echo;

import cn.hutool.core.util.StrUtil;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * @author shn
 * @version 1.0
 * @description: TODO
 * @date 2022/5/27 10:22
 */
public class EchoClientHandler extends ChannelInboundHandlerAdapter {

    private final ByteBuf byteBuf;

    public EchoClientHandler(){
        byteBuf = Unpooled.buffer(EchoClient.SIZE);
        for (int i = 0; i < byteBuf.capacity(); i++) {
            byteBuf.writeByte((byte) i );
        }
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println(StrUtil.format("send byteBuf:{}",byteBuf));
        ctx.writeAndFlush(byteBuf);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ctx.write(msg);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
