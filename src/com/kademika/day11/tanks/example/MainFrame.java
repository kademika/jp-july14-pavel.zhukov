package com.kademika.day11.tanks.example;


import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import com.kademika.day11.tanks.example.ActionField;
import com.kademika.day11.tanks.bf.BattleField;

public class MainFrame extends JFrame {
    ActionField actionField;
    SetupPanel setupPanel;

    public MainFrame() throws Exception {
        System.out.println("SETUP EDT: " + SwingUtilities.isEventDispatchThread());
        setupFrame();
        loadSetupPanel();
    }

    private void setupFrame() throws Exception {
        this.setTitle("Tanks");
        this.setLocation(750, 150);
//        this.setMinimumSize(new Dimension(BattleField.bfWidth, BattleField.bfHeight + 22));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        actionField = new ActionField();
        setupPanel = new SetupPanel(this);
        this.getContentPane().add(setupPanel);
        this.setVisible(true);
    }

    public void loadSetupPanel() {
        this.getContentPane().removeAll();
        this.getContentPane().add(setupPanel);
        this.pack();
        setupPanel.setVisible(true);
    }

    public void loadActionField() {
//        actionField.initialize();
        this.getContentPane().removeAll();
        this.getContentPane().add(actionField);
        this.revalidate();
        this.pack();
        actionField.setVisible(true);
    }
}
