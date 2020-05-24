package com.checkers;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class GmodePanel extends JPanel implements ActionListener
{
    private JFrame gmodeMenu; //to know which frame to close
    private JButton mode1;
    private JButton mode2;
    private JButton mode3;
    private JButton back;

    public GmodePanel(JFrame frameToClose)
    {
        gmodeMenu = frameToClose;
        mode1 = new JButton("6x6");
        mode2 = new JButton("8x8");
        mode3 = new JButton("10x10");
        back = new JButton("Back");
        mode1.addActionListener(this);
        mode2.addActionListener(this);
        mode3.addActionListener(this);
        back.addActionListener(this);
        setLayout(new GridLayout(4, 1, 0, 10));
        setPreferredSize(new Dimension(500, 500));
        add(mode1);
        add(mode2);
        add(mode3);
        add(back);
    }

    public void actionPerformed(ActionEvent pos)
    {
        Object source = pos.getSource();
        if (source == mode1)
        {
            new Menu(3, 1);
            gmodeMenu.dispose();
        }
        else if (source == mode2)
        {
            new Menu(3, 2);
            gmodeMenu.dispose();
        }
        else if (source == mode3)
        {
            new Menu(3, 3);
            gmodeMenu.dispose();
        }
        else if (source == back)
        {
            new Menu(0, 0);
            gmodeMenu.dispose();
        }
    }
}
