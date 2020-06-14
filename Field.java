package com.checkers;

public class Field {
    private int x;
    private int y;
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

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Pawn getPawn() {
        return pawn;
    }

    //remove life of pawn on the field, retruns false if it's already dead
    public boolean removePawnLife() {
        return pawn.removeLife();
    }
    //add new pawn on the field
    public void addPawn(Pawn.Color c) { pawn = new Pawn(c, Pawn.Type.BASIC); }
    //move pawn from field to this; new pawn is created on field(this), then pawn's lives on old field are set to 0, meaning it's 'removed'
    public void movePawn(Pawn p) {
        pawn = new Pawn(p);
        p.removePawn();
    }
    //move king
    public boolean moveKing(Pawn p) {
        movePawn(p);
        return pawn.removeLife();
    }
    //convert pawn to king
    public void convertPawnToKing() {
        pawn.convertToKing();
    }

    public boolean equalTo(Field field) {
        return x == field.x && y == field.y;
    }

    public void swapPawns(Field field) {
        Pawn pawnTemp = pawn;
        pawn = field.pawn;
        field.pawn = pawnTemp;
    }
}
