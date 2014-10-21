package com.kademika.day12.tanks;

import com.kademika.day12.tanks.bf.AbstractBfObject;
import com.kademika.day12.tanks.bf.BattleField;
import com.kademika.day12.tanks.bf.Empty;
import com.kademika.day12.tanks.bf.Water;
import com.kademika.day12.tanks.bf.tanks.*;
import com.kademika.day12.tanks.bf.tanks.Action;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.*;
import java.util.ArrayList;

public class ActionField extends JPanel {
    private String fileName = "TanksLog.txt";
    private String resultStr;
    private File file;
    private FileInputStream fis;
    private InputStreamReader reader;
    private BufferedReader bufferedReader;
    private FileWriter fw;
    private StringBuilder stringBuilder;
    private volatile ArrayList<Bullet> bulletList;

    private BattleField bf;
    private AbstractTank defender;
    private AbstractTank aggressor;
    private Bullet bullet;
    private Bullet agrBullet;
    private Bullet defBullet;
    private GameFrame frame;
    private ActionField actionField;

    public ActionField(GameFrame frame) throws Exception {
        file = new File(fileName);
        stringBuilder = new StringBuilder();
        this.frame = frame;
    }


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
        bulletList = new ArrayList<>();
    }

    public void runTheGame() throws Exception {
        actionOfDefender();
        actionOfAggressor();
        actionOfBullet();
        independantScreenUpdate();


//        fw = new FileWriter(file);
//        while (true) {
//            if (!aggressor.isDestroyed() && !defender.isDestroyed()
//                    && !bf.objEagle.isDestroyed()) {
//
//                aggressor.isEnemyTank(defender);
//                // System.out.println(aggressor.enemyTankNNQuadrant(defender));
//                processAction(aggressor.setUp(), aggressor);
//            } else {
//                fw.write(stringBuilder.toString());
//                fw.close();
//                frame.getGameOver();
//                return;
//            }
//            if (!aggressor.isDestroyed() && !defender.isDestroyed()) {
//                defender.isEnemyTank(aggressor);
//                processAction(defender.setUp(), defender);
//            } else {
//
//                fw.write(stringBuilder.toString());
//                fw.close();
//                frame.getGameOver();
//                return;
//            }
//
//        }
    }

    private void actionOfAggressor() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (!aggressor.isDestroyed() && !defender.isDestroyed() && !bf.objEagle.isDestroyed()) {
                    aggressor.isEnemyTank(defender);
                    try {
                        processAction(aggressor.setUp(), aggressor);
                        Thread.sleep(300);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                frame.getGameOver();
                return;
            }
        }).start();
    }

    private void actionOfDefender() {
        actionOfKey();
//        this.setVisible(true);
//        this.setFocusable(true);
//        SwingWorker worker = new SwingWorker<Void, Void>() {
//            @Override
//            protected Void doInBackground() throws Exception {
//                actionOfKey();
////                getKeyAdapter();
//                return null;
//            }
//
//        };
//        worker.execute();
        new Thread(new Runnable() {
            @Override
            public void run() {
//
                while (!aggressor.isDestroyed() && !defender.isDestroyed()) {
                    defender.isEnemyTank(aggressor);
//                    actionOfKey();
//                    actionField.setVisible(true);
//actionField.setFocusable(true);
//                   getKeyAdapter();
                }
                frame.getGameOver();
                return;
            }
        }).start();


    }

    public void actionOfKey() {
        this.addKeyListener(getKeyAdapter());
        new Thread(new Runnable() {
            @Override
            public void run() {

                getKeyAdapter();
            }
        }).start();
    }

    public KeyAdapter getKeyAdapter() {
        return new KeyAdapter() {

            public void keyPressed(KeyEvent ke) {
                switch (ke.getKeyCode()) {
                    case KeyEvent.VK_W:
                    case KeyEvent.VK_UP:
                        defender.setDirection(Direction.UP);
                        try {
                            processTurn(defender);
                            processAction(Action.MOVE, defender);
                            System.out.println("123");
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        break;
                    case KeyEvent.VK_A:
                    case KeyEvent.VK_LEFT:
                        defender.setDirection(Direction.LEFT);
                        try {
                            processTurn(defender);
                            processAction(Action.MOVE, defender);
                            System.out.println("123");
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        break;
                    case KeyEvent.VK_S:
                    case KeyEvent.VK_DOWN:
                        defender.setDirection(Direction.DOWN);
                        try {
                            processTurn(defender);
                            processAction(Action.MOVE, defender);
                            System.out.println("123");
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        break;
                    case KeyEvent.VK_D:
                    case KeyEvent.VK_RIGHT:
                        defender.setDirection(Direction.RIGHT);
                        try {
                            processTurn(defender);
                            processAction(Action.MOVE, defender);
                            System.out.println("123");
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        break;
                    case KeyEvent.VK_SPACE:
                        try {
                            processTurn(defender);
                            processAction(Action.FIRE, defender);
                            System.out.println("123");
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        break;
                }
            }
        };
    }


    private void processAction(Action a, AbstractTank t) throws Exception {
        if (t instanceof T34) {
            stringBuilder.append("D");
        } else {
            stringBuilder.append("A");
        }
        stringBuilder.append(a.toString().substring(0, 1));
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
                System.out.println("MOVE EDT: " + SwingUtilities.isEventDispatchThread());
                repaint();
                Thread.sleep(tank.getSpeed());
            }
        }
    }

    public void processFire(Bullet bullet) throws Exception {
        bulletList.add(bullet);
    }

    private void actionOfBullet() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    while (bulletList.size() > 0) {
//                        for (Bullet b : bulletList) {


                        bullet = bulletList.get(0);
                        if (bullet != null) {
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
                                System.out.println("FIRE EDT: " + SwingUtilities.isEventDispatchThread());
                                repaint();
                                try {
                                    Thread.sleep(bullet.getSpeed());
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                                if (bullet.isDestroyed()) {
                                    bulletList.remove(bullet);
                                    break;
                                }
                            }
                        }
                    }
                }
            }
//            }
        }).start();
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
        independantScreenUpdate();
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


    protected void openFileForRead() throws IOException {
        if (!file.exists()) file.createNewFile();
        fis = new FileInputStream(file);
        reader = new InputStreamReader(fis);
        bufferedReader = new BufferedReader(reader);
    }


    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        System.out.println("REPAINTING AF");
        bf.draw(g);
        defender.draw(g);
        bullet.draw(g);
        aggressor.draw(g);
        bf.drawWater(g);
    }

    private void independantScreenUpdate() {
        this.setVisible(true);
        this.setFocusable(true);
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    repaint();
                    try {
                        Thread.sleep(16);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }
}
