package com.checkers;

public class Player {
    int reviveCount;
    int pawnCount;
    int maxPawnCount;
    Player opponent; //not needed
    Colour colour;
    Board board;

    public void make_turn() {
        ;//if 2 arguments passed move();
        //if 1 argument passed revive();
        //else exception
    }

    private void revive(Field revField) {
        if (reviveCount == 0) {
            System.out.println("Revive limit reached");
            //try again
        }
        else if (pawnCount == maxPawnCount) {
            System.out.println("No pawn has died yet");
            //try again
        }
        else if (revField.taken()) {
            System.out.println("Space is occupied");
            //try again
        }
        //change
        else if ((colour == Colour.BLACK && revField.getY() == board.getHeight()) || (colour == Colour.WHITE && revField.getY() == 0)) {
            revField.addPawn(colour);
            --reviveCount;
            ++pawnCount;
        }
        else {
            System.out.println("Can only revive at the bottom/top row");
            //try again
        }
    }

    private void move(Field currentField, Field newField) {
        int distanceX = newField.getX() - currentField.getX();
        int absDistanceX = Math.abs(distanceX);
        int distanceY = newField.getY() - currentField.getY();
        int absDistanceY = Math.abs(distanceY);
        if (!currentField.taken()) {
            System.out.println("No pawn chosen");
            //try again
        }
        else if (currentField.getPawn().getColour() != colour) {
            System.out.println("Choose your pawn first");
            //try again
        }
        else if (newField.taken()) {
            System.out.println("Space is occupied");
            //try again
        }
        else if (currentField.equalTo(newField)) {
            System.out.println("Choose 2 different fields");
            //try again
        }
        //if pawn
        else if (currentField.getPawn().getType() == Pawn.Type.BASIC) {
            if (absDistanceX == 2 && absDistanceY == 2) {
                take_pawn(opponent, currentField, searchMiddleFieldsDiagonally(currentField, newField), newField);
                convertToKing(newField);
            }
            else if (absDistanceX > 1 || absDistanceY > 1 || absDistanceX == absDistanceY || (distanceX == 0 && distanceY == -1)) {
                System.out.println("Can only move by 1 field up/right/left");
            }
            else {
                newField.movePawn(currentField.getPawn()); //moving pawn
                convertToKing(newField);
            }
        }
        //if king
        else if (currentField.getPawn().getType() == Pawn.Type.KING) {
            if (absDistanceX == absDistanceY) {
                take_pawn(opponent, currentField, searchMiddleFieldsDiagonally(currentField, newField), newField);
            }
            else if (absDistanceX == 0 || absDistanceY == 0) {
                if (!searchMiddleFieldsLinearly(currentField, newField)) {
                    newField.moveKing(currentField.getPawn()); //moving king
                }
            }
            else {
                System.out.println("Can only move by X fields up/right/left or X fields diagonally while taking pawn");
            }
        }
        else {
            System.out.println("Other invalid move");
            //try again
        }
    }
    //returns a taken field or dummy field if less or more than 1 pawn found
    //expects newField to be empty
    private Field searchMiddleFieldsDiagonally(Field oldField, Field newField) {
        int distanceX = newField.getX() - oldField.getX();
        int distanceY = newField.getY() - oldField.getY();
        int takenCount = 0;
        Field fieldToTake = new Field(); //dummy field
        if (distanceX > 0 && distanceY > 0) {
            int x = distanceX - 1;
            for (int y = distanceY - 1; takenCount < 2 || y > 0; --y) {
                if (board.findField(oldField.getX() + x, oldField.getY() + y).taken()) {
                    ++takenCount;
                    fieldToTake = board.findField(oldField.getX() + x, oldField.getY() + y);
                }
                --x;
            }
        }
        else if (distanceX > 0 && distanceY < 0) {
            int x = distanceX - 1;
            for (int y = distanceY + 1; takenCount < 2 || y < 0; ++y) {
                if (board.findField(oldField.getX() + x, oldField.getY() + y).taken()) {
                    ++takenCount;
                    fieldToTake = board.findField(oldField.getX() + x, oldField.getY() + y);
                }
                --x;
            }
        }
        else if (distanceX < 0 && distanceY > 0) {
            int x = distanceX + 1;
            for (int y = distanceY - 1; takenCount < 2 || y > 0; --y) {
                if (board.findField(oldField.getX() + x, oldField.getY() + y).taken()) {
                    ++takenCount;
                    fieldToTake = board.findField(oldField.getX() + x, oldField.getY() + y);
                }
                ++x;
            }
        }
        else {
            int x = distanceX + 1;
            for (int y = distanceY + 1; takenCount < 2 || y < 0; ++y) {
                if (board.findField(oldField.getX() + x, oldField.getY() + y).taken()) {
                    ++takenCount;
                    fieldToTake = board.findField(oldField.getX() + x, oldField.getY() + y);
                }
                ++x;
            }
        }
        if (takenCount != 1) {
            fieldToTake = new Field(); //dummy field
        }
        return fieldToTake;
    }
    //returns false if no taken fields found
    //excpects newField to be empty
    private boolean searchMiddleFieldsLinearly(Field oldField, Field newField) {
        int distanceX = newField.getX() - oldField.getX();
        int distanceY = newField.getY() - oldField.getY();
        boolean invalid = false;
        if (distanceY == 0 && distanceX > 0) {
            for (int x = distanceX - 1; x > 0 || !invalid; --x) {
                if (board.findField(oldField.getX() + x, oldField.getY()).taken()) {
                    invalid = true;
                }
            }
        }
        else if (distanceY == 0 && distanceX < 0) {
            for (int x = distanceX + 1; x > 0 || !invalid; ++x) {
                if (board.findField(oldField.getX() + x, oldField.getY()).taken()) {
                    invalid = true;
                }
            }
        }
        else if (distanceX == 0 && distanceY > 0) {
            for (int y = distanceY - 1; y > 0 || !invalid; --y) {
                if (board.findField(oldField.getX(), oldField.getY() + y).taken()) {
                    invalid = true;
                }
            }
        }
        else if (distanceX == 0 && distanceY < 0){
            for (int y = distanceY + 1; y > 0 || !invalid; ++y) {
                if (board.findField(oldField.getX(), oldField.getY() + y).taken()) {
                    invalid = true;
                }
            }
        }
        //shouldn't get there
        else {
            System.out.println("Unexpected arguments in searchMiddleFieldsLinearly(Field, Field)");
            invalid = true;
            System.exit(1);
        }
        return invalid;
    }

    private void take_pawn(Player opponent, Field oldField, Field midField, Field newField) {
        Field dummyField = new Field();
        if (midField.equalTo(dummyField)) {
            System.out.println("Invalid number of pawns to take (must be 1)");
            //try again
        }
        else if (colour == midField.getPawn().getColour()) {
            System.out.println("Can't take your own pawn");
            //try again
        }
        else {
            if (!midField.remLife()) { //if dead
                --opponent.pawnCount;
            }
            newField.movePawn(oldField.getPawn()); //moving pawn
            if (newField.getPawn().getType() == Pawn.Type.KING) { //if king
                newField.remLife();
            }
        }
    }
    //change
    private void convertToKing(Field field) {
        if (colour == Colour.BLACK) {
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
