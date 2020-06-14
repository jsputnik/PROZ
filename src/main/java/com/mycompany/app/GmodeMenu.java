package com.mycompany.app;
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

        if (source == back) {
            new NewLoadGame();
            dispose();
            return;
        }

        JOptionPane setName = new JOptionPane();
        String nickname = setName.showInputDialog(this, "Choose name:", "Set name", JOptionPane.PLAIN_MESSAGE);

        if(nickname == null) {
            dispose();
            new NewLoadGame();
            return;
        }

        JOptionPane setColor = new JOptionPane();
        Object[] options = {"black", "white"};
        String color = (String)setColor.showInputDialog(this,"Choose color:","Set color", JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
        if(color == null) {
            dispose();
            new NewLoadGame();
            return;
        }

        int m;
        if (source == mode1) m = 1;
        else if (source == mode2) m = 2;
        else  m = 3;

        Player p = new Player(m, color, nickname);
        dispose();
        new GUI(p);
    }
}
