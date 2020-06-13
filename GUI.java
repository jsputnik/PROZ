package com.checkers;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI extends JFrame implements ActionListener {
    private JButton revive;
    private JButton move;
    //private Board board;
    private Player player;
    private Player opponent;

    public GUI(Player p) {
        super("Checkers");
        setLocation(700, 400); //1950x
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setSize(500, 500);
        player = p;
        opponent = new Player(1, "white", "two");
        //board = new Board(p.mode, p.color);
        revive = new JButton("revive");
        move = new JButton("move");
        revive.addActionListener(this);
        move.addActionListener(this);
        setLayout(new GridLayout(2, 1, 0, 10));
        add(revive);
        add(move);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent pos) {
        Object source = pos.getSource();
        if (source == revive) {
            System.out.println("current player");
            player.getBoard().printBoard();
            System.out.println("opponent");
            opponent.getBoard().printBoard();
            player.getBoard().rotate();
            //System.out.println("current players rotated board");
            //player.getBoard().printBoard();
            //player.revive(player.board.findField(3, 0));
        }
        else if (source == move) {
            player.move(opponent, player.board.findField(0, 1), player.board.findField(0, 2));
            System.out.println("board after move");
            player.getBoard().printBoard();
        }
    }
}
