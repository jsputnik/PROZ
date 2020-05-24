package com.checkers;

public class Pawn
{
        private boolean colour; //false - black, true - white
        private int lives;
        private boolean type; //false - pawn, true - king
        //private int state; //not neccessary
        //empty field
        public Pawn()
        {
            colour = false; //not important
            lives = 0; //not important
            type = false; //not important
        }
        //put pawn on the field
        public Pawn(boolean c, boolean t)
        {
            colour = c;
            lives = 2;
            type = t;
        }
        //copy constructor
        public Pawn(Pawn pawn)
        {
            colour = pawn.colour;
            lives = pawn.lives;
            type = pawn.type;
        }

        public boolean getColour() {
            return colour;
        }

        public int getLives() {
            return lives;
        }

        public boolean getType() { return type; }

        public boolean remLife()
        {
            --lives;
            if (lives == 0)
            {
                remPawn();
                return false;
            }
            return true;
        }

        public void remPawn() {
            lives = 0;
        }

        public void convertToKing() {
            type = true;
        }
}
