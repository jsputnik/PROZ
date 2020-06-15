package com.mycompany.app;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JoinMenu extends JFrame {
    private GameClient gc;
    private Packet resp = null;

    public JoinMenu() {
        super("Checkers");

        setLocation(700, 400); //1950x
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setSize(500, 500);
        setVisible(true);

        JOptionPane setName = new JOptionPane();
        String nickname = setName.showInputDialog(this, "Choose name:", "Set name", JOptionPane.PLAIN_MESSAGE);

        if(nickname == null) {
            dispose();
            new NewJoinMenu();
            return;
        }

        dispose();

        gc = new GameClient(-1, "black", nickname);

        Runnable r = new Runnable() {
            public void run() {
                try {
                    gc.run();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };

        Thread gameClientThread = new Thread(r);
        gameClientThread.start();
    }
}