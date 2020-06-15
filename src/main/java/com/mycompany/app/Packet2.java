package com.mycompany.app;

import java.io.Serializable;

public class Packet2 implements Packet, Serializable {
    private int ID;
    private String data;
    private String[] channels;

    public Packet2(int id, String d) {
        ID = id;
        data = d;
    }

    public Packet2(int id, String d, String[] g) {
        ID = id;
        data = d;
        channels = g;
    }

    @Override
    public void setNum(int n) {
    }

    @Override
    public int getNum() {
        return 0;
    }

    public void setChannels(String[] c) {
        channels = c;
    }

    @Override
    public Packet2 cloneDeep() {
        return new Packet2(this.ID, this.data, this.channels);
    }

    @Override
    public void print() {
        System.out.println(data);
    }

    @Override
    public String getData() {
        return data;
    }

    @Override
    public int getID() {
        return ID;
    }

    @Override
    public void printNum() {

    }

    public String[] getChannels() {
        return channels;
    }
}
