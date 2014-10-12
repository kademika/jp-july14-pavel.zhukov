package com.kademika.day11.tanks.bf;

import java.awt.Color;
import java.io.File;

import javax.imageio.ImageIO;

public class Eagle extends AbstractBfObject {

    public Eagle(int x, int y) {
        super(x, y);
        color = new Color(255, 215, 0);
        try {
            image = ImageIO.read(new File("images" + File.separator + "eagle.jpg").getAbsoluteFile());
        } catch (Exception e) {
            throw new IllegalStateException("Can't find image");
        }
    }

}
