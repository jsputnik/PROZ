package com.mycompany.app;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;
import io.netty.handler.ssl.SslContext;

/**
 * Creates a newly configured {@link ChannelPipeline} for a new channel.
 */
public class GameClientInitializer extends ChannelInitializer<SocketChannel> {

    public GameClientInitializer() {

    }

    @Override
    public void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();

        // Add SSL handler first to encrypt and decrypt everything.
        // In this example, we use a bogus certificate in the server side
        // and accept any invalid certificates in the client side.
        // You will need something more complicated to identify both
        // and server in the real world.
//        pipeline.addLast(sslCtx.newHandler(ch.alloc(), GameClient.HOST, GameClient.PORT));

        // On top of the SSL handler, add the text line codec.
        pipeline.addLast(new ObjectDecoder(100000, ClassResolvers.cacheDisabled(null)));
        pipeline.addLast(new ObjectEncoder());

        // and then business logic.
        pipeline.addLast(new GameClientHandler());
    }
}