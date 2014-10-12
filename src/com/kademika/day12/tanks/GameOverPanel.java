package com.kademika.day12.tanks;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameOverPanel extends JPanel {
    private GameFrame frame;

    public GameOverPanel(GameFrame frame) {
        this.frame = frame;
        setComponents();
    }

    private void setComponents() {
        this.setLayout(new GridBagLayout());

        JLabel jl = new JLabel("Start a new game?");
        this.add(jl, new GridBagConstraints(0, 0, 1, 1, 0, 0,
                GridBagConstraints.CENTER, 0,
                new Insets(5, 10, 5, 10), 0, 0));

        JButton yes = new JButton("YES");
        yes.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    frame.init();
                    frame.loadSetupPanel();
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        });

        JButton no = new JButton("NO");
        no.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(yes);
        buttonPanel.add(no);
        this.add(buttonPanel, new GridBagConstraints(0, 1, 1, 1, 0, 0,
                GridBagConstraints.CENTER, 0, new Insets(0, 10, 0, 10), 0, 0));
//        this.add(no, new GridBagConstraints(1, 1, 1, 1, 0, 0,
//                GridBagConstraints.LINE_START, 0, new Insets(0, 0, 0, 10), 0, 0));


        JLabel jLabel2 = new JLabel("or repeat the previous game");
        this.add(jLabel2, new GridBagConstraints(0, 2, 1, 1, 0, 0,
                GridBagConstraints.CENTER, 0,
                new Insets(5, 10, 10, 5), 0, 0));

        JButton repeatButton = new JButton("Repeat game");
        repeatButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    frame.loadRepeatField();
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
                SwingWorker worker = new SwingWorker<Void, Void>() {
                    @Override
                    protected Void doInBackground() throws Exception {
                        frame.actionField.repeatGame();
                        return null;
                    }
                };
                worker.execute();
            }
        });

        this.add(repeatButton, new GridBagConstraints(0, 3, 1, 1, 0, 0,
                GridBagConstraints.CENTER, 0,
                new Insets(5, 5, 10, 5), 0, 0));
    }
}
