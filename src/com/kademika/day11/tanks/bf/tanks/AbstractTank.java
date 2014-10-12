package com.kademika.day11.tanks.bf.tanks;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.ImageObserver;
import java.io.*;
import java.util.Random;

import com.kademika.day11.tanks.*;
import com.kademika.day11.tanks.bf.*;

public abstract class AbstractTank implements Tank {

    private int speed = 10;
    protected int movePath = 1;

    // 1 - up, 2 - down, 3 - left, 4 - right
    private Direction direction;

    // current position on BF
    private int x;
    private int y;
    protected boolean enemy;
    protected boolean enemyOnLine;
    protected int eTankX;
    protected int eTankY;
    protected int nX;
    protected int nY;

    protected Random rand = new Random();

    private boolean destroyed;

    protected BattleField bf;
    protected ActionField af;

    protected Color tankColor;
    protected Color towerColor;
    protected Image[] images;

    public AbstractTank(BattleField bf) {
        this(bf, 128, 512, Direction.UP);
    }

    public AbstractTank(BattleField bf, int x, int y, Direction direction) {
        this.bf = bf;
        this.x = x;
        this.y = y;
        this.direction = direction;
        this.destroyed = false;
    }

    public void turn(Direction direction) {
        this.direction = direction;
    }

    public void move() {
    }

    public AbstractBfObject getNextQuadrant(int v, int h) {
        String coords = bf.getQuadrant(v, h);
        int separator = coords.indexOf("_");
        int y = Integer.parseInt(coords.substring(0, separator));
        int x = Integer.parseInt(coords.substring(separator + 1));

        if ((x == 0 && direction == Direction.LEFT)
                || (y == 0 && direction == Direction.UP)
                || (x == 8 && direction == Direction.RIGHT)
                || (y == 8 && direction == Direction.DOWN)) {
            System.out.println("Current quadrant");
            return bf.scanQuadrant(y, x);
        }

        if (direction == Direction.UP) {
            y--;
        } else if (direction == Direction.DOWN) {
            y++;
        } else if (direction == Direction.LEFT) {
            x--;
        } else if (direction == Direction.RIGHT) {
            x++;
        }
        nX = x;
        nY = y;

        return bf.scanQuadrant(y, x);
    }

    public int scanNextQuadrant(int x, int y, Direction direction) {
        this.direction = direction;
        if (getNextQuadrant(x, y) instanceof Brick) {
            return 1;
        }
        if (getNextQuadrant(x, y) instanceof Rock) {
            return 2;
        }
        return 0;
    }

    public AbstractBfObject getNextNextQuadrant(int v, int h) {
        String coords = bf.getQuadrant(v, h);
        int separator = coords.indexOf("_");
        int y = Integer.parseInt(coords.substring(0, separator));
        int x = Integer.parseInt(coords.substring(separator + 1));

        if ((x == 1 && direction == Direction.LEFT)
                || (y == 1 && direction == Direction.UP)
                || (x == 7 && direction == Direction.RIGHT)
                || (y == 7 && direction == Direction.DOWN)) {
            System.out.println("Current quadrant");
            return bf.scanQuadrant(y, x);
        }

        if (direction == Direction.UP) {
            y = y - 2;
        } else if (direction == Direction.DOWN) {
            y = y + 2;
        } else if (direction == Direction.LEFT) {
            x = x - 2;
        } else if (direction == Direction.RIGHT) {
            x = x + 2;
        }
        if (x > 8) {
            x = 8;
        } else if (y > 8) {
            y = 8;
        }

        return bf.scanQuadrant(y, x);
    }

