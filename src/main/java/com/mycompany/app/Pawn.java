package com.mycompany.app;

import java.io.Serializable;

public class Pawn implements Serializable {
    enum Type {BASIC, KING};
    enum Color {BLACK, WHITE};

    private Color color;
    private int lives;
    private Type type;
    //dummy Pawn constructor
    public Pawn() {
        color = Color.BLACK;
        lives = 0;
        type = Type.BASIC;
    }
    //constructor for adding pawn to the field
    public Pawn(Color c, Type t) {
        color = c;
        type = t;
        lives = type == Type.KING ? 5 : 2;
    }
    //copy constructor
    public Pawn(Pawn pawn) {
        color = pawn.color;
        lives = pawn.lives;
        type = pawn.type;
    }

    public Color getColor() {
        return color;
    }

    public int getLives() {
        return lives;
    }

    public Type getType() { return type; }
    //false if dead
    public boolean removeLife() {
        --lives;
        return lives > 0;
    }
    //sets pawns lives to 0, doesn't remove Pawn object
    public void removePawn() {
        lives = 0;
    }

    public void convertToKing() {
        type = Type.KING;
        lives = 5;
    }
}