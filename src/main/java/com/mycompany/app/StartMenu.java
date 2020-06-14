package com.mycompany.app;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;


public class StartMenu extends JFrame implements ActionListener {
    private JButton start;
    private JButton help;
    private JButton quit;

    public StartMenu() {
        super("Checkers");
        setLocation(700, 400); //1950x
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setSize(500, 500);
        start = new JButton("Start");
        help = new JButton("Help");
        quit = new JButton("Quit");
        start.addActionListener(this);
        help.addActionListener(this);
        quit.addActionListener(this);
        setLayout(new GridLayout(3, 1, 0, 10));
        add(start);
        add(help);
        add(quit);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent pos) {
        Object source = pos.getSource();
        if (source == start) {
            new NewLoadGame();
            dispose();
        }
        else if (source == help) {
            new HelpMenu();
            dispose();
        }
        else if (source == quit) {
            dispose();
        }
    }
}
