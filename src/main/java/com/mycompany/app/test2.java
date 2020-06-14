package com.mycompany.app;

import java.io.Serializable;

public class test2 implements TestClass, Serializable {
    private int ID;
    private String data;
    private String[] channels;

    public test2(int id, String d) {
        ID = id;
        data = d;
    }

    public test2(int id, String d, String[] g) {
        ID = id;
        data = d;
        channels = g;
    }

    public void setChannels(String[] c) {
        channels = c;
    }

    @Override
    public test2 cloneDeep() {
        return new test2(this.ID, this.data, this.channels);
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
