package com.kademika.day12.f7;


import java.awt.*;

public class Stair {
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
}
