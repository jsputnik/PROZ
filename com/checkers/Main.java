package com.checkers;
import java.awt.EventQueue;

public class Main
{
    public static void main(String[] args)
    {
        EventQueue.invokeLater(new Runnable()
        {
            public void run() {
                new com.checkers.Menu(0, 0);
            }
        });
    }
}
