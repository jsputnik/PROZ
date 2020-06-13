package com.checkers;

public class Pawn {
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
