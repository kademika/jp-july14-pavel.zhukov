package com.kademika.day12.f2to3ball;


import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Balls extends JPanel {

    private static final int WIDTH = 400;
    private static final int HEIGHT = 400;

    private static final Color[] COLORS = new Color[]{
            Color.RED,
            Color.ORANGE,
            Color.YELLOW,
            Color.GREEN,
            new Color(30, 144, 255),
            Color.BLUE,
            new Color(123, 104, 238),
            Color.GRAY,
            Color.BLACK
    };

    private List<Ball> balls;
    private Random rand;

    public Balls() {
        JFrame frame = new JFrame("Balls");
        frame.setLocation(450, 150);
        frame.setMinimumSize(new Dimension(WIDTH, HEIGHT));
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.getContentPane().add(this);
        frame.pack();
        frame.setVisible(true);

        createBalls();
        action();
    }

    private void createBalls() {
        Ball ball;
        balls = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            rand = new Random();
            ball = new Ball(15, 15, WIDTH / 2 + rand.nextInt(30), rand.nextInt(HEIGHT - 40), rand.nextInt(10), COLORS[rand.nextInt(COLORS.length)]);
            ball.setNum(i);
            balls.add(ball);
        }
    }

    private void action() {
        for (Ball ball : balls) {
            new ThreadForBall(ball, WIDTH, HEIGHT).start();
        }
    }

    public void update() {
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (Ball ball : balls) {
            g.setColor(ball.getColor());
            g.fillOval(ball.getX(), ball.getY(), ball.getWidth(), ball.getHeight());
        }
    }
}
