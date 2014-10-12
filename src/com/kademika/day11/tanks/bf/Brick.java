package com.kademika.day11.tanks.bf;

import java.awt.Color;
import java.io.File;

import javax.imageio.ImageIO;

public class Brick extends AbstractBfObject {
    public Brick(int x, int y) {
        super(x, y);
        color = new Color(178, 34, 34);
        try {
            image = ImageIO.read(new File("images" + File.separator + "brick.jpg").getAbsoluteFile());
        } catch (Exception e) {
            throw new IllegalStateException("Can't find image");
        }
    }
}
