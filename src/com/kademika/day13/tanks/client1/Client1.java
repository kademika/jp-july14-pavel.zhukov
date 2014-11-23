package com.kademika.day13.tanks.client1;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.net.Socket;

/**
 * Created by Admin on 04.11.2014.
 */
public class Client1 extends JFrame {

    private SetupPanel setupPanel;
    private int numTank = 0;
    private String var = "var";
    public boolean def = false;

    public static void main(String[] args) throws Exception {
        Client1 cl = new Client1();
    }

    public Client1() throws Exception {


//        loadSetupPanel();
        StringBuilder stringBuilder = new StringBuilder();
        try (
                Socket socket = new Socket("localhost", 8080);
                OutputStream out = socket.getOutputStream();
                InputStream in = socket.getInputStream();

        ) {
            out.write("start\n".getBytes());
            out.write(13);
            int data;
            int c = 0;
            while ((data = in.read()) != -1) {
                c = data;

                if (c == 13) {
                    String str = stringBuilder.toString();
                    byte[] buf = new byte[str.length()];
                    int k = 0;
                    for (String s : str.split(" ")) {
                        if (!s.equals("10")) {
                            buf[k] = Byte.parseByte(s);
                            k++;
                        }
                    }
                    String command = new String(buf, 0, k);
                    stringBuilder = new StringBuilder();
                    System.out.println(command);
                    if (command.equals("1_0") || command.equals("2_1") || command.equals("2_2") || command.equals("2_3")) {
                        var = var + Integer.parseInt(command.split("_")[0]);
                        init();
                        loadSetupPanel(Integer.parseInt(command.split("_")[0]), Integer.parseInt(command.split("_")[1]));
                        while (true) {
                            if (numTank > 0) {
                                out.write((numTank + "").getBytes());
                                out.write(13);
                                break;
                            }
                        }
                    }else if (command.equals("start the game")){

                    }
                } else {
                    stringBuilder.append(data + " ");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void init() throws Exception {
        this.setTitle("Start the game");
        this.setLocation(300, 300);
        this.setMinimumSize(new Dimension(250, 250));
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setFocusable(true);
    }

    public void loadSetupPanel(int var, int tank) {
        setupPanel = new SetupPanel(this, var, tank);
        this.getContentPane().removeAll();
        this.getContentPane().add(setupPanel);
        this.pack();
        setupPanel.setVisible(true);
    }

    public int getNumTank() {
        return numTank;
    }

    public void setNumTank(int numTank) {
        this.numTank = numTank;
    }
}
