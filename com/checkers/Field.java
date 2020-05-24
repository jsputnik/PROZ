package com.checkers;

public class Field
{
    private int x;
    private int y;
    private Pawn pawn;
    private boolean selected = false;
    //if field is empty or no
    public boolean taken()
    {
        return pawn.getLives() == 0;
    }
    //empty field constructor
    public Field(int x, int y)
    {
        this.x = x;
        this.y = y;
        pawn = new Pawn(); //pawn.lives == 0 ==> empty field
    }
    //not empty field constructor
    public Field(int x, int y, boolean color, boolean type)
    {
        this.x = x;
        this.y = y;
        pawn = new Pawn(color, type);
    }
    //copy constructor
    public Field(Field field)
    {
        x = field.x;
        y = field.y;
        pawn = field.pawn;
    }

    public void select()
    {
        selected = true;
    }
    public void unselect()
    {
        selected = false;
    }
    public int getX()
    {
        return x;
    }

    public int getY()
    {
        return y;
    }

    public Pawn getPawn()
    {
        return pawn;
    }
    //remove pawn from field
    public void remPawn()
    {
        pawn.remPawn();
    }
    //remove life
    public boolean remLife()
    {
        return pawn.remLife();
    }
    //add new pawn on the field
    public void addPawn(boolean color)
    {
        pawn = new Pawn(color, false); // not king
    }
    //move pawn
    public void movePawn(Pawn p) //move some pawn to this field (???)
    {
        pawn = new Pawn(p);
        p.remPawn(); // perhaps
    }
    //convert pawn to king
    public void convertToKing ()
    {
        pawn.convertToKing();
    }

    public boolean equalTo(Field field)
    {
        return x == field.x && y == field.y;
    }
}
