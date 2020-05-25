package com.checkers;

public class Pawn {
        private final boolean colour; //false - black, true - white
        private int lives;
        private boolean type; //false - pawn, true - king
        //dummy Pawn constructor
        public Pawn() {
            colour = false; //dummy
            lives = 0; //dummy
            type = false; //dummy
        }
        //constructor for adding pawn to the field
        public Pawn(boolean c, boolean t) {
            colour = c;
            type = t;
            lives = type ? 5 : 2;
        }
        //copy constructor
        public Pawn(Pawn pawn) {
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
        //false if dead
        public boolean remLife() {
            --lives;
            return lives != 0;
        }

        public void remPawn() {
            lives = 0;
        }

        public void convertToKing() {
            type = true;
        }
}
