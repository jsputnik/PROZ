package com.mycompany.app;

import java.io.Serializable;

public class TestClass implements Serializable {
    private int ID;
    private int[] data;

    public TestClass(int id, int arr[]) {
        ID = id;
        this.data = arr;
    }

    public void print() {
        for(int i = 0; i < 5; ++i) System.err.print(data[i] + " ");
        System.err.println("");
    }

    public void setData(int[] arr) {
        this.data = arr;
    }
}
