package com.kademika.day11.tanks.bf;

import java.awt.Color;
import java.io.File;

import javax.imageio.ImageIO;

public class Empty extends AbstractBfObject {

    public Empty(int x, int y) {
        super(x, y);
        color = new Color(0, 0, 0);
        try {
            image = ImageIO.read(new File("images" + File.separator + "empty.jpg").getAbsoluteFile());
        } catch (Exception e) {
            throw new IllegalStateException("Can't find image");
        }
    }
}
