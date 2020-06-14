package com.mycompany.app;

public class Player {
    private int reviveCount;
    private int pawnCount;
    private int maxPawnCount;
    private Pawn.Color color;
    private Board board;
    private String name;
    private int mode;
    private String errorMsg;

    public Player(int m, String c, String n) {
        errorMsg = "";
        mode = m;
        if (m == 1) {
            reviveCount = 3;
            maxPawnCount = 12;
        }
        else if (m == 2) {
            reviveCount = 4;
            maxPawnCount = 16;
        }
        else {
            reviveCount = 5;
            maxPawnCount = 20;
        }
        pawnCount = maxPawnCount;
        color = Pawn.Color.BLACK;
        if (c == "white") {
            color = Pawn.Color.WHITE;
        }
        board = new Board(mode, color);
        name = n;
    }

    public Board getBoard() {
        return board;
    }

    public String getErrorMsg() { return errorMsg; }

    public int getPawnCount() { return pawnCount; }

    public void setBoard(Board b) { board = b; }
    //returns true if the turn is finished, otherwise false
    public boolean revive(Field revField) {
        if (reviveCount == 0) {
            errorMsg = "Revive limit reached";
            return false;
        }
        else if (revField.taken()) {
            errorMsg = "Space is occupied";
            return false;
        }
        else if (pawnCount == maxPawnCount) {
            errorMsg = "Can't place anymore pawns";
            return false;
        }
        else if (revField.getY() == 0) {
            revField.addPawn(color);
            --reviveCount;
            ++pawnCount;
        }
        else {
            errorMsg = "Can only revive at the bottom/top row";
            return false;
        }
        return true;
    }
    //returns true if the turn is finished, toherwise false
    public boolean move(Player opponent, Field currentField, Field newField) {
        int distanceX = newField.getX() - currentField.getX();
        int absDistanceX = Math.abs(distanceX);
        int distanceY = newField.getY() - currentField.getY();
        int absDistanceY = Math.abs(distanceY);
        if (!currentField.taken()) {
            errorMsg = "No pawn chosen";
            return false;
        }
        else if (currentField.getPawn().getColor() != color) {
            errorMsg = "Choose your pawn first";
            return false;
        }
        else if (newField.taken()) {
            errorMsg = "Space is occupied";
            return false;
        }
        else if (currentField.equalTo(newField)) {
            errorMsg = "Choose 2 different fields";
            return false;
        }
        //if pawn
        else if (currentField.getPawn().getType() == Pawn.Type.BASIC) {
            if (absDistanceX == 2 && absDistanceY == 2) {
                if (!take_pawn(opponent, currentField, searchMiddleFieldsDiagonally(currentField, newField), newField)) {
                    return false;
                }
                if (newField.getY() == board.getMaxY()) {
                    newField.convertPawnToKing();
                }
            }
            else if (absDistanceX > 1 || absDistanceY > 1 || absDistanceX == absDistanceY || (distanceX == 0 && distanceY == -1)) {
                errorMsg = "Can only move by 1 field up/right/left";
                return false;
            }
            else {
                newField.movePawn(currentField.getPawn()); //moving pawn
                if (newField.getY() == board.getMaxY()) {
                    newField.convertPawnToKing();
                }
            }
        }
        //if king
        else if (currentField.getPawn().getType() == Pawn.Type.KING) {
            if (absDistanceX == absDistanceY) {
                return take_pawn(opponent, currentField, searchMiddleFieldsDiagonally(currentField, newField), newField);
            }
            else if (absDistanceX == 0 || absDistanceY == 0) {
                if (searchMiddleFieldsLinearly(currentField, newField)) {
                    errorMsg = "Opponent's pawn on the way - can't take pawns linearly";
                    return false;
                }
                if (!newField.moveKing(currentField.getPawn())) { //moving king
                    --pawnCount;
                }
            }
            else {
                errorMsg = "Can only move by X fields up/right/left or X fields diagonally while taking pawn";
                return false;
            }
        }
        else {
            errorMsg = "Other invalid move";
            return false;
        }
        return true;
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
            for (int y = distanceY - 1; takenCount < 2 && y != 0; --y) {
                if (board.findField(oldField.getX() + x, oldField.getY() + y).taken()) {
                    ++takenCount;
                    fieldToTake = board.findField(oldField.getX() + x, oldField.getY() + y);
                }
                --x;
            }
        }
        else if (distanceX > 0 && distanceY < 0) {
            int x = distanceX - 1;
            for (int y = distanceY + 1; takenCount < 2 && y != 0; ++y) {
                if (board.findField(oldField.getX() + x, oldField.getY() + y).taken()) {
                    ++takenCount;
                    fieldToTake = board.findField(oldField.getX() + x, oldField.getY() + y);
                }
                --x;
            }
        }
        else if (distanceX < 0 && distanceY > 0) {
            int x = distanceX + 1;
            for (int y = distanceY - 1; takenCount < 2 && y != 0; --y) {
                if (board.findField(oldField.getX() + x, oldField.getY() + y).taken()) {
                    ++takenCount;
                    fieldToTake = board.findField(oldField.getX() + x, oldField.getY() + y);
                }
                ++x;
            }
        }
        else {
            int x = distanceX + 1;
            //in loop, when y == 0, then the checking field is oldField - the end of searching
            for (int y = distanceY + 1; takenCount < 2 && y != 0; ++y) {
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
            for (int x = distanceX - 1; x != 0 && !invalid; --x) {
                if (board.findField(oldField.getX() + x, oldField.getY()).taken()) {
                    invalid = true;
                }
            }
        }
        else if (distanceY == 0 && distanceX < 0) {
            for (int x = distanceX + 1; x != 0 && !invalid; ++x) {
                if (board.findField(oldField.getX() + x, oldField.getY()).taken()) {
                    invalid = true;
                }
            }
        }
        else if (distanceX == 0 && distanceY > 0) {
            for (int y = distanceY - 1; y != 0 && !invalid; --y) {
                if (board.findField(oldField.getX(), oldField.getY() + y).taken()) {
                    invalid = true;
                }
            }
        }
        else if (distanceX == 0 && distanceY < 0){
            for (int y = distanceY + 1; y != 0 && !invalid; ++y) {
                if (board.findField(oldField.getX(), oldField.getY() + y).taken()) {
                    invalid = true;
                }
            }
        }
        //shouldn't get there, because function should be called with right arguments
        else {
            System.out.println("Unexpected arguments in searchMiddleFieldsLinearly(Field, Field)");
            errorMsg = "Unexpected arguments in searchMiddleFieldsLinearly(Field, Field)";
            invalid = true;
            System.exit(1);
        }
        return invalid;
    }
    //returns true on successfull take
    private boolean take_pawn(Player opponent, Field oldField, Field midField, Field newField) {
        Field dummyField = new Field();
        if (midField.equalTo(dummyField)) {
            errorMsg = "Invalid number of pawns to take (must be 1)";
            return false;
        }
        else if (color == midField.getPawn().getColor()) {
            errorMsg = "Can't take your own pawn";
            return false;
        }
        else {
            if (!midField.removePawnLife()) { //if dead
                --opponent.pawnCount;
            }
            newField.movePawn(oldField.getPawn()); //moving pawn
            if (newField.getPawn().getType() == Pawn.Type.KING) { //if king
                if (newField.removePawnLife()) {
                    --pawnCount;
                }
            }
        }
        return true;
    }

    //only for unit tests
    void utConvertToKing(Field field) {
        field.convertPawnToKing();
    }
}