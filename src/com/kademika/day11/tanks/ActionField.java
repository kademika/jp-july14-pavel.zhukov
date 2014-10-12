package com.kademika.day11.tanks;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.io.*;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import com.kademika.day11.tanks.bf.*;
import com.kademika.day11.tanks.bf.tanks.*;
import com.kademika.day11.tanks.example.MainFrame;

public class ActionField extends JPanel {
    private String fileName = "TanksLog.txt";
    private String resultStr;
    private File file;
    private FileInputStream fis;
    private InputStreamReader reader;
    private BufferedReader bufferedReader;
    private FileWriter fw;
    private StringBuilder stringBuilder;

    private BattleField bf;
    private AbstractTank defender;
    private AbstractTank aggressor;
    private Bullet bullet;
    private GameFrame frame;

    public void runTheGame() throws Exception {
        fw = new FileWriter(file);
        while (true) {
            if (!aggressor.isDestroyed() && !defender.isDestroyed()
                    && !bf.objEagle.isDestroyed()) {

                aggressor.isEnemyTank(defender);
                // System.out.println(aggressor.enemyTankNNQuadrant(defender));
                processAction(aggressor.setUp(), aggressor);
            } else {
                fw.write(stringBuilder.toString());
                fw.close();
                frame.getGameOver();
                return;
            }
            if (!aggressor.isDestroyed() && !defender.isDestroyed()) {
                defender.isEnemyTank(aggressor);
                processAction(defender.setUp(), defender);
            } else {

                fw.write(stringBuilder.toString());
                fw.close();
                frame.getGameOver();
                return;
            }

        }
    }

    private void processAction(Action a, AbstractTank t) throws Exception {
        if (t instanceof T34) {
            stringBuilder.append("D");
        } else {
            stringBuilder.append("A");
        }
        stringBuilder.append(a.toString().substring(0, 1));
//        System.out.println(a.toString() + " " + t.getDirection().toString());
        stringBuilder.append(t.getDirection().toString().substring(0, 1));
        if (a == Action.MOVE) {
            processMove(t);
        } else if (a == Action.FIRE) {
            processTurn(t);
            processFire(t.fire());
        }
        stringBuilder.append("_");
    }

    public void processTurn(AbstractTank tank) throws Exception {
        repaint();
    }

    public void processMove(AbstractTank tank) throws Exception {
        // processTurn(tank);
        Direction direction = tank.getDirection();
        int step = 1;

        for (int i = 0; i < tank.getMovePath(); i++) {
            int covered = 0;

            String tankQuadrant = getQuadrant(tank.getX(), tank.getY());
            int v = Integer.parseInt(tankQuadrant.split("_")[0]);
            int h = Integer.parseInt(tankQuadrant.split("_")[1]);

            if ((direction == Direction.UP && tank.getY() == 0)
                    || (direction == Direction.DOWN && tank.getY() >= 512)
                    || (direction == Direction.LEFT && tank.getX() == 0)
                    || (direction == Direction.RIGHT && tank.getX() >= 512)) {
                System.out.println("[illegal move] direction: " + direction
                        + " tankX: " + tank.getX() + ", tankY: " + tank.getY());
                return;
            }

            if (direction == Direction.UP) {
                v--;
            } else if (direction == Direction.DOWN) {
                v++;
            } else if (direction == Direction.RIGHT) {
                h++;
            } else if (direction == Direction.LEFT) {
                h--;
            }


            AbstractBfObject abf = bf.scanQuadrant(v, h);
            if (!(abf instanceof Empty) && !abf.isDestroyed()
                    && !(abf instanceof Water)) {
                System.out.println("[illegal move: "
                        + abf.getClass().getSimpleName() + "] direction: "
                        + direction + " tankX: " + tank.getX() + ", tankY: "
                        + tank.getY());
                return;
            }


            while (covered < 64) {
                if (direction == Direction.UP) {
                    tank.updateY(-step);
                } else if (direction == Direction.DOWN) {
                    tank.updateY(step);
                } else if (direction == Direction.LEFT) {
                    tank.updateX(-step);
                } else {
                    tank.updateX(step);
                }
                covered += step;
                repaint();
                Thread.sleep(tank.getSpeed());
            }
        }
    }