    public void isEnemyTank(AbstractTank enemyTank) {
        String coords = bf.getQuadrant(enemyTank.getX(), enemyTank.getY());
        int separator = coords.indexOf("_");
        int etY = Integer.parseInt(coords.substring(0, separator));
        int etX = Integer.parseInt(coords.substring(separator + 1));
        eTankY = Integer.parseInt(coords.substring(0, separator));
        eTankX = Integer.parseInt(coords.substring(separator + 1));

        coords = bf.getQuadrant(getX(), getY());
        int y = Integer.parseInt(coords.substring(0, separator));
        int x = Integer.parseInt(coords.substring(separator + 1));

        int tmpX = x;
        int tmpY = y;

        if (direction == Direction.UP) {
            y--;
            tmpY = y - 1;
        } else if (direction == Direction.DOWN) {
            y++;
            tmpY = y + 1;
        } else if (direction == Direction.LEFT) {
            x--;
            tmpX = x - 1;
        } else if (direction == Direction.RIGHT) {
            x++;
            tmpX = x + 1;
        }

        if ((x == etX && y == etY) || (tmpX == etX && tmpY == etY)) {
            enemy = true;
        } else {
            enemy = false;
        }
    }

    protected boolean scanEnemyOnLine() {
        String coords = bf.getQuadrant(getX(), getY());
        int separator = coords.indexOf("_");
        int y = Integer.parseInt(coords.substring(0, separator));
        int x = Integer.parseInt(coords.substring(separator + 1));

        if (direction == Direction.UP) {
            for (int i = y; i >= 0; i--) {
                if (!(bf.scanQuadrant(i, x) instanceof Empty) && i <= 4) {
                    return false;
                } else if (eTankX != x && eTankY != i) {
                    return false;
                }
            }
        } else if (direction == Direction.DOWN) {
            for (int i = y; i <= 8; i++) {
                if (!(bf.scanQuadrant(i, x) instanceof Empty) && i <= 4) {
                    return false;
                } else if (eTankX != x && eTankY != i) {
                    return false;
                }
            }
        } else if (direction == Direction.LEFT) {
            for (int i = x; i >= 0; i--) {
                if (!(bf.scanQuadrant(y, i) instanceof Empty) && i <= 4) {
                    return false;
                } else if (eTankX != i && eTankY != y) {
                    return false;
                }
            }
        } else if (direction == Direction.RIGHT) {
            for (int i = x; i <= 8; i++) {
                if (!(bf.scanQuadrant(y, i) instanceof Empty) && i <= 4) {
                    return false;
                } else if (eTankX != i && eTankY != y) {
                    return false;
                }
            }
        }
        return true;
    }

    public Bullet fire() {
        int bulletX = -100;
        int bulletY = -100;
        if (direction == Direction.UP) {
            bulletX = x + 25;
            bulletY = y;
        } else if (direction == Direction.DOWN) {
            bulletX = x + 25;
            bulletY = y + 64;
        } else if (direction == Direction.LEFT) {
            bulletX = x;
            bulletY = y + 25;
        } else if (direction == Direction.RIGHT) {
            bulletX = x + 64;
            bulletY = y + 25;
        }
        return new Bullet(bulletX, bulletY, direction);
    }

    public void draw(Graphics g) {
        if (!destroyed) {
            g.drawImage(images[getDirection().getId()], getX(), getY(),
                    new ImageObserver() {
                        @Override
                        public boolean imageUpdate(Image img, int infoflags,
                                                   int x, int y, int width, int height) {
                            return false;
                        }
                    });
        }
    }

    public void destroy() {
        destroyed = true;
    }


    public void setAggressorTank() {
        Random r = new Random();
        int i = 0;
        i = r.nextInt(3) + 1;

        switch (i) {
            case 1: {
                x = 0;
                y = 64;
                direction = Direction.RIGHT;
                break;
            }
            case 2: {
                x = 512;
                y = 64;
                direction = Direction.LEFT;
                break;
            }
            case 3: {
                x = 256;
                y = 0;
                direction = Direction.DOWN;
                break;
            }
        }
    }

    public void updateX(int x) {
        this.x += x;
    }

    public void updateY(int y) {
        this.y += y;
    }

    public boolean isDestroyed() {
        return destroyed;
    }

    public int getMovePath() {
        return movePath;
    }

    public int getSpeed() {
        return speed;
    }

    public Direction getDirection() {
        return direction;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public void setDestroyed(boolean destroyed) {
        this.destroyed = destroyed;
    }
}
