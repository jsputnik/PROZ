package com.mycompany.app;
import java.awt.EventQueue;

public class Game {
    private Player one;
    private Player two;

    public static void main(String[] args) {
        System.out.println("Start");
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                new StartMenu();
            }
        });
    }
}