    public void processFire(Bullet bullet) throws Exception {
        this.bullet = bullet;
        int step = 1;

        while ((bullet.getX() > -14 && bullet.getX() < 590)
                && (bullet.getY() > -14 && bullet.getY() < 590)) {
            if (bullet.getDirection() == Direction.UP) {
                bullet.updateY(-step);
            } else if (bullet.getDirection() == Direction.DOWN) {
                bullet.updateY(step);
            } else if (bullet.getDirection() == Direction.LEFT) {
                bullet.updateX(-step);
            } else {
                bullet.updateX(step);
            }
            if (processInterception()) {
                bullet.destroy();
            }

            repaint();
            Thread.sleep(bullet.getSpeed());
            if (bullet.isDestroyed()) {
                break;
            }
        }
    }

    public String getQuadrantXY(int v, int h) {
        return (v - 1) * 64 + "_" + (h - 1) * 64;
    }

    public String getQuadrant(int x, int y) {
        return y / 64 + "_" + x / 64;
    }

    private boolean processInterception() {
        String coordinates = getQuadrant(bullet.getX(), bullet.getY());
        int bY = Integer.parseInt(coordinates.split("_")[0]);
        int bX = Integer.parseInt(coordinates.split("_")[1]);

        if (bX >= 0 && bX < 9 && bY >= 0 && bY < 9) {
            AbstractBfObject abf = bf.scanQuadrant(bY, bX);

            if (!(abf instanceof Empty) && !abf.isDestroyed()
                    && !(abf instanceof Water)) {
                bf.destroyObject(bY, bX);
                bf.setEmptyElement(bY, bX);
                return true;
            }
            // if (!checkInterception(beginCoords, coordinates)) {
            if (checkInterception(
                    getQuadrant(aggressor.getX(), aggressor.getY()),
                    coordinates)) {
                aggressor.destroy();
                return true;
            }
            if (checkInterception(
                    getQuadrant(defender.getX(), defender.getY()), coordinates)) {
                defender.destroy();
                return true;
            }
            // }
        }

        return false;
    }

    private boolean checkInterception(String objQuadrant, String targetQuadrant) {
        int oY = Integer.parseInt(objQuadrant.split("_")[0]);
        int oX = Integer.parseInt(objQuadrant.split("_")[1]);

        int tY = Integer.parseInt(targetQuadrant.split("_")[0]);
        int tX = Integer.parseInt(targetQuadrant.split("_")[1]);

        if (oY >= 0 && oY < 9 && oX >= 0 && oX < 9) {
            if (oX == tX && oY == tY) {
                return true;
            }
        }
        return false;
    }

    public void initRepeat() throws Exception {
        openFileForRead();

        stringBuilder = new StringBuilder();
        String str;
        while ((str = bufferedReader.readLine()) != null) {
            stringBuilder.append(str);
        }
        resultStr = stringBuilder.toString();

        bf = new BattleField();

        int agrX = Integer.parseInt(resultStr.substring(resultStr.indexOf("(") + 1, resultStr.indexOf(")")).split("_")[0]);
        int agrY = Integer.parseInt(resultStr.substring(resultStr.indexOf("(") + 1, resultStr.indexOf(")")).split("_")[1]);
        int defX = Integer.parseInt(resultStr.substring(resultStr.indexOf("(") + 1, resultStr.indexOf(")")).split("_")[2]);
        int defY = Integer.parseInt(resultStr.substring(resultStr.indexOf("(") + 1, resultStr.indexOf(")")).split("_")[3]);

        defender = new T34(bf);
        defender.setX(defX);
        defender.setY(defY);

        int aggressorType = Integer.parseInt(resultStr.substring(0, 1));
        if (aggressorType == 1) {
            aggressor = new BT7(bf, agrX, agrY, Direction.DOWN);

        } else {
            aggressor = new Tiger(bf, agrX, agrY, Direction.DOWN);

        }
        bullet = new Bullet(-100, -100, Direction.NONE);

        resultStr = resultStr.substring(resultStr.indexOf(")") + 1, resultStr.length() - 1);
        System.out.println(resultStr);
    }

