package com.mycompany.app;

public class ModelUnitTest {
    private Player black;
    private Player white;

    public ModelUnitTest(int mode) {
        black = new Player(mode, "black", "black");
        white = new Player(mode, "white", "white");
    }

    public static void main(String[] args) {
        ModelUnitTest uTest = new ModelUnitTest(1);
        uTest.test1();
        //uTest.test2();
        //uTest.test3();
        //uTest.test4();
    }

    public void test1() {
        System.out.println("board at the start");
        white.getBoard().printBoard();
        boolean turnFinished = false;
        //ruch pionkiem o 1 do przodu
        if (!turnFinished) {
            turnFinished = white.move(black, white.getBoard().findField(0, 1), white.getBoard().findField(0, 2));
        }
        else {
            System.out.println("Not this player turn");
        }
        System.out.println("board after white move1 (0, 1) to (0, 2)");
        System.out.println(white.getErrorMsg());
        white.getBoard().printBoard();
        if (turnFinished) { //change turn if the end of turn
            white.getBoard().rotate();
            black.setBoard(white.getBoard());
            System.out.println("board after white end turn1");
            black.getBoard().printBoard();
            turnFinished = false;
        }
        if (!turnFinished) {
            turnFinished = black.move(white, black.getBoard().findField(1, 1), black.getBoard().findField(1, 2));
        }
        else {
            System.out.println("Not this player turn");
        }
        System.out.println("board after black move1 (1, 1) to (1, 2)");
        System.out.println(black.getErrorMsg());
        black.getBoard().printBoard();
        if (turnFinished) { //change turn if the end of turn
            black.getBoard().rotate();
            white.setBoard(black.getBoard());
            System.out.println("board after black end turn1");
            white.getBoard().printBoard();
            turnFinished = false;
        }
        //zmiana pionka na damke
        white.utConvertToKing(white.getBoard().findField(5, 1));
        System.out.println("board after white convertToKing1 on (5, 1)");
        white.getBoard().printBoard();
        //ruch damka o 1 do przodu
        if (!turnFinished) {
            turnFinished = white.move(black, white.getBoard().findField(5, 1), white.getBoard().findField(5, 2));
        }
        else {
            System.out.println("Not this player turn");
        }
        System.out.println("board after white move2 (5, 1) to (5, 2)");
        System.out.println(white.getErrorMsg());
        white.getBoard().printBoard();
        if (turnFinished) {
            white.getBoard().rotate();
            black.setBoard(white.getBoard());
            System.out.println("board after white end turn2");
            black.getBoard().printBoard();
            turnFinished = false;
        }
        if (!turnFinished) {
            turnFinished = black.move(white, black.getBoard().findField(0, 1), black.getBoard().findField(0, 2));
        }
        else {
            System.out.println("Not this player turn");
        }
        System.out.println("board after black move2 (0, 1) to (0, 2)");
        System.out.println(black.getErrorMsg());
        black.getBoard().printBoard();

        if (!turnFinished) {
            black.move(white, black.getBoard().findField(5, 2), black.getBoard().findField(2, 2));
        }
        else {
            System.out.println("Not this player turn");
        }
        System.out.println("board after black move3 (5,2) to (2, 2)");
        System.out.println(black.getErrorMsg());
        black.getBoard().printBoard();
    }

    public void test2() {
        System.out.println("board at the start");
        white.getBoard().printBoard();

        white.move(black, white.getBoard().findField(2, 1), white.getBoard().findField(2, 2));
        System.out.println("board after white move1 (2, 1) to (2, 2)");
        System.out.println(white.getErrorMsg());
        white.getBoard().printBoard();

        white.move(black, white.getBoard().findField(2, 2), white.getBoard().findField(2, 3));
        System.out.println("board after white move2 (2, 2) to (2, 3)");
        System.out.println(white.getErrorMsg());
        white.getBoard().printBoard();

        white.move(black, white.getBoard().findField(2, 3), white.getBoard().findField(2, 2));
        System.out.println("board after white move3 (2, 3) to (2, 2)");
        System.out.println(white.getErrorMsg());
        white.getBoard().printBoard();

        white.move(black, white.getBoard().findField(2, 3), white.getBoard().findField(3, 3));
        System.out.println("board after white move4 (2, 3) to (3, 3)");
        System.out.println(white.getErrorMsg());
        white.getBoard().printBoard();

        white.move(black, white.getBoard().findField(3, 3), white.getBoard().findField(2, 3));
        System.out.println("board after white move5 (3, 3) to (2, 3)");
        System.out.println(white.getErrorMsg());
        white.getBoard().printBoard();

        white.move(black, white.getBoard().findField(0, 2), white.getBoard().findField(0, 3));
        System.out.println("board after white move6 (0, 2) to (0, 3)");
        System.out.println(white.getErrorMsg());
        white.getBoard().printBoard();
    }

