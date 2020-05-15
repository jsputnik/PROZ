package com.checkers;
import javax.swing.*;

public class Menu extends JFrame {
    public int gamemode;
    public Menu(int panelmode, int gmode) {
        super("Checkers");
        gamemode = gmode;
        //panelmode: 0 - StartMenu, 1 - GmodeMenu, 2 - HelpMenu, 3 - SetNameMenu
        //gamemode: 0 - not chosen yet, 1 - 6x6, 2 - 8x8, 3 - 10x10
        if (panelmode == 0) {
            JPanel startPanel = new StartPanel(this);
            add(startPanel);
        }
        else if (panelmode == 1) {
            JPanel gmodePanel = new GmodePanel(this);
            add(gmodePanel);
        }
        else if (panelmode == 2) {
            JPanel helpPanel = new HelpPanel(this);
            add(helpPanel);
        }
        else if (panelmode == 3) {
            JPanel setNamePanel = new SetNamePanel(this, gamemode);
            add(setNamePanel);
        }
        setLocation(700, 400); //1950x
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        pack();
        setVisible(true);
    }
}
