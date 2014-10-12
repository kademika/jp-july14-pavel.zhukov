package com.kademika.day12.tanks.bf;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;

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