    public void test3() {
        System.out.println("board at the start");
        white.getBoard().printBoard();

        white.move(black, white.getBoard().findField(2, 1), white.getBoard().findField(2, 2));
        System.out.println("board after white move1 (2, 1) to (2, 2)");
        System.out.println(white.getErrorMsg());
        white.getBoard().printBoard();

        white.move(black, white.getBoard().findField(2, 2), white.getBoard().findField(2, 3));
        System.out.println("board after white move2 (2, 2) to (2, 3)");
        System.out.println(white.getErrorMsg());
        white.getBoard().printBoard();

        white.getBoard().rotate();
        black.setBoard(white.getBoard());
        System.out.println("board after white end turn1");
        black.getBoard().printBoard();

        black.move(white, black.getBoard().findField(4, 1), black.getBoard().findField(2, 3));
        System.out.println("board after black move1 (4, 1) to (2, 3)");
        System.out.println(black.getErrorMsg());
        black.getBoard().printBoard();

        black.move(white, black.getBoard().findField(2, 3), black.getBoard().findField(2, 4));
        System.out.println("board after black move1 (2, 3) to (2, 4)");
        System.out.println(black.getErrorMsg());
        black.getBoard().printBoard();

        black.getBoard().rotate();
        white.setBoard(black.getBoard());
        System.out.println("board after black end turn1");
        white.getBoard().printBoard();

        white.move(black, white.getBoard().findField(2, 0), white.getBoard().findField(2, 1));
        System.out.println("board after white move3 (2, 0) to (2, 1)");
        System.out.println(white.getErrorMsg());
        white.getBoard().printBoard();

        white.move(black, white.getBoard().findField(2, 1), white.getBoard().findField(4, 3));
        System.out.println("board after white move4 (2, 1) to (4, 3)");
        System.out.println(white.getErrorMsg());
        white.getBoard().printBoard();

        white.revive(white.getBoard().findField(2, 0));
        System.out.println("board after white revive1 (2, 0)");
        System.out.println(white.getErrorMsg());
        white.getBoard().printBoard();

        white.getBoard().rotate();
        black.setBoard(white.getBoard());
        System.out.println("board after white end turn2");
        black.getBoard().printBoard();

        System.out.println("White pawnCount: " + white.getPawnCount());

        black.move(white, black.getBoard().findField(2, 1), black.getBoard().findField(4, 3));
        System.out.println("board after black move3 (2, 1) to (4, 3)");
        System.out.println(black.getErrorMsg());
        black.getBoard().printBoard();

        System.out.println("White pawnCount: " + white.getPawnCount());

        black.getBoard().rotate();
        white.setBoard(black.getBoard());
        System.out.println("board after black end turn2");
        white.getBoard().printBoard();

        white.revive(white.getBoard().findField(2, 0));
        System.out.println("board after white revive2 (2, 0)");
        System.out.println(white.getErrorMsg());
        white.getBoard().printBoard();
    }

    public void test4() {
        System.out.println("board at the start");
        white.getBoard().printBoard();

        white.utConvertToKing(white.getBoard().findField(4, 1));
        System.out.println("board after white convertToKing1 on (4, 1)");
        white.getBoard().printBoard();

        white.move(black, white.getBoard().findField(4, 1), white.getBoard().findField(4, 3));
        System.out.println("board after white move1 (4, 1) to (4, 3)");
        System.out.println(white.getErrorMsg());
        white.getBoard().printBoard();

        white.move(black, white.getBoard().findField(4, 3), white.getBoard().findField(0, 3));
        System.out.println("board after white move2 (4, 3) to (0, 3)");
        System.out.println(white.getErrorMsg());
        white.getBoard().printBoard();

        white.move(black, white.getBoard().findField(0, 3), white.getBoard().findField(0, 2));
        System.out.println("board after white move3 (0, 3) to (0, 2)");
        System.out.println(white.getErrorMsg());
        white.getBoard().printBoard();

        white.move(black, white.getBoard().findField(0, 2), white.getBoard().findField(1, 3));
        System.out.println("board after white move4 (0, 2) to (1, 3)");
        System.out.println(white.getErrorMsg());
        white.getBoard().printBoard();

        white.getBoard().rotate();
        black.setBoard(white.getBoard());
        System.out.println("board after white end turn2");
        black.getBoard().printBoard();

        black.move(white, black.getBoard().findField(2, 1), black.getBoard().findField(2, 2));
        System.out.println("board after black move1 (2, 1) to (2, 2)");
        System.out.println(black.getErrorMsg());
        black.getBoard().printBoard();

        black.move(white, black.getBoard().findField(2, 0), black.getBoard().findField(2, 1));
        System.out.println("board after black move3 (2, 0) to (2, 1)");
        System.out.println(black.getErrorMsg());
        black.getBoard().printBoard();

        black.getBoard().rotate();
        white.setBoard(black.getBoard());
        System.out.println("board after black end turn1");
        white.getBoard().printBoard();

        white.move(black, white.getBoard().findField(0, 2), white.getBoard().findField(3, 5));
        System.out.println("board after white move5 (0, 2) to (3, 5)");
        System.out.println(white.getErrorMsg());
        white.getBoard().printBoard();

        white.move(black, white.getBoard().findField(3, 5), white.getBoard().findField(5, 3));
        System.out.println("board after white move5 (3, 5) to (5, 3)");
        System.out.println(white.getErrorMsg());
        white.getBoard().printBoard();

        System.out.println("White pawnCount: " + white.getPawnCount());
    }
}
