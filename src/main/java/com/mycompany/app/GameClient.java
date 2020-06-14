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
    private static String data = null;
    private static TestClass odp;
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
                    if (data != null) {
                        if (data.equals("bye")) break;

                        TestClass test = new TestClass(0, data);
                        test.print();

                        // Sends the received line to the server.
                        lastWriteFuture = ch.writeAndFlush(test);
                        data = null;
                    }
                }
                TestClass response = handler.getResponse();
                synchronized(this) {
                    if (response != null) {
                        System.err.print("Client response: ");
                        response.print();
                        js.setText(response);
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

    public void writeMessage(String text) {
        data = text;
    }

    public String getMessage() {
        if (odp != null)
            return odp.getData();
        return null;
    }
}
