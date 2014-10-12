package com.kademika.day12.f8to9;


import javax.swing.*;
import java.awt.*;

public class BallOnStairs extends JPanel {
    private static final int WIDTH = 400;
    private static final int HEIGHT = 400;

    public BallOnStairs(Ball ball) {
        JFrame frame = new JFrame("Ball on Stair");
        frame.getContentPane().add(this);
        frame.setLocation(450, 150);
        frame.setMinimumSize(new Dimension(WIDTH, HEIGHT));
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.getContentPane().add(this);
        frame.pack();
        frame.setVisible(true);

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
        Launcher.ball.draw(g);
        Launcher.stair.draw(g);
    }

    public void draw(Graphics g) {
        g.setColor(Color.BLACK);
        g.drawLine(20, 120, WIDTH / 2 - 40, 120);
        g.drawLine(WIDTH / 2, 120, WIDTH / 2, 120 - 50);
        g.drawLine(WIDTH / 2, 70, WIDTH - 10 * 4, 70);
    }
}
