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

    public static void main(String[] ar) {
        serverPort = 6666; // здесь обязательно нужно указать порт к которому привязывается сервер.
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
//                    DataInputStream in = new DataInputStream(sin);
//                    DataOutputStream out = new DataOutputStream(sout);


                    outObj = new ObjectOutputStream(sout);
//
//                    // Создаем поток для чтения с клавиатуры.
//                    BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));
//                    String line = null;
//                    System.out.println("Type in something and press enter. Will send it to the server and tell ya what it thinks.");
//                    System.out.println();
                    inObj = new ObjectInputStream(sin);
                    outObj.writeUTF("start");
                    outObj.flush();
                    while (true) {
                        data = inObj.readObject();
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
                    outObj.writeUTF("get data");
                }
            } else if (data instanceof Transaction) {
                Transaction trans = (Transaction) data;
                list.add(trans);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}