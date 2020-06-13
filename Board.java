package com.checkers;

public class Board {
    private Field[] fields;
    private int width;
    private int height;
    private int mode;
    private Pawn.Color color;

    public Board(int m, Pawn.Color c) {
        mode = m;
        color = c;
        Pawn.Color secondColor;
        secondColor = Pawn.Color.BLACK;
        if (color == Pawn.Color.BLACK) {
            secondColor = Pawn.Color.WHITE;
        }
        if(mode == 1)
            width = 6;
        else if(mode == 2)
            width = 8;
        else if(mode == 3)
            width = 10;
        height = width;
        fields = new Field[width * height];
        for(int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                int index = y * width + x;
                if (y == 0 || y == 1)
                    fields[index] = new Field(x, y, color, Pawn.Type.BASIC);
                else if (y == height - 1 || y == height - 2)
                    fields[index] = new Field(x, y, secondColor, Pawn.Type.BASIC);
                else
                    fields[index] = new Field(x, y); //empty field
            }
        }
    }

    public int getHeight() {
        return height - 1;
    }

    public Field findField(int x, int y) {
        return fields[y * width + x];
    }
}
