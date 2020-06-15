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
    private Packet msg = null;
    private Packet rsp = null;
    boolean turn;
    boolean gameStart = false;
    int mode;
    String color;
    String nickname;

    GameClient(int mode, String color, String nickname) {
        this.mode = mode;
        this.color = color;
        this.nickname = nickname;
        this.turn = color.equals("white");
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

            ChannelFuture lastWriteFuture = null;

            // Waiting for other player to join
            WaitingScreen ws = new WaitingScreen();
            msg = new Packet1();

            System.err.println(color);
            while(!gameStart) {
                lastWriteFuture = ch.writeAndFlush(msg);
                Thread.sleep(1000);
                for (; ; ) {
                    // waiting for server response that two players are online
                    Packet p = handler.getResponse();
                    synchronized (this) {
                        if (p != null) {
                            rsp = p.cloneDeep();
                            break;
                        }
                    }
                }
                if (rsp.getID() == 1 && rsp.getNum() == 1 && color.equals("black")) {
                    System.err.println("No active game");
                    break;
                }

                if (rsp.getID() == 1 && rsp.getNum() == 2)
                    gameStart = true;
            }
            ws.dispose();

            GUI g;
            if (gameStart) {
                // if two players are online, start the game
                g = new GUI(new Player(mode, color, nickname));
            }
            else {
                return;
            }

            Packet msg = new Packet1(0, -1);
            // Moves
            for (;;) {
                synchronized(this) {
                    if (msg != null) {
                        lastWriteFuture = ch.writeAndFlush(msg);
                        msg = null;
                    }
                }
                Packet p = handler.getResponse();
                synchronized(this) {
                    if (p != null) {
                        if (p.getID() == -1) break;
                        rsp = p.cloneDeep();
                        p = null;
                        g.setBoard(rsp.getBoard());
                        g.setMove(true);
                        while(g.getMove() == true);
                        if(g.getPawnCount() == 0) // koniec gry
                            msg = new Packet1(-1, -1);
                        else
                            msg = new Packet2(2, g.getBoard());
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

    public void assignChannel() {
        msg = new Packet1();
    }
}