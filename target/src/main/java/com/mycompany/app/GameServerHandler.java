package com.mycompany.app;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;


public class GameServerHandler extends SimpleChannelInboundHandler<Packet> {

    static final ChannelGroup channels = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    @Override
    public void channelActive(final ChannelHandlerContext ctx) {
        System.out.println("User " + ctx.channel().remoteAddress() + " joined.");
        channels.add(ctx.channel());
    }

    @Override
    public void channelRead0(ChannelHandlerContext ctx, Packet msg) throws Exception {
        System.err.print("Message recieved: ");
        ((PacketConnect)msg).print();

        for (Channel c: channels) {
//            System.err.println(c.remoteAddress());
            if (c != ctx.channel()) {
                c.writeAndFlush(msg);
            }
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }
}
