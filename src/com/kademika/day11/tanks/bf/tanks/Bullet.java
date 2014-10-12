package com.kademika.day11.tanks.bf.tanks;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.ImageObserver;
import java.io.File;

import javax.imageio.ImageIO;

import com.kademika.day11.tanks.*;
import com.kademika.day11.tanks.bf.*;

public class Bullet implements Drawable, Destroyable {

    private int speed = 5;

    private int x;
    private int y;
    private Direction direction;
    private Image image;

    private boolean destroyed;

    public Bullet(int x, int y, Direction direction) {
        this.x = x;
        this.y = y;
        this.direction = direction;
        this.destroyed = false;
        setImages();
    }

    public void updateX(int x) {
        this.x += x;
    }

    public void updateY(int y) {
        this.y += y;
    }

    private void setImages() {
        try {
            image = ImageIO.read(new File("images" + File.separator + "bullet.jpg").getAbsoluteFile());
        } catch (Exception e) {
            throw new IllegalStateException("Can't find image");
        }
    }

    @Override
    public void draw(Graphics g) {
        if (!destroyed) {
            if (image != null) {
                g.drawImage(image, getX(), getY(), new ImageObserver() {

                    @Override
                    public boolean imageUpdate(Image img, int infoflags, int x,
                                               int y, int width, int height) {
                        // TODO Auto-generated method stub
                        return false;
                    }
                });
            } else {
                g.setColor(new Color(255, 255, 0));
                g.fillRect(this.x, this.y, 14, 14);
            }
        }
    }

    public void destroy() {
        destroyed = true;
    }

    public boolean isDestroyed() {
        return destroyed;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getSpeed() {
        return speed;
    }

    public Direction getDirection() {
        return direction;
    }

}
