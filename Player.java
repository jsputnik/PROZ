package com.checkers;

public class Player {
    int reviveCount;
    int pawnCount;
    int maxPawnCount;
    Player opponent;
    boolean colour;
    Board board;

    private void revive(Field revField) {
        if (reviveCount == 0) {
            System.out.println("Revive limit reached");//revive limit reached
        }
        else if (pawnCount == maxPawnCount) {
            System.out.println("No pawn has died yet");//invalid revive, no pawn has died yet
        }
        else if (revField.taken()) {
            System.out.println("Space is occupied");//invalid revive, space is occupied
        }
        else if ((colour == false && revField.getY() == board.getHeight()) || (colour == true && revField.getY() == 0)) {
            revField.addPawn(colour);
            --reviveCount;
            ++pawnCount;
        }
        else {
            System.out.println("Can only revive at the bottom/top row");//invalid revive, can revive only at the bottom/top row
        }
    }

    private void move(Field currentField, Field newField) {
        int distanceX = newField.getX() - currentField.getX();
        int absDistanceX = Math.abs(distanceX);
        int distanceY = newField.getY() - currentField.getY();
        int absDistanceY = Math.abs(distanceY);
        if (currentField.getPawn().getColour() != colour) {
            ;//invalid move, choose your pawn first
        }
        else if (newField.taken()) {
            ;//invalid move, space is occupied
        }
        else if (absDistanceX == 2 && absDistanceY == 2) {
            int midX, midY;
            if (distanceX > 0 && distanceY > 0) {
                midX = currentField.getX() + 1;
                midY = currentField.getY() + 1;
            }
            else if (distanceX > 0 && distanceY < 0) {
                midX = currentField.getX() + 1;
                midY = currentField.getY() - 1;
            }
            else if (distanceX < 0 && distanceY < 0) {
                midX = currentField.getX() - 1;
                midY = currentField.getY() - 1;
            }
            else {
                midX = currentField.getX() - 1;
                midY = currentField.getY() + 1;
            }
            take_pawn(opponent, currentField, board.findField(midX, midY), newField);
        }
        else if (absDistanceX > 1 || absDistanceY > 1 || absDistanceX == absDistanceY || (distanceX == -1 && distanceY == 0)) {
            ;//invalid move, can only move by 1 field up/right/left
        }
        else {
            currentField.remPawn();
            newField.addPawn(colour);
        }
    }
    private void take_pawn(Player opponent, Field oldField, Field midField, Field newField) {
        if (midField.getPawn().getLives() == 0) {
            ;//invalid move, no pawn to take
        }
        else if (colour == midField.getPawn().getColour()) {
            ;//invalid move, cant take your own pawn
        }
        else {
            if (!midField.remLife()) { //if dead
                --opponent.pawnCount;
            }
            newField.addPawn(oldField.getPawn().getColour());
            oldField.remPawn();
        }

    }
}
