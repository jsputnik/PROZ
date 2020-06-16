package com.mycompany.app;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;


public final class GameClient implements Runnable {

    static final String HOST = System.getProperty("host", "127.0.0.1");
    static final int PORT = Integer.parseInt(System.getProperty("port", "8992"));
    private static Packet packet = null;
    private static Packet respond = null;
    private static JoinServer js;

    GameClient(JoinServer jsinstance) {
        js = jsinstance;
    }

    public void run() {
               EventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap b = new Bootstrap();
            b.group(group)
                    .channel(NioSocketChannel.class)
                    .handler(new GameClientInitializer());

            // Start the connection attempt.
            ChannelFuture f = b.connect(HOST, PORT).sync();
            Channel ch = f.channel();

            GameClientHandler handler =
                    (GameClientHandler) f.channel().pipeline().last();

            // Read commands from the stdin.
            ChannelFuture lastWriteFuture = null;
            for (;;) {
                synchronized(this) {
                    if (packet != null) {
                        if (packet.getPacketID() == -1) break;

                        if (packet.getPacketID() == 1) {
                            ((PacketConnect) packet).print();
                        }
                        // Sends the received line to the server.
                        lastWriteFuture = ch.writeAndFlush(packet);
                        packet = null;
                    }
                }
                Packet response = handler.getResponse();
                synchronized(this) {
                    if (response != null) {
                        System.err.print("Client response: ");
                        js.setText(((PacketConnect)response).getData());
                    }
                }
            }

            // Wait until all messages are flushed before closing the channel.
            if (lastWriteFuture != null) {
                lastWriteFuture.sync();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            // The connection is closed automatically on shutdown.
            group.shutdownGracefully();
        }
    }

    public void getResponse(Packet p) {
        packet = p;
    }
}
