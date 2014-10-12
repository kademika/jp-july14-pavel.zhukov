package com.kademika.day11.tanks.bf.tanks;

import java.awt.Color;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.kademika.day11.tanks.*;
import com.kademika.day11.tanks.bf.*;

public class T34 extends AbstractTank {

    private Action a;
    private int edX;
    private int edY;
    private boolean ed = false;
    private int nearX;
    private int nearY;
    private boolean near = false;
    private int k = 1;
    private int t = 0;
    private int startX = 4;
    private int startY = 6;
    private int eY = Integer.parseInt(bf.eagle.split("_")[0]);
    private int eX = Integer.parseInt(bf.eagle.split("_")[1]);

    // private Object[] actions = new Object[] { Action.FIRE, Direction.UP,
    // Action.FIRE, Action.MOVE, Action.FIRE, Direction.RIGHT,
    // Action.FIRE, Action.MOVE, Action.MOVE, Direction.UP, Action.FIRE,
    // Action.MOVE };

    private int step = 0;

    public T34(BattleField bf) {
        super(bf, 128, 512, Direction.UP);
        tankColor = new Color(215, 255, 0);
        towerColor = new Color(86, 3, 25);
        setImages();
    }

    public T34(BattleField bf, int x, int y, Direction direction) {
        super(bf, x, y, direction);
        tankColor = new Color(215, 255, 0);
        towerColor = new Color(86, 3, 25);
        setImages();
    }

    @Override
    public Action setUp() {
        String coords = bf.getQuadrant(getX(), getY());
        int separator = coords.indexOf("_");
        int y = Integer.parseInt(coords.substring(0, separator));
        int x = Integer.parseInt(coords.substring(separator + 1));

        scanEagleDanger();

        if (t == 0) {
            if (k == 1) {
                if (y < startY) {
                    turn(Direction.DOWN);
                } else if (y > startY) {
                    turn(Direction.UP);
                } else {
                    if (x < startX) {
                        turn(Direction.RIGHT);
                    } else {
                        turn(Direction.LEFT);
                    }
                }
            } else {
                if (x < startX) {
                    turn(Direction.RIGHT);
                } else if (x > startX) {
                    turn(Direction.LEFT);
                } else {
                    if (y < startY) {
                        turn(Direction.DOWN);
                    } else {
                        turn(Direction.UP);
                    }
                }
            }

            while (true) {
                if (scanNextQuadrant(getX(), getY(), getDirection()) == 2
                        || scanNextQuadrant(getX(), getY(), getDirection()) == 3) {
                    if (getDirection() == Direction.LEFT
                            || getDirection() == Direction.RIGHT) {
                        if (y == 0) {
                            turn(Direction.DOWN);
                        } else if (y == 8) {
                            turn(Direction.UP);
                        } else {
                            turn(Direction.UP);
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
                    // flag = false;

                } else if (scanNextQuadrant(getX(), getY(), getDirection()) == 1) {
                    a = Action.FIRE;
                    break;
                } else {
                    a = Action.MOVE;
                    break;
                }
                k = 2;
            }
            if (nX == startX && nY == startY) {
                t++;
                k = 1;
            }
        } else {
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
            } else if (ed) {
                if (x < edX) {
                    turn(Direction.RIGHT);
                } else if (x > edX) {
                    turn(Direction.LEFT);
                } else {
                    if (y < edY) {
                        turn(Direction.DOWN);
                    } else if (y > edY) {
                        turn(Direction.UP);
                    }
                }
            } else {
                a = Action.NONE;
                return a;
            }
            while (true) {
                if (scanNextQuadrant(getX(), getY(), getDirection()) == 2
                        || scanNextQuadrant(getX(), getY(), getDirection()) == 3) {
                    if (getDirection() == Direction.LEFT
                            || getDirection() == Direction.RIGHT) {
                        if (y == 0) {
                            turn(Direction.DOWN);
                        } else if (y == 8) {
                            turn(Direction.UP);
                        } else {
                            turn(Direction.UP);
                        }
                    } else {
                        if (x == 0) {
                            turn(Direction.RIGHT);
                        } else if (x == 8) {
                            turn(Direction.LEFT);
                        } else if (x < eTankX) {
                            turn(Direction.RIGHT);
                        } else {
                            turn(Direction.LEFT);
                        }
                    }
                    // flag = false;

                } else if (scanNextQuadrant(getX(), getY(), getDirection()) == 1) {
                    a = Action.FIRE;
                    break;
                } else {
                    a = Action.MOVE;
                    break;
                }
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
            } else if (ed) {
                if (x < edX) {
                    turn(Direction.RIGHT);
                } else if (x > edX) {
                    turn(Direction.LEFT);
                } else {
                    if (y < edY) {
                        turn(Direction.DOWN);
                    } else if (y > edY) {
                        turn(Direction.UP);
                    }
                }
            }

            if (enemy || scanEnemyOnLine()) {
                a = Action.FIRE;
                return a;
            }
        }
        return a;
    }

    private void scanEagleDanger() {
        for (int i = eY; i >= eY - 3; i--) {
            for (int j = 0; j <= 8; j++) {
                if (i == eTankY && j == eTankX) {
                    edX = i;
                    edY = j;
                    ed = true;
                }
            }
        }
    }

    private void scanArea(int x, int y) {
        int stX = x - 4, finX = x + 4;
        int stY = y - 4, finY = y + 4;
        if (x == 0 || x == 1 || x == 2 || x == 3) {

            stX = x;
        } else if (x == 8 || x == 7 || x == 6 || x == 5) {
            finX = x;
        }
        if (y == 0 || y == 1 || y == 2 || y == 3) {
            stY = y;
        } else if (y == 8 || y == 7 || y == 6 || y == 5) {
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

    public int scanNextQuadrant(int x, int y, Direction direction) {
        turn(direction);
        if (getNextQuadrant(x, y) instanceof Brick) {
            if ((nX == 3 && nY == 8) || (nX == 4 && nY == 8)
                    || (nX == 5 && nY == 8) || (nX == 3 && nY == 7)
                    || (nX == 4 && nY == 7) || (nX == 5 && nY == 7)) {
                return 3;
            }
            return 1;
        }
        if (getNextQuadrant(x, y) instanceof Rock) {
            if ((nX == 3 && nY == 8) || (nX == 4 && nY == 8)
                    || (nX == 5 && nY == 8) || (nX == 3 && nY == 7)
                    || (nX == 4 && nY == 7) || (nX == 5 && nY == 7)) {
                return 3;
            }
            return 2;
        }
        return 0;
    }

    private void setImages() {
        images = new Image[4];
        try {
            images[0] = ImageIO.read(new File("images" + File.separator + "defender-top.jpg")
                    .getAbsoluteFile());
            images[1] = ImageIO.read(new File("images" + File.separator + "defender-bottom.jpg")
                    .getAbsoluteFile());
            images[2] = ImageIO.read(new File("images" + File.separator + "defender-left.jpg")
                    .getAbsoluteFile());
            images[3] = ImageIO.read(new File("images" + File.separator + "defender-right.jpg")
                    .getAbsoluteFile());
        } catch (IOException e) {
            throw new IllegalStateException("Can't find image");
        }
    }

}
