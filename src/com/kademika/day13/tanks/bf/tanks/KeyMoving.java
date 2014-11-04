package com.kademika.day13.tanks.bf.tanks;


import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Created by Admin on 15.09.2014.
 */
public class KeyMoving implements KeyListener {
    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

        int keyCode = e.getKeyCode();
        if (keyCode == KeyEvent.VK_LEFT) System.out.println("VK_LEFT");
        else if (keyCode == KeyEvent.VK_RIGHT) System.out.println("VK_RIGHT");
        else if (keyCode == KeyEvent.VK_UP) System.out.println("VK_UP");
        else if (keyCode == KeyEvent.VK_DOWN) System.out.println("VK_DOWN");
    }




    @Override
    public void keyReleased(KeyEvent e) {

    }
}
