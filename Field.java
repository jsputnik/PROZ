package com.checkers;

public class Field {
    private final int x;
    private final int y;
    private Pawn pawn;
    //if field is empty or no
    public boolean taken() {
        return pawn.getLives() != 0;
    }
    //dummy field constructor
    public Field() {
        x = -1;
        y = -1;
        pawn = new Pawn();
    }
    //empty field constructor
    public Field(int a, int b) {
        x = a;
        y = b;
        pawn = new Pawn();
    }
    //not empty field constructor
    public Field(int a, int b, Pawn.Color c, Pawn.Type t) {
        x = a;
        y = b;
        pawn = new Pawn(c, t);
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
    public void addPawn(Pawn.Color c) { pawn = new Pawn(c, Pawn.Type.BASIC); }
    //move pawn
    public void movePawn(Pawn p) {
        pawn = new Pawn(p);
        p.remPawn();
    }
    //move king
    public void moveKing(Pawn p) {
        movePawn(p);
        pawn.remLife();
    }
    //convert pawn to king
    public void convertToKing () {
        pawn.convertToKing();
    }

    public boolean equalTo(Field field) {
        return x == field.x && y == field.y;
    }
}
