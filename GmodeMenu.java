package com.checkers;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class GmodeMenu extends JFrame implements ActionListener {
    private JButton mode1;
    private JButton mode2;
    private JButton mode3;
    private JButton back;

    public GmodeMenu() {
        super("Checkers");
        setLocation(700, 400); //1950x
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setSize(500, 500);
        mode1 = new JButton("6x6");
        mode2 = new JButton("8x8");
        mode3 = new JButton("10x10");
        back = new JButton("Back");
        mode1.addActionListener(this);
        mode2.addActionListener(this);
        mode3.addActionListener(this);
        back.addActionListener(this);
        setLayout(new GridLayout(4, 1, 0, 10));
        add(mode1);
        add(mode2);
        add(mode3);
        add(back);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent pos) {
        Object source = pos.getSource();
        if (source == mode1) {
            new SetNameMenu(1);
            dispose();
        }
        else if (source == mode2) {
            new SetNameMenu(2);
            dispose();
        }
        else if (source == mode3) {
            new SetNameMenu(3);
            dispose();
        }
        else if (source == back) {
            new StartMenu();
            dispose();
        }
    }
}
