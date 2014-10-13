package com.kademika.day12.tanks;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Admin on 02.09.2014.
 */
public class SetupPanel extends JPanel {

    private GameFrame frame;
    public int idTank = 1;

    public SetupPanel(GameFrame frame) {
        this.frame = frame;
        setComponents();
    }

    private void setComponents() {
        this.setLayout(new GridBagLayout());

        JLabel jLabel1 = new JLabel("Choose the type of tank");
        this.add(jLabel1, new GridBagConstraints(0, 0, 1, 1, 0, 0,
                GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL,
                new Insets(5, 15, 10, 5), 0, 0));

        String[] tanksType = new String[]{"BT7", "Tiger"};

        ButtonGroup tanksGroup = new ButtonGroup();
        JPanel pTanks = new JPanel();
        JRadioButton bt7 = new JRadioButton(tanksType[0]);
        tanksGroup.add(bt7);
        pTanks.add(bt7);
        JRadioButton tiger = new JRadioButton(tanksType[1]);
        tanksGroup.add(tiger);
        pTanks.add(tiger);

        bt7.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                idTank = 1;
                frame.loadActionField(idTank);
                System.out.println("RUN EDT: " + SwingUtilities.isEventDispatchThread());
                SwingWorker worker = new SwingWorker<Void, Void>() {
                    @Override
                    protected Void doInBackground() throws Exception {
                        frame.actionField.runTheGame();
//                        frame.actionField.setVisible(true);
//                        frame.actionField.setFocusable(true);
//                        frame.actionField.getKeyAdapter();
                        return null;
                    }
                };
                worker.execute();
            }
        });

        tiger.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                idTank = 2;
                frame.loadActionField(idTank);
                System.out.println("RUN EDT: " + SwingUtilities.isEventDispatchThread());
                SwingWorker worker = new SwingWorker<Void, Void>() {
                    @Override
                    protected Void doInBackground() throws Exception {
                        frame.actionField.runTheGame();
//                        frame.actionField.setVisible(true);
//                        frame.actionField.setFocusable(true);
//                        frame.actionField.getKeyAdapter();
                        return null;
                    }
                };
                worker.execute();
            }
        });

        this.add(pTanks, new GridBagConstraints(0, 1, 1, 1, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(5, 0, 10, 0), 0, 0));

        JLabel jLabel2 = new JLabel("or repeat the previous game");
        this.add(jLabel2, new GridBagConstraints(0, 2, 1, 1, 0, 0,
                GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL,
                new Insets(5, 5, 10, 5), 0, 0));

        JButton repeatButton = new JButton("Repeat game");
        repeatButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    frame.loadRepeatField();
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
                System.out.println("RUN EDT: " + SwingUtilities.isEventDispatchThread());
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
                GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL,
                new Insets(5, 5, 10, 5), 0, 0));
    }
}
