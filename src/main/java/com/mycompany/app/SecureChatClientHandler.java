package com.mycompany.app;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * Handles a client-side channel.
 */
public class SecureChatClientHandler extends SimpleChannelInboundHandler<TestClass> {

    @Override
    public void channelRead0(ChannelHandlerContext ctx, TestClass msg) throws Exception {
        msg.print();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }
}
