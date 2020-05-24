package com.checkers;

public class Board
{
    private Field[] fields;
    private int width;
    private int height;
    private int mode;
    private boolean color;

    public Board(int mode, boolean color)
    {
        this.mode = mode;
        this.color = color;
        if(mode == 1)
            width = 6;
        else if(mode == 2)
            width = 8;
        else if(mode == 3)
            width = 10;
        height = width;

        for(int x = 0; x < width; x++)
            for(int y = 0; y < height; y++)
            {
                int index = y * width + x;
                if(y == 0 || y == 1)
                    fields[index] = new Field(x, y, !color, false);
                else if(y == height - 1 || y == height - 2)
                    fields[index] = new Field(x, y, color, false);
                else
                    fields[index] = new Field(x, y); //empty field
            }
    }
    public int getHeight()
    {
        return height;
    }

    public Field findField(int x, int y)
    {
        return fields[y * width + x];
    }
}
