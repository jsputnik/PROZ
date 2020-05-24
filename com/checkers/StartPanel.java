package com.checkers;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;


public class StartPanel extends JPanel implements ActionListener
{
    private JFrame startMenu; //to know which frame to close
    private JButton start;
    private JButton help;
    private JButton quit;

    public StartPanel(JFrame frameToClose)
    {
        startMenu = frameToClose;
        start = new JButton("Start");
        help = new JButton("Help");
        quit = new JButton("Quit");
        start.addActionListener(this);
        help.addActionListener(this);
        quit.addActionListener(this);
        setLayout(new GridLayout(3, 1, 0, 10));
        setPreferredSize(new Dimension(500, 500));
        add(start);
        add(help);
        add(quit);
    }

    public void actionPerformed(ActionEvent pos)
    {
        Object source = pos.getSource();
        if (source == start)
        {
            new Menu(1, 0);
            startMenu.dispose();
        }
        else if (source == help)
        {
            new Menu(2, 0);
            startMenu.dispose();
        }
        else if (source == quit)
        {
            System.exit(0);
        }
    }
}
