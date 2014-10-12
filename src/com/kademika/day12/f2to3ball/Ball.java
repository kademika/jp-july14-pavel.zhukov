package com.kademika.day12.f2to3ball;


import java.awt.*;

public class Ball {
    private int x;
    private int y;
    private int width;
    private int height;
    private int speed;
    private Color color;
    private int num;

    public Ball(int width, int height, int startX, int startY, int speed, Color color) {
        this.width = width;
        this.height = height;
        this.x = startX;
        this.y = startY;
        this.color = color;
        this.speed = speed;

    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Color getColor() {
        return color;
    }

    public int getSpeed() {
        return speed;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public int getNum() {
        return num;
    }

    public void step(int stepX, int stepY) {
        x += stepX;
        y += stepY;
    }
}
