package com.mycompany.app;

import java.io.Serializable;

public class Packet1 implements Packet, Serializable {
    private int ID;
    private int number;
    private Player player;

    public Packet1() {
        ID = 1;
        number = -1;
    }

    public Packet1(int id, int n, Player p) {
        ID = id;
        number = n;
        player = p;
    }

    @Override
    public void setNum(int n) {
        this.number = n;
    }

    @Override
    public int getNum() {
        return number;
    }

    @Override
    public Board getBoard() {
        return null;
    }

    @Override
    public Player getPlayer() {
        return player;
    }

    @Override
    public Packet1 cloneDeep() {
        return new Packet1(this.ID, this.number, this.player);
    }

    @Override
    public int getID() {
        return ID;
    }

}
