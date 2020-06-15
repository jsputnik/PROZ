package com.mycompany.app;

import javax.swing.*;
import java.awt.*;


public class WaitingScreen extends JFrame  {
    private int mode;
    private String color;
    private String nickname;

    public WaitingScreen() {
        super("Checkers");
        setLocation(700, 400); //1950x
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setSize(500, 500);
        setLayout(new GridLayout(4, 1, 0, 10));
        JTextField field = new JTextField();
        field.setText("Oczekiwanie na przeciwnika...");
        field.setEditable(false);
        add(field);
        setVisible(true);
    }

    public void infoBox(String infoMessage, String titleBar)
    {
        JOptionPane.showMessageDialog(null, infoMessage, "InfoBox: " + titleBar, JOptionPane.INFORMATION_MESSAGE);
    }
}
