package com.kademika.day12.f7;


import javax.swing.*;
import java.awt.*;

public class BallOnStairs extends JPanel {
    private static final int WIDTH = 400;
    private static final int HEIGHT = 400;

    private Ball ball;
    private Stair stair;

    public static void main(String[] args) {
        new BallOnStairs();
    }

    public BallOnStairs() {
        JFrame frame = new JFrame("Ball on Stair");
        frame.setLocation(450, 150);
        frame.setMinimumSize(new Dimension(WIDTH, HEIGHT));
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.getContentPane().add(this);
        frame.pack();
        frame.setVisible(true);

        stair = new Stair();
//        ball = new Ball(10, 100, 10, Color.RED);

        upStair();
        actionBall();

        while (true) {
//            upStair();
            actionBall();

            repaint();
            sleep(1000 / 60);
        }
    }

    private void upStair() {

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    synchronized (stair) {
                        System.out.println("Stair: Wait client for lift the top");
                        stair.wait();
                    }
                    while (!stair.isUp) {
                        moveStair();
                    }
                    synchronized (ball) {
                        ball.notify();
                    }
                    synchronized (stair) {
                        stair.wait();
                    }
                    while (stair.isUp) {
                        moveStair();
                    }
                    synchronized (ball) {
                        ball.notify();
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        ).start();
    }

    private void moveStair() {
        if (!stair.isUp) {
            if (stair.y != stair.topY) {
                stair.y--;
                ball.y--;
            } else {
                stair.isUp = true;
                System.out.println("Stair: On the top");
                synchronized (ball) {
                    ball.notify();
                }
            }
        } else {
            if (stair.y != stair.topY + stair.height) {
                stair.y++;
            } else {
                stair.isUp = false;
                synchronized (ball) {
                    ball.notify();
                }
            }
        }
    }

    private void actionBall() {
        ball = new Ball(10, 100, 10, Color.RED);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    moveBall();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void moveBall() throws InterruptedException {
        while (ball.x <= WIDTH - ball.radius * 4) {
            if (ball.x == stair.x + ball.radius && !stair.isUp) {
                synchronized (stair) {
                    stair.notify();
                    System.out.println("Ball: Arrived at the lift");

                }

                synchronized (ball) {
                    System.out.println(" and waiting stair to lift to the top.");
                    ball.wait();

                }
            }

            if (ball.x == stair.x + stair.width + ball.radius && stair.isUp) {
                synchronized (stair) {
                    stair.notify();
                    System.out.println("Ball: Wait that lift arrived down");
                }

                synchronized (ball) {
                    System.out.println(" and waiting stair arrive down.");
                    ball.wait();
                }
            }

            ball.x++;
            sleep(5);
        }
        System.out.println("Ball: at home");
        while (ball.x != 10 && ball.y != 100) {
            if (ball.x == WIDTH - ball.radius * 4 && ball.y != HEIGHT - ball.radius * 4) {
                ball.y++;
                sleep(5);
            }
        }
    }

    private void sleep(int timeout) {
        try {
            Thread.sleep(timeout);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(Color.BLACK);
        g.drawLine(0, 120, WIDTH / 2 - stair.width, 120);
        g.drawLine(WIDTH / 2, 120, WIDTH / 2, 120 - stair.height);
        g.drawLine(WIDTH / 2, 70, WIDTH, 70);

        g.setColor(ball.color);
        g.fillOval(ball.x, ball.y, ball.radius * 2, ball.radius * 2);

        g.setColor(stair.color);
        g.fillRect(stair.x, stair.y, stair.width, stair.height);

    }
}
