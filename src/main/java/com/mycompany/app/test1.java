package com.mycompany.app;

import java.io.Serializable;

public class test1 implements TestClass, Serializable {
    private int number;
    private int ID;
    private String data;

    public test1(int id, String d, int n) {
        ID = id;
        data = d;
        number = n;
    }

    public void printNum() {
        System.out.println(number);
    }

    @Override
    public test1 cloneDeep() {
        return new test1(this.ID, this.data, this.number);
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
