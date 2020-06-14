package com.mycompany.app;

import java.io.Serializable;

public class TestClass implements Serializable {
    private int ID;
    private String data;

    public TestClass(int id, String d) {
        ID = id;
        data = d;
    }

    public TestClass(TestClass t) {
        ID = t.ID;
        data = t.data;
    }

    public void print() {
        System.err.println(data);
    }

    public String getData() {
        return data;
    }
}
