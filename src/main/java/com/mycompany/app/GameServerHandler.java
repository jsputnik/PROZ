package com.mycompany.app;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;


public class GameServerHandler extends SimpleChannelInboundHandler<Packet> {

    static final ChannelGroup channels = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
    Player p1 = null;
    Player p2 = null;

    private int channelCount(ChannelGroup cg) {
        int i = 0;
        for (Channel c : cg) i++;
        return i;
    }

    @Override
    public void channelActive(final ChannelHandlerContext ctx) {
        System.out.println("User " + ctx.channel().remoteAddress() + " joined.");
        channels.add(ctx.channel());
    }

    @Override
    public void channelRead0(ChannelHandlerContext ctx, Packet msg) throws Exception {
        System.err.println("Message recieved");
        if (msg.getID() == 1) {
            msg.setNum(channelCount(channels));

            for (Channel c: channels) {
                if (c == ctx.channel()) {
                    c.writeAndFlush(msg).sync();
                }
            }
            return;
        }
        if (msg.getID() == 2) {
            for (Channel c: channels) {
                if (c != ctx.channel()) {
                    c.writeAndFlush(msg).sync();
                }
            }
            return;
        }

        for (Channel c: channels) {
            if (c != ctx.channel()) {
                c.writeAndFlush(msg).sync();
            }
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }
}