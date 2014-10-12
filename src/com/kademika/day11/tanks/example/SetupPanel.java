package com.kademika.day11.tanks.example;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class SetupPanel extends JPanel {
    private MainFrame frame;
    private JButton button_StartGame;

    public SetupPanel(MainFrame frame) {
        this.frame = frame;
        setComponents();
    }

    private void setComponents() {
        button_StartGame = new JButton("Start game");
        this.add(button_StartGame);
        button_StartGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                frame.loadActionField();
                SwingWorker worker = new SwingWorker<Void, Void>() {
                    @Override
                    protected Void doInBackground() throws Exception {
                        System.out.println("RUN EDT: " + SwingUtilities.isEventDispatchThread());
//                        frame.actionField.runTheGame();
                        return null;
                    }
                };
                worker.execute();
            }
        });
    }
}