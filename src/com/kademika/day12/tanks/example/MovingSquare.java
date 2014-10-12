package com.kademika.day12.tanks.example;

import javax.swing.SwingUtilities;
import javax.swing.JFrame;

public class MovingSquare {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame frm = new JFrame("Moving Square");
                frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frm.add(new PaintPanel());
                frm.pack();
                frm.setVisible(true);
            }
        });
    }
}