package com.kademika.day13.tanks.bf;

import java.awt.*;
import java.awt.image.ImageObserver;

public class AbstractBfObject implements Drawable, Destroyable {

    private int x;
    private int y;

    protected Color color;
    protected Image image;

    private boolean isDestroyed = false;
    protected static final int QUADRANT_SIZE = 64;

    public AbstractBfObject(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public void destroy() {
        isDestroyed = true;
    }

    @Override
    public void draw(Graphics g) {
        if (!isDestroyed) {
            if (image != null) {
                g.drawImage(image, getX(), getY(), getX() + 64, getY() + 64,
                        getX(), getY(), getX() + 64, getY() + 64,
                        new ImageObserver() {

                            @Override
                            public boolean imageUpdate(Image img,
                                                       int infoflags, int x, int y, int width,
                                                       int height) {
                                // TODO Auto-generated method stub
                                return false;
                            }
                        });
            } else {
                g.setColor(this.color);
                g.fillRect(this.getX(), this.getY(), 64, 64);
            }
        }
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean isDestroyed() {
        return isDestroyed;
    }
}
