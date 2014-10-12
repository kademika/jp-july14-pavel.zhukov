package com.kademika.day12.f8to9;

import java.awt.*;

public class Ball implements Runnable {
    public int x;
    public int y;
    public int radius;
    public Color color;

    public Ball(int x, int y, int radius, Color color) {
        this.x = x;
        this.y = y;
        this.radius = radius;
        this.color = color;
    }

    public void draw(Graphics g) {
        g.setColor(color);
        g.fillOval(x, y, radius * 2, radius * 2);
    }

    @Override
    public void run() {
        while (true) {
            synchronized (Launcher.ball) {
                while (Launcher.ball.x < Launcher.WIDTH - Launcher.ball.radius * 4) {
                    if (Launcher.ball.x == Launcher.stair.x + Launcher.ball.radius && !Launcher.stair.isUp) {
                        synchronized (Launcher.stair) {
                            System.out.println("Ball: Arrived at the lift");
                            Launcher.stair.notify();
                        }
                        synchronized (Launcher.ball) {
                            System.out.println(" and waiting stair to lift to the top.");
                            try {
                                Launcher.ball.wait();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }

                        }
                    }
                    if (Launcher.ball.x == Launcher.stair.x + Launcher.stair.width + Launcher.ball.radius && Launcher.stair.isUp) {
                        synchronized (Launcher.stair) {
                            Launcher.stair.notify();
                            System.out.println("Ball: Wait that lift arrived down");
                        }

                        synchronized (Launcher.ball) {
                            System.out.println(" and waiting stair arrive down.");
                            try {
                                Launcher.ball.wait();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                    Launcher.ball.x++;
                    Launcher.sleep(5);
                }
                while (Launcher.ball.x == Launcher.WIDTH - Launcher.ball.radius * 4 && Launcher.ball.y != Launcher.HEIGHT - Launcher.ball.radius * 6) {
                    Launcher.ball.y++;
                    Launcher.sleep(5);
                }
                while (Launcher.ball.y == Launcher.HEIGHT - Launcher.ball.radius * 6 && Launcher.ball.x != 2) {
                    Launcher.ball.x--;
                    Launcher.sleep(5);
                }
                while (Launcher.ball.x == 2 && Launcher.ball.y != 100) {
                    Launcher.ball.y--;
                    Launcher.sleep(5);
                }
                while (Launcher.ball.x != 10 && Launcher.ball.y == 100) {
                    Launcher.ball.x++;
                    Launcher.sleep(5);
                }
            }
        }
    }
}
