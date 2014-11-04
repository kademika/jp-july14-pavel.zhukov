package com.kademika.day13.tanks.bf.tanks;

import com.kademika.day13.tanks.Direction;
import com.kademika.day13.tanks.bf.BattleField;
import com.kademika.day13.tanks.bf.Brick;
import com.kademika.day13.tanks.bf.Eagle;
import com.kademika.day13.tanks.bf.Rock;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class BT7 extends AbstractTank {

    private int k;
    private Action a;
    private boolean flag = true;
    private int eY = Integer.parseInt(bf.eagle.split("_")[0]);
    private int eX = Integer.parseInt(bf.eagle.split("_")[1]);

    public BT7(BattleField bf, int x, int y, Direction direction) {
        super(bf, x, y, direction);
        movePath = 2;
        tankColor = new Color(3, 192, 60);
        towerColor = new Color(23, 114, 69);
        setImages();
        k = rand.nextInt(2) + 1;
        step = 4;
    }

    public BT7(BattleField bf) {
        super(bf);
        movePath = 2;
        tankColor = new Color(3, 192, 60);
        towerColor = new Color(23, 114, 69);
        setImages();
        k = rand.nextInt(2) + 1;
        step = 4;
    }

    private void setImages() {
        images = new Image[4];
        try {
            images[0] = ImageIO.read(new File("images" + File.separator + "bt7-top.jpg").getAbsoluteFile());
            images[1] = ImageIO.read(new File("images" + File.separator + "bt7-bottom.jpg")
                    .getAbsoluteFile());
            images[2] = ImageIO
                    .read(new File("images" + File.separator + "bt7-left.jpg").getAbsoluteFile());
            images[3] = ImageIO.read(new File("images" + File.separator + "bt7-right.jpg")
                    .getAbsoluteFile());
        } catch (IOException e) {
            throw new IllegalStateException("Can't find image");
        }
    }

    @Override
    public Action setUp() {
        String coords = bf.getQuadrant(getX(), getY());
        int separator = coords.indexOf("_");
        int y = Integer.parseInt(coords.substring(0, separator));
        int x = Integer.parseInt(coords.substring(separator + 1));
        movePath = 2;

        if (getNextNextQuadrant(getX(), getY()) instanceof Rock
                || getNextQuadrant(getX(), getY()) instanceof Rock
                || getNextNextQuadrant(getX(), getY()) instanceof Brick
                || y == 7 || enemy) {
            movePath = 1;
        }

        if (flag) {
            if (k == 1) {
                if (y < eY) {
                    turn(Direction.DOWN);
                } else if (y > eY) {
                    turn(Direction.UP);
                } else {
                    if (x < eX) {
                        turn(Direction.RIGHT);
                    } else {
                        turn(Direction.LEFT);
                    }
                }
            } else {
                if (x < eX) {
                    turn(Direction.RIGHT);
                } else if (x > eX) {
                    turn(Direction.LEFT);
                } else {
                    if (y < eY) {
                        turn(Direction.DOWN);
                    } else {
                        turn(Direction.UP);
                    }
                }
            }

            while (true) {
                if (scanNextQuadrant(getX(), getY(), getDirection()) == 2) {
                    if (getDirection() == Direction.LEFT
                            || getDirection() == Direction.RIGHT) {
                        if (y == 0) {
                            turn(Direction.DOWN);
                        } else if (y == 8) {
                            turn(Direction.UP);
                        } else {
                            turn(Direction.DOWN);
                        }
                    } else {
                        if (x == 0) {
                            turn(Direction.RIGHT);
                        } else if (x == 8) {
                            turn(Direction.LEFT);
                        } else if (k == 1) {
                            turn(Direction.RIGHT);
                        } else {
                            turn(Direction.LEFT);
                        }
                    }
                    flag = false;
                    movePath = 1;
                } else if (scanNextQuadrant(getX(), getY(), getDirection()) == 1) {
                    a = Action.FIRE;
                    break;
                } else {
                    a = Action.MOVE;
                    break;
                }
            }
        } else {
            if (eY >= 5) {
                turn(Direction.DOWN);
            } else {
                turn(Direction.UP);
            }
            a = Action.MOVE;
            flag = true;
            k = 1;
        }

        if (getNextNextQuadrant(getX(), getY()) instanceof Eagle
                || getNextQuadrant(getX(), getY()) instanceof Eagle || enemy) {
            a = Action.FIRE;
        }

        return a;
    }
}
