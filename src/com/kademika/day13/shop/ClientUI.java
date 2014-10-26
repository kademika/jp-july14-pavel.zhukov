package com.kademika.day13.shop;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

/**
 * Created by Admin on 21.10.2014.
 */
public class ClientUI {

    public static void main(String[] args) {
        createSocket();
    }

    public static void createSocket() {
        ArrayList<Transaction> list = new ArrayList<>();
        StringBuilder stringBuilder = new StringBuilder();
        try (
                Socket socket = new Socket("localhost", 8082);
                OutputStream out = socket.getOutputStream();
                InputStream in = socket.getInputStream();

        ) {
            out.write("get data\n".getBytes());
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
                    System.out.println(command);
                    if (command.equals("ok")) {
                        ObjectInputStream inFile = new ObjectInputStream(new BufferedInputStream(socket.getInputStream()));
                        try {
                            while (inFile.readObject() != null) {
                                Transaction tr = (Transaction) inFile.readObject();
                                list.add(tr);
                            }
                        } catch (ClassNotFoundException e) {
                            e.printStackTrace();
                        }

                    }
                } else {
                    stringBuilder.append(data + " ");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        ArrayList<String> strList = getTransactions(list);
        for (String str : strList) {
            System.out.println(str);
        }
    }

    private static ArrayList<String> getTransactions(ArrayList<Transaction> transactions) {
        double oneDayTotalPrice = 0;
        int oneDayProducts = 0;
        if (!transactions.isEmpty()) {
            ArrayList<String> info = new ArrayList<>();
            for (Transaction tr : transactions) {
                if (tr != null) {
                    oneDayProducts += tr.getNumber();
                    oneDayTotalPrice += tr.getTotalPrice();
                    info.add("ID: " + tr.getNumTransaction()
                            + "	Client: "
                            + tr.getClient().getFio()
                            + "	ID watch: " + tr.getIdProd()
                            + "	Watch: " + tr.getName()
                            + " price: " + tr.getPrice()
                            + "	number: " + tr.getNumber()
                            + "	total: " + tr.getTotalPrice()
                            + " seller: "
                            + tr.getSeller().getFio());
                } else {
                    info.add("Null transaction");
                }
            }
            System.out.println("Total:	In this day sold " + oneDayProducts
                    + " pcs. of watches for the total amount "
                    + oneDayTotalPrice + " $");
            System.out.println("-----------------------------------------");
            return info;
        } else {
            ArrayList<String> info = new ArrayList<>();
            info.add("No transactions");
            return info;
        }
    }
}
