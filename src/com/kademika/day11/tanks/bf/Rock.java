package com.kademika.day11.tanks.bf;

import java.awt.Color;
import java.io.File;

import javax.imageio.ImageIO;

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
