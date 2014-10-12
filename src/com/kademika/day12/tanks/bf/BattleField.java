package com.kademika.day12.tanks.bf;

import java.awt.*;

public class BattleField implements Drawable {

    public static final String BRICK = "B";
    public static final String EAGLE = "E";
    public static final String ROCK = "R";
    public static final String WATER = "W";

    private String[][] battleFieldTemp = {
            {"B", "B", "B", "B", " ", "B", "R", "B", "B"},
            {" ", " ", " ", "B", " ", "B", " ", " ", " "},
            {"B", " ", "B", "W", "R", "B", " ", "B", "B"},
            {" ", " ", "W", "W", "W", "W", " ", " ", "R"},
            {"B", "R", "B", "B", "R", "R", "B", " ", "B"},
            {" ", "B", "B", "B", "W", "B", "B", " ", "B"},
            {" ", " ", "B", " ", " ", " ", "B", " ", " "},
            {"B", "B", "W", "B", "B", "B", " ", "R", " "},
            {"B", "B", " ", "B", "E", "B", " ", " ", " "}};

    private int bfWidth = 576;
    private int bfHeight = 576;

    public String eagle;
    public AbstractBfObject objEagle;

    private AbstractBfObject[][] battleField = new AbstractBfObject[9][9];

    public BattleField() {
        createABfObj();
    }

    public BattleField(String[][] battleField) {
        this.battleFieldTemp = battleField;
        createABfObj();
    }

    public String getQuadrantXY(int v, int h) {
        return (v - 1) * 64 + "_" + (h - 1) * 64;
    }

    public String getQuadrant(int x, int y) {
        return y / 64 + "_" + x / 64;
    }

    private void createABfObj() {
        for (int i = 0; i < getDimentionY(); i++) {
            for (int j = 0; j < getDimentionX(); j++) {
                String coordinates = getQuadrantXY(i + 1, j + 1);
                int separator = coordinates.indexOf("_");
                int y = Integer.parseInt(coordinates.substring(0, separator));
                int x = Integer.parseInt(coordinates.substring(separator + 1));

                String obj = battleFieldTemp[i][j];
                AbstractBfObject abf;
                if (obj.equals(BRICK)) {
                    abf = new Brick(x, y);
                } else if (obj.equals(ROCK)) {
                    abf = new Rock(x, y);
                } else if (obj.equals(WATER)) {
                    abf = new Water(x, y);
                } else if (obj.equals(EAGLE)) {
                    objEagle = new Eagle(x, y);
                    abf = objEagle;
                    eagle = getQuadrant(x, y);

                } else {
                    abf = new Empty(x, y);
                }
                battleField[i][j] = abf;
            }
        }

    }

    @Override
    public void draw(Graphics g) {
        for (int j = 0; j < battleField.length; j++) {
            for (int k = 0; k < battleField.length; k++) {
                battleField[j][k].draw(g);
            }
        }
    }

    public void drawWater(Graphics g) {
        for (int j = 0; j < battleField.length; j++) {
            for (int k = 0; k < battleField.length; k++) {
                if (battleField[j][k] instanceof Water) {
                    battleField[j][k].draw(g);
                }
            }
        }
    }

    public void destroyObject(int v, int h) {
        battleField[v][h].destroy();
    }

    public AbstractBfObject scanQuadrant(int v, int h) {
        return battleField[v][h];
    }

    public int getDimentionX() {
        return battleFieldTemp[1].length;
    }

    public int getDimentionY() {
        return battleFieldTemp.length;
    }

    public int getBfWidth() {
        return bfWidth;
    }

    public int getBfHeight() {
        return bfHeight;
    }

    public AbstractBfObject[][] getArrayOfObject() {
        return battleField;
    }

    public void setEmptyElement(int x, int y) {
        battleField[x][y] = new Empty(y * 64, x * 64);
    }
}
