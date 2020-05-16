package com.checkers;

public class Board {
    private Field[] fields;
    private int width;
    private int height;

    public int getHeight() {
        return height - 1;
    }

    public Field findField(int x, int y) {
        return fields[y * width + x];
    }
}
