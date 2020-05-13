package com.checkers;

public class Pawn {
        private boolean colour; //0 - black, 1 - white
        //private Field position; //pole na ktorym sie znajduje

        }
        private void move(int x, int y) {
                //if (|x - position.x| > 1 || |y - position.y| > 1) {
                //invalid move, try again (exception 1)
                //}
                //Field* new_field = new Field(x, y)
                //if (|x - position.x)| == 1 $$ |y - position.y| == 1 $$ new_field.taken == not taken) {
                //invalid move, try again (exception 1)
                //return new_field

        }
        private void revive(int x, int y) {;
                //if ((colour == 0 && y != 0) || (colour == 1 $$ y != board.y_max)
                        //invalid move exception 1
                //Field* new_field = new Field(x, y)
                //return new_field();
        }
        public void make_turn() {
                //Board* board = (Board*) read_board();
                //cin << char a
                //if a == r revive()
                //if a == m move()
                //else exception 2
                //board.update(new_field->x, new_field->y);
        }
        public int lives;
        public Pawn(boolean c) {colour = c;}
}

