package com.kademika.day13.tanks.client1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Admin on 04.11.2014.
 */
public class SetupPanel extends JPanel {

    private Client1 frame;
    private int var;
    private int tank;
    private int numPanel = 0;
    private JPanel pResult;

    public SetupPanel(Client1 client1, int var, int tank) {
        this.frame = client1;
        this.var = var;
        this.tank = tank;
        setComponents();
    }

    private void setComponents() {
        this.setLayout(new GridBagLayout());

        JLabel jLabel1 = new JLabel("Choose the type of tank");
        this.add(jLabel1, new GridBagConstraints(0, 0, 1, 1, 0, 0,
                GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL,
                new Insets(5, 15, 10, 5), 0, 0));

        String[] tanksType = new String[]{"defender", "aggressor"};

        ButtonGroup tanksGroup = new ButtonGroup();
        JPanel pTanks = new JPanel();
        JRadioButton defender = new JRadioButton(tanksType[0]);
        tanksGroup.add(defender);
        pTanks.add(defender);
        JRadioButton aggressor = new JRadioButton(tanksType[1]);
        tanksGroup.add(aggressor);
        pTanks.add(aggressor);

        String[] aggrType = new String[]{"BT7", "Tiger"};

        ButtonGroup aggressorGroup = new ButtonGroup();
        JPanel pAggressors = new JPanel();
        JRadioButton bt7 = new JRadioButton(aggrType[0]);
        aggressorGroup.add(bt7);
        pAggressors.add(bt7);
        JRadioButton tiger = new JRadioButton(aggrType[1]);
        aggressorGroup.add(tiger);
        pAggressors.add(tiger);

        pResult = new JPanel();
        pResult.setLayout(new GridBagLayout());

        defender.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                if (var == 1) {

                } else {

                }
            }
        });

        aggressor.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {

            }
        });

        this.add(pTanks, new GridBagConstraints(0, 1, 1, 1, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(5, 0, 10, 0), 0, 0));


        JLabel jLabel2 = new JLabel("or repeat the previous game");
        this.add(jLabel2, new GridBagConstraints(0, 2, 1, 1, 0, 0,
                GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL,
                new Insets(5, 5, 10, 5), 0, 0));


    }
}