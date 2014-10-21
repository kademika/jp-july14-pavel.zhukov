package com.kademika.day12.tanks;

import com.kademika.day12.tanks.bf.tanks.*;

import javax.swing.*;
import javax.swing.Action;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class GameFrame extends JFrame {
    ActionField actionField;
    SetupPanel setupPanel;
    GameOverPanel gameOverPanel;

    public GameFrame() throws Exception {
        System.out.println("SETUP EDT: " + SwingUtilities.isEventDispatchThread());
        init();
        loadSetupPanel();
    }

    public void init() throws Exception {
        this.setTitle("TANKS, start the game");
        this.setLocation(300, 300);
        this.setMinimumSize(new Dimension(250, 250));
        setupPanel = new SetupPanel(this);
        actionField = new ActionField(this);
        gameOverPanel = new GameOverPanel(this);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.getContentPane().add(setupPanel);
        this.setVisible(true);
    }

    public void loadSetupPanel() {
        this.getContentPane().removeAll();
        this.getContentPane().add(setupPanel);
        this.pack();
        setupPanel.setVisible(true);
        setupPanel.setFocusable(true);
    }

    public void loadActionField(int num) {
        actionField.init(num);
        setupPanel.setVisible(false);
        setupPanel.setFocusable(false);
        loadField();
    }

    public void loadRepeatField() throws Exception {
        actionField.initRepeat();
        loadField();
    }

    private void loadField() {
        this.setMinimumSize(new Dimension(576 + 8, 576 + 32));
        this.getContentPane().removeAll();
        this.getContentPane().add(actionField);
        this.revalidate();
        this.pack();
        actionField.setVisible(true);
        this.setVisible(true);
        actionField.setFocusable(true);
        actionField.getKeyAdapter();

//        this.addKeyListener(actionField.getKeyAdapter());

    }

    public void getGameOver() {
        this.setMinimumSize(new Dimension(250, 250));
        this.getContentPane().removeAll();
        this.getContentPane().add(gameOverPanel);
        this.revalidate();
        this.pack();
        gameOverPanel.setVisible(true);
    }
}
