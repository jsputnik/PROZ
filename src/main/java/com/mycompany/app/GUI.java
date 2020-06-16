package com.mycompany.app;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI extends JFrame implements ActionListener {
    private JButton revive;
    private JButton move;
    private JButton uTest1;
    private JButton uTest2;
    private JButton uTest3;
    private JButton uTest4;
    private Player player;
    private Player opponent;

    public GUI(Player p) {
        super("Checkers");
        setLocation(700, 400); //1950x
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setSize(500, 500);
        revive = new JButton("revive");
        move = new JButton("move");
        uTest1 = new JButton("test1");
        uTest2 = new JButton("test2");
        uTest3 = new JButton("test3");
        uTest4 = new JButton("test4");
        revive.addActionListener(this);
        move.addActionListener(this);
        uTest1.addActionListener(this);
        uTest2.addActionListener(this);
        uTest3.addActionListener(this);
        uTest4.addActionListener(this);
        setLayout(new GridLayout(6, 1, 0, 10));
        add(revive);
        add(move);
        add(uTest1);
        add(uTest2);
        add(uTest3);
        add(uTest4);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent pos) {
        Object source = pos.getSource();
        if (source == revive) {
            System.out.println("Revive clicked");
        }
        else if (source == move) {
            System.out.println("move clicked");
        }
        else if (source == uTest1) {
            ModelUnitTest modelUnitTest = new ModelUnitTest(1);
            modelUnitTest.test1();
        }
        else if (source == uTest2) {
            ModelUnitTest modelUnitTest = new ModelUnitTest(1);
            modelUnitTest.test2();
        }
        else if (source == uTest3) {
            ModelUnitTest modelUnitTest = new ModelUnitTest(1);
            modelUnitTest.test3();
        }
        else if (source == uTest4) {
            ModelUnitTest modelUnitTest = new ModelUnitTest(1);
            modelUnitTest.test4();
        }
    }
}