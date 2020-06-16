package com.mycompany.app;

public class Packet {
    private int packetID;

    Packet(int ID) {
        packetID = ID;
    }

    Packet(Packet p) {
        packetID = p.packetID;
    }

    public int getPacketID() {
        return packetID;
    }
}
