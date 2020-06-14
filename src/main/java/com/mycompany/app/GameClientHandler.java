package com.mycompany.app;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * Handles a client-side channel.
 */
public class GameClientHandler extends SimpleChannelInboundHandler<TestClass> {
    private static TestClass response = null;

    public TestClass getResponse() {
        synchronized (this) {
            if (response != null) {
                TestClass test = new TestClass(response);
                response = null;
                return test;
            }
            return null;
        }
    }
    @Override
    public void channelActive(final ChannelHandlerContext ctx) {
        System.out.println("You joined " + ctx.channel().remoteAddress() + " server.");
    }

    @Override
    public void channelRead0(ChannelHandlerContext ctx, TestClass msg) throws Exception {
        System.err.print(ctx.channel().remoteAddress() + " ");
        msg.print();
        response = msg;
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }
}
