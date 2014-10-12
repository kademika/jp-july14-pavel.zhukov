package com.kademika.day12.tanks.bf;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;

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
