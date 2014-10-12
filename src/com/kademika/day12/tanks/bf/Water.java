package com.kademika.day12.tanks.bf;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.ImageObserver;
import java.io.File;

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
