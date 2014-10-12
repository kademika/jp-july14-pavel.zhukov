package com.kademika.day12.f6;

import java.awt.*;

public class Gates {
    public int topX;
    public int topY;
    public boolean isOpen;
    public int openTopY;
    public int openBottomY;
    public int bottomX;
    public int bottomY;
    public Color color;
    public int doorHeight;
    public int topWidth;
    public int bottomWidth;

    public Gates() {
        color = Color.RED;
        topX = 160;
        topY = 80;
        bottomX = 160;
        bottomY = 120;
        doorHeight = 40;
        openTopY = 40;
        openBottomY = 160;
        topWidth = 30;
        bottomWidth = 30;
        isOpen = false;
    }
}
