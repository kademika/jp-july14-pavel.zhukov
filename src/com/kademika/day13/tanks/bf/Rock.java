package com.kademika.day13.tanks.bf;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;

public class Rock extends AbstractBfObject {
    public Rock(int x, int y) {
        super(x, y);
        color = new Color(59, 68, 75);
        try {
            image = ImageIO.read(new File("images" + File.separator + "rock.jpg").getAbsoluteFile());
        } catch (Exception e) {
            throw new IllegalStateException("Can't find image");
        }
    }
}
