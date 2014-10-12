package com.kademika.day11.tanks;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import com.kademika.day11.tanks.bf.BattleField;
import com.kademika.day11.tanks.bf.tanks.BT7;
import com.kademika.day11.tanks.bf.tanks.Tiger;

public class GameGUI {
    private JPanel startGame;
    private JPanel gameOver;
    public JFrame frame;
    public int idTank = 1;

    public GameGUI() {
        init();
        getStartGame();
        frame.getContentPane().add(startGame);

        frame.getContentPane().repaint();
        frame.pack();
        frame.repaint();
    }

    public void init() {
        frame = new JFrame("TANKS, start the game");
        frame.setLocation(400, 400);
        frame.setMinimumSize(new Dimension(200, 200));

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
//	public void startGame() {
//		frame = new JFrame("TANKS, start the game");
//		frame.setLocation(400, 400);
//		frame.setMinimumSize(new Dimension(200, 200));
//
//		getStartGame();
//		frame.getContentPane().add(startGame);
//
//		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
//		frame.pack();
//		frame.setVisible(true);
//	}

    public void gameOver() {
//        init();

        frame.setTitle("TANKS, GAME OVER");
        frame.setLocation(400, 400);
        frame.setMinimumSize(new Dimension(200, 200));

//		frame = new JFrame("TANKS, GAME OVER");
//
//		frame.setLocation(400, 400);
//		frame.setMinimumSize(new Dimension(200, 200));
//
        getGameOver();
        frame.getContentPane().removeAll();
        frame.getContentPane().add(gameOver);
        frame.getContentPane().repaint();
//
//		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.pack();

        frame.repaint();
        frame.setVisible(true);
    }

    private void getStartGame() {
        startGame = new JPanel();
        startGame.setLayout(new GridBagLayout());

        JLabel jl = new JLabel("Choose the type of tank");
        startGame.add(jl, new GridBagConstraints(0, 0, 1, 1, 0, 0,
                GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL,
                new Insets(5, 5, 10, 5), 0, 0));

        String[] tanksType = new String[]{"BT7", "Tiger"};

        ButtonGroup tanksGroup = new ButtonGroup();
        JPanel pTanks = new JPanel();
        JRadioButton bt7 = new JRadioButton(tanksType[0]);
        tanksGroup.add(bt7);
        pTanks.add(bt7);
        JRadioButton tiger = new JRadioButton(tanksType[1]);
        tanksGroup.add(tiger);
        pTanks.add(tiger);

        bt7.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                idTank = 1;
//                frame.setVisible(false);
                SwingUtilities.invokeLater(new Runnable() {
                    //
                    @Override
                    public void run() {
                        try {
                            new Launcher();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
//                new Launcher();
            }
        });

        tiger.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                idTank = 2;
//                frame.setVisible(false);
                new Launcher();
            }
        });

        startGame.add(pTanks, new GridBagConstraints(0, 1, 1, 1, 0, 0,
                GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL,
                new Insets(5, 5, 10, 5), 0, 0));

    }

    private void getGameOver() {
        gameOver = new JPanel();
        gameOver.setLayout(new GridBagLayout());

        JLabel jl = new JLabel("Start a new game?");
        gameOver.add(jl, new GridBagConstraints(0, 0, 1, 1, 0, 0,
                GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL,
                new Insets(5, 5, 10, 5), 0, 0));

        JButton yes = new JButton("YES");
        yes.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
//                SwingUtilities.invokeLater(new Runnable() {
//
//                    @Override
//                    public void run() {
//                        try {
//                            new Launcher(new ActionField());
//                        } catch (Exception e) {
//                            e.printStackTrace();
//                        }
//                    }
//                });
                reloadGame();
            }
        });

        JButton no = new JButton("NO");
        no.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        gameOver.add(yes, new GridBagConstraints(0, 1, 1, 1, 0, 0,
                GridBagConstraints.LINE_START, 0, new Insets(0, 0, 0, 0), 0, 0));
        gameOver.add(no, new GridBagConstraints(1, 1, 1, 1, 0, 0,
                GridBagConstraints.LINE_START, 0, new Insets(0, 0, 0, 0), 0, 0));
    }

    private void reloadGame() {
        getStartGame();
        frame.getContentPane().removeAll();
        frame.getContentPane().add(startGame);
        frame.getContentPane().repaint();

        frame.pack();
        frame.repaint();
    }
}
