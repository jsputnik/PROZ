package com.checkers;

public class Pawn {
        enum Type {BASIC, KING};

        private Colour colour;
        private int lives;
        private Type type;
        //dummy Pawn constructor
        public Pawn() {
            colour = Colour.BLACK;
            lives = 0;
            type = Type.BASIC;
        }
        //constructor for adding pawn to the field
        public Pawn(Colour c, Type t) {
            colour = c;
            type = t;
            lives = type == Type.KING ? 5 : 2;
        }
        //copy constructor
        public Pawn(Pawn pawn) {
            colour = pawn.colour;
            lives = pawn.lives;
            type = pawn.type;
        }

        public Colour getColour() {
            return colour;
        }

        public int getLives() {
            return lives;
        }

        public Type getType() { return type; }
        //false if dead
        public boolean remLife() {
            --lives;
            return lives != 0;
        }

        public void remPawn() {
            lives = 0;
        }

        public void convertToKing() {
            type = Type.KING;
        }
}
