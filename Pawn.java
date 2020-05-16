package com.checkers;

public class Pawn {
        private boolean colour; //false - black, true - white
        protected int lives;
        //private int state; //not neccessary
        //empty field
        public Pawn() {
            colour = false; //not important
            lives = 0;
        }
        //put new pawn on the field
        public Pawn(boolean c) {
            colour = c;
            lives = 2;
        }
        //copy copnstructor
        public Pawn(Pawn pawn) {
            colour = pawn.colour;
            lives = pawn.lives;
        }

        public boolean getColour() {
            return colour;
        }

        public int getLives() {
            return lives;
        }

        public boolean remLife() {
            --lives;
            if (lives == 0) {
                remPawn();
                return false;
            }
            return true;
        }

        public void remPawn() {
            lives = 0;
        }
}

