package com.mycompany.app;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * Handles a client-side channel.
 */
public class GameClientHandler extends SimpleChannelInboundHandler<Packet> {
    private static Packet response = null;

    public Packet getResponse() {
        synchronized (this) {
            if (response != null) {
                System.err.println("Packet sent!");
                Packet test = new Packet(response);
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
    public void channelRead0(ChannelHandlerContext ctx, Packet msg) throws Exception {
        System.err.print(ctx.channel().remoteAddress() + " ");
        ((PacketConnect)msg).print();
        response = msg;
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }
}
