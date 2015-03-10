package com.kademika.day13.shop.net;

/**
 * Created by User on 10.02.2015.
 */

import com.kademika.day13.shop.Transaction;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;

public class Client {
    private static String address;
    public static int serverPort;
    private Object data;
    private ArrayList<Transaction> list = new ArrayList<>();

    private ObjectInputStream inObj;
    private ObjectOutputStream outObj;
    private DataInputStream in;
    private DataOutputStream out;

    public static void main(String[] ar) {
        serverPort = 8080; // здесь обязательно нужно указать порт к которому привязывается сервер.
        address = "127.0.0.1"; // это IP-адрес компьютера, где исполняется наша серверная программа.
        Client client = new Client();
        client.startClient();

    }

    public void startClient() {

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    InetAddress ipAddress = InetAddress.getByName(address); // создаем объект который отображает вышеописанный IP-адрес.
//                    System.out.println("Any of you heard of a socket with IP address " + address + " and port " + serverPort + "?");
                    Socket socket = new Socket(ipAddress, serverPort); // создаем сокет используя IP-адрес и порт сервера.
//                    System.out.println("Yes! I just got hold of the program.");

                    // Берем входной и выходной потоки сокета, теперь можем получать и отсылать данные клиентом.
                    InputStream sin = socket.getInputStream();
                    OutputStream sout = socket.getOutputStream();

                    // Конвертируем потоки в другой тип, чтоб легче обрабатывать текстовые сообщения.

                    out = new DataOutputStream(sout);
                    in = new DataInputStream(sin);


//                    outObj = new ObjectOutputStream(sout);
//
//                    // Создаем поток для чтения с клавиатуры.
//                    BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));
//                    String line = null;
//                    System.out.println("Type in something and press enter. Will send it to the server and tell ya what it thinks.");
//                    System.out.println();

                    checkDataFromServer("start");
//                    inObj = new ObjectInputStream(sin);
                    while (true) {
                        data = in.readUTF();
                        checkDataFromServer(data);
                        data = null;
//                        line = keyboard.readLine(); // ждем пока пользователь введет что-то и нажмет кнопку Enter.
//                        System.out.println("Sending this line to the server...");
//                        out.writeUTF(line); // отсылаем введенную строку текста серверу.
//                        out.flush(); // заставляем поток закончить передачу данных.
//                        line = in.readUTF(); // ждем пока сервер отошлет строку текста.
//                        System.out.println("The server was very polite. It sent me this : " + line);
//                        System.out.println("Looks like the server is pleased with us. Go ahead and enter more lines.");
//                        System.out.println();
                    }
                } catch (Exception x) {
                    x.printStackTrace();
                }
            }
        }).start();
    }

    public void checkDataFromServer(Object data) {
        try {
            if (data instanceof String) {
                String command = (String) data;
                if (command.equals("ready to transfer data")) {
                    out.writeUTF("get data");
                    out.flush();
                } else if (command.equals("start")) {
                    out.writeUTF("start");
                    out.flush();
                } else if (command.equals("send data")) {
                    Transaction trans = (Transaction) inObj.readObject();
                    list.add(trans);
                }
            }
//            } else if (data instanceof Transaction) {
//                Transaction trans = (Transaction) data;
//                list.add(trans);
//            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void writeListTransactions() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    if (list.size() > 0) {
                        getTransactions(list);
                    }
                }
            }
        }).start();
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