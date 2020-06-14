package com.mycompany.app;

import io.netty.channel.Channel;
import io.netty.channel.group.ChannelGroup;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JoinServer extends JFrame implements ActionListener {
    private JTextField textField;
    private JButton send;
    private JButton back;
    private GameClient gc;
    private Thread gameClientThread;
    private TestClass resp = null;

    public JoinServer() {
        super("Checkers");
        gc = new GameClient(this);

        Runnable r = new Runnable() {
            public void run() {
                try {
                    gc.run();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };

        gameClientThread = new Thread(r);
        gameClientThread.start();

        setLocation(700, 400); //1950x
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setSize(500, 500);
        textField = new JTextField("This is a text", 20);
        send = new JButton("Send");
        back = new JButton("Back");
        send.addActionListener(this);
        back.addActionListener(this);
        setLayout(new GridLayout(3, 1, 0, 10));
        add(textField);
        add(send);
        add(back);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent pos) {
        Object source = pos.getSource();
        if (source == send) {
            gc.writeMessage(textField.getText());
        }
        else if(source == back) {
            new NewLoadGame();
            dispose();
        }
    }

    public void setText(TestClass t) {
        textField.setText(t.getData());
        resp = t;

        System.out.println("Aktywni klienci:");
        String[] channels = t.getChannels();
        for (String c: channels) {
            if(c != null)
                System.err.println(c);
        }
    }
}