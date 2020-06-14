package com.mycompany.app;

import io.netty.channel.Channel;

public class PacketConnect extends Packet {
    private Channel c;
    private String msg;

    PacketConnect(String msg) {
        super(1);
        this.msg = msg;
    }

    PacketConnect(PacketConnect p) {
        super(p);
        this.msg = p.msg;
    }

    public void print() {
        System.out.println(msg);
    }

    public String getData() {
        return  msg;
    }
}
