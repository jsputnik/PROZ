package com.mycompany.app;

import java.io.Serializable;

public class Packet2 implements Packet, Serializable {
    private int ID;
    private Board board;

    public Packet2(int id, Board b) {
        ID = id;
        board = b;
    }

    @Override
    public void setNum(int n) {
    }

    @Override
    public int getNum() {
        return 0;
    }

    @Override
    public Board getBoard() {
        return board;
    }

    @Override
    public Packet2 cloneDeep() {
        return new Packet2(this.ID, this.board);
    }

    @Override
    public int getID() {
        return ID;
    }
}
