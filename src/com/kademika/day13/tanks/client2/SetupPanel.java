package com.kademika.day13.tanks.client2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Admin on 04.11.2014.
 */
public class SetupPanel extends JPanel {

    private Client2 frame;
    private int var;
    private int tank;
    private int numPanel = 0;
    private JPanel pTanks;
    private JPanel pResult;
    private JPanel setupPanel;
    private JPanel pButtonPanel;
    private JLabel jWait;
    public boolean startFlag = false;

    public SetupPanel(Client2 client2, int var, int tank) {
        this.frame = client2;
        this.var = var;
        this.tank = tank;
        setComponents();
    }

    private void setComponents() {
        setupPanel = this;
        setupPanel.setLayout(new GridBagLayout());

        JLabel jLabel1 = new JLabel("Choose the type of tank");
        setupPanel.add(jLabel1, new GridBagConstraints(0, 0, 1, 1, 0, 0,
                GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL,
                new Insets(5, 15, 10, 5), 0, 0));

        String[] tanksType = new String[]{"defender", "aggressor"};

        ButtonGroup tanksGroup = new ButtonGroup();
        pTanks = new JPanel();
        JRadioButton defender = new JRadioButton(tanksType[0]);
        tanksGroup.add(defender);
        pTanks.add(defender);
        JRadioButton aggressor = new JRadioButton(tanksType[1]);
        tanksGroup.add(aggressor);
        pTanks.add(aggressor);

//        String[] aggrType = new String[]{"BT7", "Tiger"};

//        aggressorGroup = new ButtonGroup();
//        pAggressors = new JPanel();
//        JRadioButton bt7 = new JRadioButton(aggrType[0]);
//        aggressorGroup.add(bt7);
//        pAggressors.add(bt7);
//        JRadioButton tiger = new JRadioButton(aggrType[1]);
//        aggressorGroup.add(tiger);
//        pAggressors.add(tiger);

        pResult = new JPanel();
        pResult.setLayout(new GridBagLayout());

        defender.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                if (var == 1) {
                    jWait = new JLabel("Your choice accepted! Wait start the game");
                    frame.setNumTank(1);
                    jWait.setVisible(true);
                    setupPanel.add(jWait, new GridBagConstraints(0, 3, 1, 1, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(5, 0, 10, 0), 0, 0));
                    pResult.setVisible(false);
                    pTanks.setVisible(false);
                    frame.getContentPane().removeAll();
                    frame.getContentPane().add(setupPanel);
                    frame.revalidate();
                    frame.pack();
                } else {
                    if (tank != 1) {
                        jWait = new JLabel("Your choice accepted! Wait start the game");
                        setupPanel.add(jWait, new GridBagConstraints(0, 3, 1, 1, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(5, 0, 10, 0), 0, 0));
                        frame.setNumTank(1);
                        pResult.setVisible(false);
                        pTanks.setVisible(false);
                        jWait.setVisible(true);
                        frame.getContentPane().removeAll();
                        frame.getContentPane().add(setupPanel);
                        frame.revalidate();
                        frame.pack();
//                        JLabel jLabel2 = new JLabel("Choose type of aggressor");
//                        pResult.add(jLabel2, new GridBagConstraints(0, 0, 1, 1, 0, 0,
//                                GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL,
//                                new Insets(5, 5, 10, 5), 0, 0));
//                        pResult.add(pAggressors, new GridBagConstraints(0, 1, 1, 1, 0, 0,
//                                GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL,
//                                new Insets(5, 5, 10, 5), 0, 0));
//                        pResult.setVisible(true);
                    } else {
                        jWait = new JLabel("You must change your selection!");
                        jWait.setVisible(true);
                        setupPanel.add(jWait, new GridBagConstraints(0, 3, 1, 1, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(5, 0, 10, 0), 0, 0));
                    }
                }
            }
        });

        aggressor.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                if (var == 1) {
                    jWait = new JLabel("Your choice accepted! Wait start the game");
                    setupPanel.add(jWait, new GridBagConstraints(0, 3, 1, 1, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(5, 0, 10, 0), 0, 0));
                    pResult.setVisible(false);
                    pTanks.setVisible(false);
                    jWait.setVisible(true);
                    frame.setNumTank(2);
                    frame.getContentPane().removeAll();
                    frame.getContentPane().add(setupPanel);
                    frame.revalidate();
                    frame.pack();
                } else {
                    if (tank == 1) {
                        jWait = new JLabel("Your choice accepted! Wait start the game");
                        setupPanel.add(jWait, new GridBagConstraints(0, 3, 1, 1, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(5, 0, 10, 0), 0, 0));
                        pResult.setVisible(false);
                        pTanks.setVisible(false);
                        jWait.setVisible(true);
                        frame.setNumTank(2);
                        frame.getContentPane().removeAll();
                        frame.getContentPane().add(setupPanel);
                        frame.revalidate();
                        frame.pack();
                    } else {
                        jWait = new JLabel("You must change your selection!");
                        jWait.setVisible(true);
                        setupPanel.add(jWait, new GridBagConstraints(0, 3, 1, 1, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(5, 0, 10, 0), 0, 0));
                    }
                }
            }
        });

//        bt7.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent arg0) {
//                jWait = new JLabel("Your choice accepted! Wait start the game");
//                frame.setNumTank(2);
//                jWait.setVisible(true);
//                pTanks.setVisible(false);
//                setupPanel.add(jWait, new GridBagConstraints(0, 3, 1, 1, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(5, 0, 10, 0), 0, 0));
//                frame.getContentPane().removeAll();
//                frame.getContentPane().add(setupPanel);
//                frame.revalidate();
//                frame.pack();
//            }
//        });
//
//        tiger.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent arg0) {
//                jWait = new JLabel("Your choice accepted! Wait start the game");
//                frame.setNumTank(3);
//                jWait.setVisible(true);
//                pTanks.setVisible(false);
//                setupPanel.add(jWait, new GridBagConstraints(0, 3, 1, 1, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(5, 0, 10, 0), 0, 0));
//                frame.getContentPane().removeAll();
//                frame.getContentPane().add(setupPanel);
//                frame.revalidate();
//                frame.pack();
//            }
//        });
        setupPanel.add(pTanks, new GridBagConstraints(0, 1, 1, 1, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(5, 0, 10, 0), 0, 0));
        setupPanel.add(pResult, new GridBagConstraints(0, 2, 1, 1, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(5, 0, 10, 0), 0, 0));

        pButtonPanel = new JPanel();
        setupPanel.add(pButtonPanel, new GridBagConstraints(0, 3, 1, 1, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(5, 0, 10, 0), 0, 0));

        JButton startButton = new JButton("Start");
        pButtonPanel.add(startButton, new GridBagConstraints(0, 0, 1, 1, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(5, 0, 10, 0), 0, 0));
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingWorker worker = new SwingWorker<Void, Void>() {
                    @Override
                    protected Void doInBackground() throws Exception {
//                        frame.actionField.runTheGame();
//                        frame.actionField.setVisible(true);
//                        frame.actionField.setFocusable(true);
//                        frame.actionField.getKeyAdapter();
                        return null;
                    }
                };
                worker.execute();
            }
        });
        pButtonPanel.setVisible(false);
//        jWait = new JLabel("");

//        jWait.setVisible(false);
    }

    public void startGame() {
        pButtonPanel.setVisible(true);
        setupPanel.add(pButtonPanel, new GridBagConstraints(0, 4, 1, 1, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(5, 0, 10, 0), 0, 0));
        frame.getContentPane().removeAll();
        frame.getContentPane().add(setupPanel);
        frame.revalidate();
        frame.pack();

    }
}

