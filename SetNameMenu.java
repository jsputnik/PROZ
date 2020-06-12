package com.checkers;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SetNameMenu extends JFrame implements ActionListener {
    private JTextField nicknameField;
    private JButton confirm;
    String nickname; //wywalic
    int mode; //wywalic

    public SetNameMenu(int m) {
        super("Checkers");
        setLocation(700, 400); //1950x
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setSize(300, 100);
        nickname = "Guest";
        mode = m;
        confirm = new JButton("Confirm");
        confirm.addActionListener(this);
        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        JLabel nickname = new JLabel("Nickname: ");
        c.fill = GridBagConstraints.NONE;
        c.gridx = 0;
        c.gridy = 0;
        c.anchor = GridBagConstraints.FIRST_LINE_START;
        add(nickname, c);
        nicknameField = new JTextField();
        nicknameField.setPreferredSize(new Dimension(150, 20));
        c.fill = GridBagConstraints.NONE;
        c.gridx = 1;
        c.gridy = 0;
        c.anchor = GridBagConstraints.LINE_END;
        add(nicknameField, c);
        c.fill = GridBagConstraints.NONE;
        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 2;
        c.anchor = GridBagConstraints.PAGE_END;
        add(confirm, c);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent pos) {
        Object source = pos.getSource();
        if (source == confirm) {
            nickname = nicknameField.getText();
            if (nickname.length() == 0) {
                nickname = "Guest";
            }
            this.setVisible(false);
            if (mode == 1) {
                //connect and load 6x6 board
            }
            else if (mode == 2) {
                //connect and load 8x8 board
            }
            else if (mode == 3) {
                //connect and load 10x10 board
            }
        }
    }
}
