package com.mycompany.app;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NewLoadGame extends JFrame implements ActionListener {
    private JButton newGame;
    private JButton loadGame;
    private JButton back;

    public NewLoadGame() {
        super("Checkers");
        setLocation(700, 400); //1950x
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setSize(500, 500);
        newGame = new JButton("New Game");
        loadGame = new JButton("Join Game");
        back = new JButton("Back");
        newGame.addActionListener(this);
        loadGame.addActionListener(this);
        back.addActionListener(this);
        setLayout(new GridLayout(3, 1, 0, 10));
        add(newGame);
        add(loadGame);
        add(back);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent pos) {
        Object source = pos.getSource();
        if (source == newGame) {
            new GmodeMenu();
            dispose();
        }
        else if (source == loadGame) {
            new JoinServer();
            dispose();
        }
        else if (source == back) {
            new StartMenu();
            dispose();
        }
    }
}
