package com.mycompany.app;

import java.io.Serializable;

public class Board implements Serializable {
    private Field[] fields;
    private int width;
    private int height;
    private int mode;

    public Board(int m, Pawn.Color c) {
        mode = m;
        Pawn.Color secondColor;
        secondColor = Pawn.Color.BLACK;
        if (c == Pawn.Color.BLACK) {
            secondColor = Pawn.Color.WHITE;
        }
        if (mode == 1) {
            width = 6;
        }
        else if (mode == 2) {
            width = 8;
        }
        else if (mode == 3) {
            width = 10;
        }
        height = width;
        fields = new Field[width * height];
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                int index = y * width + x;
                if (y == 0 || y == 1) {
                    fields[index] = new Field(x, y, c, Pawn.Type.BASIC);
                }
                else if (y == height - 1 || y == height - 2) {
                    fields[index] = new Field(x, y, secondColor, Pawn.Type.BASIC);
                }
                else {
                    fields[index] = new Field(x, y); //empty field
                }
            }
        }
    }
    //prints board, starting from bottom left corner ending at top right corner
    public void printBoard() {
        for (int y = height - 1; y >= 0; --y) {
            for (int x = 0; x < width; ++x) {
                Field f = fields[y * width + x];
                if (f.getPawn().getLives() == 0) {
                    System.out.print("|[   ], [   ], 0| ");
                }
                else {
                    System.out.print("|");
                    System.out.print(f.getPawn().getType());
                    if (f.getPawn().getType() == Pawn.Type.KING) {
                        System.out.print(" ");
                    }
                    System.out.print(", ");
                    System.out.print(f.getPawn().getColor());
                    System.out.print(", ");
                    System.out.print(f.getPawn().getLives());
                    System.out.print("| ");
                }
                if ((mode == 1 && x == 5) || (mode == 2 && x == 7) || (mode == 3 && x == 9)) {
                    System.out.println("");
                }
            }
        }
        System.out.println("");
    }
    //rotates the board it's called with
    public void rotate() {
        int j = width * height - 1;
        for (int i = 0; i < width * height / 2; ++i) {
            fields[i].swapPawns(fields[j]);
            --j;
        }
    }
    //board index starting from 0, so returns height - 1
    public int getHeight() {
        return height - 1;
    }
    //for easier tests
    public Field findField(int x, int y) {
        return fields[y * width + x];
    }

}