package com.checkers;
import java.awt.EventQueue;

public class Game {

    public static void main(String[] args) {
        System.out.println("Start");
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                ModelUnitTest uTest = new ModelUnitTest(1);
                //uTest.test1();
                //uTest.test2();
                //uTest.test3();
                //uTest.test4();
                //new StartMenu();
                
            }
        });
    }


}
