package com.checkers;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class HelpMenu extends JFrame implements ActionListener {
    private JButton back;
    private JEditorPane helpText;

    public HelpMenu() {
        super("Checkers");
        setLocation(700, 400); //1950x
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setSize(500, 500);
        back = new JButton("Back");
        back.addActionListener(this);
        setLayout(new BorderLayout());
        helpText = new JEditorPane();
        helpText.setEditable(false);
        helpText.setContentType("text/html");
        helpText.setBackground(Color.orange);
        helpText.setText("Zasady:<br><ol><li>ruchy do przodu lub w boki po 1 polu<li>zbijać można tylko po ukosie<li>pionki mają po 2 życia<li>damka znika po 5 ruchach" +
                "<li>dostępne 3 tryby gry:<ul><li>plansza 6x6, 2x12 pionków, 3 ożywienia<li>plansza 8x8, 2x16 pionków, 4 ożywienia<li>plansza 10x10, 2x20 pionków, 5 ożywień");
        add(helpText, BorderLayout.NORTH);
        add(back, BorderLayout.SOUTH);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent pos) {
        Object source = pos.getSource();
        if (source == back) {
            new StartMenu();
            dispose();
        }
    }
}
