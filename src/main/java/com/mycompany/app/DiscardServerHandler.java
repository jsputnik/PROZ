package com.mycompany.app;


import io.netty.buffer.ByteBuf;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.ReferenceCountUtil;

/**
 * Handles a server-side channel.
 */
public class DiscardServerHandler extends ChannelInboundHandlerAdapter { // (1)

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        ByteBuf in = (ByteBuf) msg;
        try {
            String data = (in.toString(io.netty.util.CharsetUtil.US_ASCII));
            for (int i = 0; i < data.length(); i++){
                char c = data.charAt(i);
                System.out.println((int)c);
            }
        } finally {
            ReferenceCountUtil.release(msg); // (2)
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) { // (4)
        // Close the connection when an exception is raised.
        cause.printStackTrace();
        ctx.close();
    }
}