    public void repeatGame() throws Exception {
        for (String s : resultStr.split("_")) {
//            System.out.println(getTank(s.substring(0, 1).toString()) + "-" + getAction(s.substring(1, 2)) + "-" + getTankDirection(s.substring(2, 3)));
            getTank(s.substring(0, 1)).setDirection(getTankDirection(s.substring(2, 3)));
            processAction(getAction(s.substring(1, 2)), getTank(s.substring(0, 1)));
        }
        frame.getGameOver();
    }

    private AbstractTank getTank(String s) {
        if (s.equals("A")) {
            return aggressor;
        } else {
            return defender;
        }
    }

    private Action getAction(String s) {
        if (s.equals("F")) {
            return Action.FIRE;
        } else if (s.equals("M")) {
            return Action.MOVE;
        } else {
            return Action.NONE;
        }
    }

    private Direction getTankDirection(String s) {
        if (s.equals("U")) {
            return Direction.UP;
        } else if (s.equals("D")) {
            return Direction.DOWN;
        } else if (s.equals("R")) {
            return Direction.RIGHT;
        } else {
            return Direction.LEFT;
        }
    }

    public ActionField(GameFrame frame) throws Exception {
        file = new File(fileName);
        stringBuilder = new StringBuilder();
        this.frame = frame;
    }

    protected void openFileForRead() throws IOException {
        if (!file.exists()) file.createNewFile();
        fis = new FileInputStream(file);
        reader = new InputStreamReader(fis);
        bufferedReader = new BufferedReader(reader);
    }


//    public ActionField() throws Exception {
//        file = new File(fileName);
//        stringBuilder = new StringBuilder();
//        bf = new BattleField();
//        defender = new T34(bf);
//        aggressor = new Tiger(bf, 128, 448, Direction.DOWN);
//        aggressor.setAggressorTank();
//        stringBuilder.append("(" + aggressor.getX() + "_" + aggressor.getY() + "_" + defender.getX() + "_" + defender.getY() + ")");
//        bullet = new Bullet(-100, -100, Direction.NONE);
//        JFrame frame = new JFrame("BATTLE FIELD, DAY 9");
//        frame.setLocation(500, 150);
//
//        frame.setMinimumSize(new Dimension(576 + 8, 576 + 32));
//        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
//
//        frame.getContentPane().add(this);
//
//
//        frame.pack();
//        frame.setVisible(true);
//    }

    public void init(int num) {
        bf = new BattleField();
        defender = new T34(bf);
        if (num == 1) {
            aggressor = new BT7(bf, 128, 64, Direction.DOWN);
            aggressor.setAggressorTank();
            stringBuilder.append("1(" + aggressor.getX() + "_" + aggressor.getY() + "_" + defender.getX() + "_" + defender.getY() + ")");
        } else {
            aggressor = new Tiger(bf, 128, 448, Direction.DOWN);
            aggressor.setAggressorTank();
            stringBuilder.append("2(" + aggressor.getX() + "_" + aggressor.getY() + "_" + defender.getX() + "_" + defender.getY() + ")");
        }
        bullet = new Bullet(-100, -100, Direction.NONE);
    }

//    public ActionField(AbstractTank agressorTank) throws Exception {
////		bf = new BattleField();
//        gg = new GameGUI();
//        defender = new T34(bf);
//        // aggressor = new BT7(bf, 128, 64, Direction.DOWN);
//        // aggressor.setAggressorTank();
//        aggressor = agressorTank;
//        aggressor.setAggressorTank();
//        bullet = new Bullet(-100, -100, Direction.NONE);
//        JFrame frame = new JFrame("BATTLE FIELD, DAY 9");
//        frame.setLocation(500, 150);
//        frame.setMinimumSize(new Dimension(bf.getBfWidth() + 8,
//                bf.getBfWidth() + 32));
//        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
//        frame.getContentPane().add(this);
//        frame.pack();
//        frame.setVisible(true);
//    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        bf.draw(g);
        defender.draw(g);
        bullet.draw(g);
        aggressor.draw(g);
        bf.drawWater(g);
    }

    public AbstractTank getDefender() {
        return defender;
    }

    public AbstractTank getAggressor() {
        return aggressor;
    }
}
