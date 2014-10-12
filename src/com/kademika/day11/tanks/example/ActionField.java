package com.kademika.day11.tanks.example;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.concurrent.atomic.AtomicBoolean;
import javax.security.auth.Destroyable;
import javax.swing.*;
//        import com.kademika.day11.tanks.bf.AbstractBfObject;
//        import com.kademika.day11.tanks.bf.BattleField;
//        import com.kademika.tanks.bf.Blank;
//        import com.kademika.tanks.bf.Rock;
//        import com.kademika.tanks.bf.Water;
//        import com.kademika.tanks.bf.tanks.Action;
//        import com.kademika.tanks.bf.tanks.BT7;
//        import com.kademika.tanks.bf.tanks.Bullet;
//        import com.kademika.tanks.bf.tanks.T34;
//        import com.kademika.tanks.bf.tanks.Tank;
//        import com.kademika.tanks.bf.tanks.Tiger;

/**
 * Updated to object oriented style.
 *
 * @version 3.0
 */
public class ActionField extends JPanel {
    // private boolean COLORDED_MODE = false;
//    private BattleField battleField;
//    private Tank defender;
//    private Tank aggressor;
//    private Bullet bullet;
//    // private boolean a = false;
//    volatile boolean gameRunning = false;
//    /**
//     * Write your code here.
//     */
//    void runTheGame() throws Exception {
//// while (!gameRunning) {
//// System.out.println("WAITING");
//// Thread.sleep(100);
//// }
//        System.out.println("GAME STARTED");
//        while (true) {
//            if (!aggressor.isDestroyed() && !defender.isDestroyed()) {
//                aggressor.setEnemy(defender);
//                processAction(aggressor.setUp(), aggressor);
//            }
//            if (!aggressor.isDestroyed() && !defender.isDestroyed()) {
//                defender.setEnemy(aggressor);
//                processAction(defender.setUp(), defender);
//            }
//        }
//    }
//    private void processAction(Action a, Tank tank) throws Exception {
//        if (a == Action.MOVE) {
//            processTurn(tank);
//            processMove(tank);
//        } else if (a == Action.FIRE) {
//            processTurn(tank);
//            processFire(tank.fire());
//        }
//    }
//    private void processTurn(Tank tank) throws Exception {
//// repaint();
//    }
//    private void processMove(Tank tank) throws Exception {
//        System.out.println("TANK IS MOVING");
//        processTurn(tank);
//        Direction direction = tank.getDirection();
//        int step = 1;
//        for (int i = 0; i < tank.getMovePath(); i++) {
//            int covered = 0;
//            String tankQuadrant = getQuadrant(tank.getX(), tank.getY());
//            int v = Integer.parseInt(tankQuadrant.split("_")[0]);
//            int h = Integer.parseInt(tankQuadrant.split("_")[1]);
//// check limits x: 0, 513; y: 0, 513
//            if ((direction == Direction.UP && tank.getY() == 0) || (direction == Direction.DOWN && tank.getY() >= 512)
//                    || (direction == Direction.LEFT && tank.getX() == 0) || (direction == Direction.RIGHT && tank.getX() >= 512)) {
//                System.out.println("[illegal move] direction: " + direction
//                        + " tankX: " + tank.getX() + ", tankY: " + tank.getY());
//                return;
//            }
//// check next quadrant before move
//            if (direction == Direction.UP) {
//                v--;
//            } else if (direction == Direction.DOWN) {
//                v++;
//            } else if (direction == Direction.RIGHT) {
//                h++;
//            } else if (direction == Direction.LEFT) {
//                h--;
//            }
//            BFObject bfobject = battleField.scanQuadrant(v, h);
//            if (!(bfobject instanceof Blank) && !bfobject.isDestroyed() && !(bfobject instanceof Water)) {
//                System.out.println("[illegal move] direction: " + direction
//                        + " tankX: " + tank.getX() + ", tankY: " + tank.getY());
//                return;
//            }
//// process move
//            while (covered < 64) {
//                if (direction == Direction.UP) {
//                    tank.updateY(-step);
//// System.out.println("[move up] direction: " + direction + " tankX: " + tank.getX() + ", tankY: " + tank.getY());
//                } else if (direction == Direction.DOWN) {
//                    tank.updateY(step);
//// System.out.println("[move down] direction: " + direction + " tankX: " + tank.getX() + ", tankY: " + tank.getY());
//                } else if (direction == Direction.LEFT) {
//                    tank.updateX(-step);
//// System.out.println("[move left] direction: " + direction + " tankX: " + tank.getX() + ", tankY: " + tank.getY());
//                } else {
//                    tank.updateX(step);
//// System.out.println("[move right] direction: " + direction + " tankX: " + tank.getX() + ", tankY: " + tank.getY());
//                }
//                covered += step;
//                System.out.println("MOVE EDT: " + SwingUtilities.isEventDispatchThread());
//                repaint();
//                Thread.sleep(tank.getSpeed());
//            }
//        }
//    }
//    private void processFire(Bullet bullet) throws Exception {
//        System.out.println("TANK IS SHOOTING");
//        this.bullet = bullet;
//        int step = 1;
//        while ((bullet.getX() > -14 && bullet.getX() < 590)
//                && (bullet.getY() > -14 && bullet.getY() < 590)) {
//            if (bullet.getDirection() == Direction.UP) {
//                bullet.updateY(-step);
//            } else if (bullet.getDirection() == Direction.DOWN) {
//                bullet.updateY(step);
//            } else if (bullet.getDirection() == Direction.LEFT) {
//                bullet.updateX(-step);
//            } else {
//                bullet.updateX(step);
//            }
//            if (processInterception()) {
//                bullet.destroy();
//            }
//            System.out.println("FIRE EDT: " + SwingUtilities.isEventDispatchThread());
//            repaint();
//            Thread.sleep(bullet.getSpeed());
//            if (bullet.isDestroyed()) {
//                break;
//            }
//        }
//    }
//    private boolean processInterception() {
//        String coordinates = getQuadrant(bullet.getX(), bullet.getY());
//        int y = Integer.parseInt(coordinates.split("_")[0]);
//        int x = Integer.parseInt(coordinates.split("_")[1]);
//        if (y >= 0 && y < 9 && x >= 0 && x < 9) {
//            BFObject bfObject = battleField.scanQuadrant(y, x);
//            if (!bfObject.isDestroyed() && !(bfObject instanceof Blank) && !(bfObject instanceof Water)) {
///*if (bfObject instanceof Water) {
//return false;
//} else */if (!(bfObject instanceof Rock)) {
//                    battleField.destroyObject(y, x);
//                }
//                return true;
//            }
//// check aggressor
//            if (!aggressor.isDestroyed() && checkInterception(getQuadrant(aggressor.getX(), aggressor.getY()), coordinates)) {
//                aggressor.destroy();
//                return true;
//            }
//// check aggressor
//            if (!defender.isDestroyed() && checkInterception(getQuadrant(defender.getX(), defender.getY()), coordinates)) {
//                defender.destroy();
//                return true;
//            }
//        }
//        return false;
//    }
//    private boolean checkInterception(String object, String quadrant) {
//        int oy = Integer.parseInt(object.split("_")[0]);
//        int ox = Integer.parseInt(object.split("_")[1]);
//        int qy = Integer.parseInt(quadrant.split("_")[0]);
//        int qx = Integer.parseInt(quadrant.split("_")[1]);
//        if (oy >= 0 && oy < 9 && ox >= 0 && ox < 9) {
//            if (oy == qy && ox == qx) {
//                return true;
//            }
//        }
//        return false;
//    }
//    public String getQuadrant(int x, int y) {
//        return y / 64 + "_" + x / 64;
//    }
//    public ActionField() throws Exception {
//        initialize();
//    }

//    public void initialize() {
//        battleField = new BattleField();
//        defender = new T34(battleField, 128, 512, Direction.UP);
//        aggressor = new Tiger(battleField, 512, 64, Direction.LEFT);
//        bullet = new Bullet(-100, -100, Direction.NONE);
//    }
//
//    @Override
//    protected void paintComponent(Graphics g) {
//        System.out.println("REPAINTING AF");
//        battleField.drawExceptWater(g);
//        defender.draw(g);
//        aggressor.draw(g);
//        battleField.drawWater(g);
//        bullet.draw(g);
//    }
}