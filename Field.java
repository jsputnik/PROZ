package com.checkers;

public class Field {
    private int x;
    private int y;
    private Pawn pawn;
    //if field is empty or no
    public boolean taken() {
        if (pawn.getLives() == 0) {
            return false;
        }
        return true;
    }
    //empty field constructor
    public Field(int a, int b) {
        x = a;
        y = b;
        pawn = new Pawn();
    }
    //not empty field constructor
    public Field(int a, int b, boolean c) {
        x = a;
        y = b;
        pawn = new Pawn(c);
    }
    //copy constructor
    public Field(Field field) {
        x = field.x;
        y = field.y;
        pawn = field.pawn;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Pawn getPawn() {
        return pawn;
    }
    //remove pawn from field
    public void remPawn() {
        pawn.remPawn();
    }
    //remove life
    public boolean remLife() {
        return pawn.remLife();
    }
    //add new pawn on the field
    public void addPawn(boolean c) {
        pawn = new Pawn(c);
    }
}
