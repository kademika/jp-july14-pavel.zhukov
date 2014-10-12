package com.kademika.day11.tanks.bf;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Composite;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.ImageObserver;
import java.io.File;

import javax.imageio.ImageIO;

public class Water extends AbstractBfObject {

    public Water(int x, int y) {
        super(x, y);
        color = new Color(0, 191, 255);
        try {
            image = ImageIO.read(new File("images" + File.separator + "water.jpg").getAbsoluteFile());
        } catch (Exception e) {
            throw new IllegalStateException("Can't find image");
        }
    }

    @Override
    public void draw(Graphics g) {
        Graphics2D g2D = (Graphics2D) g;
        Composite org = g2D.getComposite();
        Composite tr = AlphaComposite
                .getInstance(AlphaComposite.SRC_OVER, 0.5f);
        g2D.setComposite(tr);
        g.drawImage(this.image, getX(), getY(), getX() + QUADRANT_SIZE, getY()
                + QUADRANT_SIZE, getX(), getY(), getX() + QUADRANT_SIZE, getY()
                + QUADRANT_SIZE, new ImageObserver() {

            @Override
            public boolean imageUpdate(Image img, int infoflags, int x, int y,
                                       int width, int height) {
                // TODO Auto-generated method stub
                return false;
            }
        });
        g2D.setComposite(org);
    }
}
