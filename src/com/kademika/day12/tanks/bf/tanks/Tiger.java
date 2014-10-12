package com.kademika.day12.tanks.bf.tanks;

import com.kademika.day12.tanks.Direction;
import com.kademika.day12.tanks.bf.BattleField;
import com.kademika.day12.tanks.bf.Brick;
import com.kademika.day12.tanks.bf.Eagle;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Tiger extends AbstractTank {

    private int armor;
    private int k;
    private Action a;
    private boolean flag = true;
    private int nearX;
    private int nearY;
    private boolean near = false;

    public Tiger(BattleField bf) {
        super(bf);
        armor = 1;
        tankColor = new Color(0, 71, 171);
        towerColor = new Color(172, 225, 175);
        setImages();
        k = rand.nextInt(2) + 1;
    }

    public Tiger(BattleField bf, int x, int y, Direction direction) {
        super(bf, x, y, direction);
        armor = 1;
        tankColor = new Color(0, 71, 171);
        towerColor = new Color(172, 225, 175);
        setImages();
        k = rand.nextInt(2) + 1;
    }

    public int getArmor() {
        return armor;
    }

    public void setArmor(int armor) {
        this.armor = armor;
    }

    private void setImages() {
        images = new Image[4];
        try {
            images[0] = ImageIO.read(new File("images" + File.separator + "tiger-top.jpg")
                    .getAbsoluteFile());
            images[1] = ImageIO.read(new File("images" + File.separator + "tiger-bottom.jpg")
                    .getAbsoluteFile());
            images[2] = ImageIO.read(new File("images" + File.separator + "tiger-left.jpg")
                    .getAbsoluteFile());
            images[3] = ImageIO.read(new File("images" + File.separator + "tiger-right.jpg")
                    .getAbsoluteFile());
        } catch (IOException e) {
            throw new IllegalStateException("Can't find image");
        }
    }

    @Override
    public void destroy() {
        if (armor > 0) {
            armor--;
        } else {
            super.destroy();

        }
    }

    @Override
    public Action setUp() {
        String coords = bf.getQuadrant(getX(), getY());
        int separator = coords.indexOf("_");
        int y = Integer.parseInt(coords.substring(0, separator));
        int x = Integer.parseInt(coords.substring(separator + 1));

        if (flag) {
            if (k == 1) {
                if (y < eTankY) {
                    turn(Direction.DOWN);
                } else if (y > eTankY) {
                    turn(Direction.UP);
                } else {
                    if (x < eTankX) {
                        turn(Direction.RIGHT);
                    } else {
                        turn(Direction.LEFT);
                    }
                }
            } else {
                if (x < eTankX) {
                    turn(Direction.RIGHT);
                } else if (x > eTankX) {
                    turn(Direction.LEFT);
                } else {
                    if (y < eTankY) {
                        turn(Direction.DOWN);
                    } else {
                        turn(Direction.UP);
                    }
                }
            }
            // System.out.println(x + " " + y);

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

                } else if (scanNextQuadrant(getX(), getY(), getDirection()) == 1) {
                    a = Action.FIRE;
                    break;
                } else {
                    a = Action.MOVE;
                    break;
                }
            }
        } else {
            if (eTankY >= 5) {
                turn(Direction.DOWN);
            } else {
                turn(Direction.UP);
            }
            a = Action.MOVE;
            flag = true;
            k = 1;
        }

        if (getNextNextQuadrant(getX(), getY()) instanceof Eagle
                || getNextQuadrant(getX(), getY()) instanceof Eagle || getNextQuadrant(getX(), getY()) instanceof Brick || enemy
                || scanEnemyOnLine()) {
            a = Action.FIRE;
            return a;
        }

        scanArea(x, y);
        if (near) {
            if (y < nearY) {
                turn(Direction.DOWN);
            } else if (y > nearY) {
                turn(Direction.UP);
            } else {
                if (x < nearX) {
                    turn(Direction.RIGHT);
                } else {
                    turn(Direction.LEFT);
                }
            }
            a = Action.MOVE;
        }
        // a=Action.NONE;
        return a;
    }

    private void scanArea(int x, int y) {
        int stX = x - 1, finX = x + 1;
        int stY = y - 1, finY = y + 1;
        if (x == 0) {
            stX = x;
        } else if (x == 8) {
            finX = x;
        }
        if (y == 0) {
            stY = y;
        } else if (y == 8) {
            finY = y;
        }
        for (int i = stX; i <= finX; i++) {
            for (int j = stY; j <= finY; j++) {
                if (i == eTankX && j == eTankY) {
                    nearX = i;
                    nearY = j;
                    near = true;
                    return;
                }
            }
        }
    }
}
