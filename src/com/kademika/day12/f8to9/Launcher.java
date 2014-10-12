package com.kademika.day12.f8to9;


import java.awt.*;

public class Launcher {
    public static Ball ball;
    public static Stair stair;

    public static final int WIDTH = 400;
    public static final int HEIGHT = 400;

    public static void main(String[] args) {

        stair = new Stair();
        new Thread(stair).start();

        ball = new Ball(10, 100, 10, Color.RED);
        new Thread(ball).start();

        BallOnStairs ballOnStairs = new BallOnStairs(ball);
        while (true) {
            ballOnStairs.repaint();
            sleep(16);
        }

    }

    public static void sleep(int timeout) {
        try {
            Thread.sleep(timeout);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
