package com.kademika.day12.tanks.bf;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;

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
