package com.checkers;

public class Player {
    int reviveCount;
    int pawnCount;
    int maxPawnCount;
    Player opponent;
    boolean colour;
    Board board;

    public void make_turn() {
        ;//if 2 arguments passed move();
        //if 1 argument passed revive();
        //else exception
    }

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
        if (!currentField.taken()) {
            ;//invalid move, no pawn chosen
        }
        else if (currentField.getPawn().getColour() != colour) {
            ;//invalid move, choose your pawn first
        }
        else if (newField.taken()) {
            ;//invalid move, space is occupied
        }
        else if (currentField.equalTo(newField)) {
            ;//invalid move, choose 2 different fields
        }
        //if pawn
        else if (!currentField.getPawn().getType()) {
            if (absDistanceX == 2 && absDistanceY == 2) {
                if (!take_pawn(opponent, currentField, searchMiddleFieldsDiagonally(currentField, newField), newField)) {
                    //invalid take_pawn, exception
                }
                convertToKing(newField);
            }
            else if (absDistanceX > 1 || absDistanceY > 1 || absDistanceX == absDistanceY || (distanceX == 0 && distanceY == -1)) {
                ;//invalid move, can only move by 1 field up/right/left
            }
            else {
                newField.movePawn(currentField.getPawn()); //moving pawn
                currentField.remPawn();
                convertToKing(newField);
            }
        }
        //if king
        else if (currentField.getPawn().getType()) {
            if (absDistanceX == absDistanceY) {
                if (!take_pawn(opponent, currentField, searchMiddleFieldsDiagonally(currentField, newField), newField)) {
                    ;//invalid take_pawn, exception
                }
            }
            else if (absDistanceX == 0 || absDistanceY == 0) {
                if (!searchMiddleFieldsLinearly(currentField, newField)) {
                    newField.movePawn(currentField.getPawn()); //moving pawn
                    currentField.remPawn();
                }
            }
        }
    }
    //returns a taken field or exception
    private Field searchMiddleFieldsDiagonally(Field oldField, Field newField) {
        int distanceX = newField.getX() - oldField.getX();
        int absDistanceX = Math.abs(distanceX);
        int distanceY = newField.getY() - oldField.getY();
        int absDistanceY = Math.abs(distanceY);
        int x = absDistanceX - 1;
        int takenCount = 0;
        Field fieldToTake = new Field(-1, -1);
        for (int y = absDistanceY - 1; takenCount < 2 || y > 0; --y) {
            if (board.findField(oldField.getX() + x, oldField.getY() + y).taken()) {
                ++takenCount;
                fieldToTake = board.findField(oldField.getX() + x, oldField.getY() + y);
            }
            --x;
            }
        if (takenCount != 1) {
            //exception
        }
        return fieldToTake;
    }
    //returns whether found taken fields or no
    private boolean searchMiddleFieldsLinearly(Field oldField, Field newField) {
        int distanceX = newField.getX() - oldField.getX();
        int absDistanceX = Math.abs(distanceX);
        int distanceY = newField.getY() - oldField.getY();
        int absDistanceY = Math.abs(distanceY);
        boolean invalid = false;
        if (absDistanceY == 0) {
            if (distanceX > 0) {
                for (int x = distanceX - 1; x > 0 || invalid; --x) {
                    if (board.findField(oldField.getX() + x, oldField.getY()).taken()) {
                        invalid = true;
                    }
                }
            }
            else {
                for (int x = distanceX - 1; x < 0 || invalid; ++x) {
                    if (board.findField(oldField.getX() + x, oldField.getY()).taken()) {
                        invalid = true;
                    }
                }
            }
        }
        else {
            if (distanceY > 0) {
                for (int y = distanceY - 1; y > 0 || invalid; --y) {
                    if (board.findField(oldField.getX(), oldField.getY() + y).taken()) {
                        invalid = true;
                    }
                }
            }
            else {
                for (int y = distanceY - 1; y < 0 || invalid; ++y) {
                    if (board.findField(oldField.getX(), oldField.getY() + y).taken()) {
                        invalid = true;
                    }
                }
            }
        }
        if (invalid) {
            return true;//invalid move, there are pawns on the way, exception
        }
        return true;
    }

    private boolean take_pawn(Player opponent, Field oldField, Field midField, Field newField) {
        if (midField.getPawn().getLives() == 0) {
            return false;//invalid move, no pawn to take
        }
        else if (colour == midField.getPawn().getColour()) {
            return false;//invalid move, cant take your own pawn
        }
        else {
            if (!midField.remLife()) { //if dead
                --opponent.pawnCount;
            }
            newField.movePawn(oldField.getPawn());
            oldField.remPawn();
            return true;
        }
    }

    private void convertToKing(Field field) {
        if (!colour) {
            if (field.getY() == 0) {
                field.convertToKing();
            }
        }
        else {
            if (field.getY() == board.getHeight()) {
                field.convertToKing();
            }
        }
    }
}
