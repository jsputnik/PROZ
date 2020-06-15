package com.mycompany.app;

import java.io.Serializable;

public class Packet1 implements Packet, Serializable {
    private int number;
    private int ID;
    private String data;

    public Packet1() {
        ID = 1;
        number = -1;
    }

    public Packet1(int id, String d, int n) {
        ID = 1;
        data = d;
        number = n;
    }

    public void printNum() {
        System.out.println(number);
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
    public Packet1 cloneDeep() {
        return new Packet1(this.ID, this.data, this.number);
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
    public String[] getChannels() {
        return null;
    }

    @Override
    public void setChannels(String[] c) {

    }
}
