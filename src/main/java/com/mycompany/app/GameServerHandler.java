package com.mycompany.app;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;


public class GameServerHandler extends SimpleChannelInboundHandler<TestClass> {

    static final ChannelGroup channels = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    @Override
    public void channelActive(final ChannelHandlerContext ctx) {
        System.out.println("User " + ctx.channel().remoteAddress() + " joined.");
        channels.add(ctx.channel());
    }

    @Override
    public void channelRead0(ChannelHandlerContext ctx, TestClass msg) throws Exception {
        String[] arr = new String[10];
        int i = 0;
        for(Channel c: channels) {
            arr[i++] = String.valueOf(c.remoteAddress());
        }
        msg.setChannels(arr);

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