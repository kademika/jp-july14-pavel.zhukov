package com.kademika.day12.f8to9;


import java.awt.*;

public class Stair implements Runnable {
    public int x;
    public int y;
    public int topY;
    public int height;
    public int width;
    public Color color;
    public boolean isUp;

    public Stair() {
        width = 40;
        height = 50;
        color = Color.BLUE;
        x = 160;
        y = 120;
        topY = y - height;
        isUp = false;
    }

    public void draw(Graphics g) {
        g.setColor(color);
        g.fillRect(x, y, width, height);
    }

    @Override
    public void run() {
        while (true) {
            synchronized (Launcher.stair) {
                System.out.println("Stair: Wait client for lift the top");
                try {
                    Launcher.stair.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                while (!Launcher.stair.isUp) {
                    moveStair();
                }
                synchronized (Launcher.ball) {
                    Launcher.ball.notify();
                }
                synchronized (Launcher.stair) {
                    try {
                        Launcher.stair.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                while (Launcher.stair.isUp) {
                    moveStair();
                }
                synchronized (Launcher.ball) {
                    Launcher.ball.notify();
                }
            }
        }
    }

    private void moveStair() {
        if (!Launcher.stair.isUp) {
            if (Launcher.stair.y != Launcher.stair.topY) {
                Launcher.stair.y--;
                Launcher.ball.y--;
            } else {
                Launcher.stair.isUp = true;
                System.out.println("Stair: On the top");
                synchronized (Launcher.ball) {
                    Launcher.ball.notify();
                }
            }
        } else {
            if (Launcher.stair.y != Launcher.stair.topY + Launcher.stair.height) {
                Launcher.stair.y++;
            } else {
                Launcher.stair.isUp = false;
                synchronized (Launcher.ball) {
                    Launcher.ball.notify();
                }
            }
        }
    }

}
