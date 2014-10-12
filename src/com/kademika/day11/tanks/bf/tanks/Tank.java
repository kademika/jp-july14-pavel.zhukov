package com.kademika.day11.tanks.bf.tanks;

import com.kademika.day11.tanks.Direction;

public interface Tank {
    public Action setUp();

    public void move();

    public Bullet fire();

    public int getX();

    public int getY();

    public Direction getDirection();

    public void updateX(int x);

    public void updateY(int y);

    public int getSpeed();

    public int getMovePath();
}
