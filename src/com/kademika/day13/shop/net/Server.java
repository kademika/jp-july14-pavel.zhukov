package com.kademika.day13.shop.net;

import com.kademika.day13.shop.*;
import com.kademika.day13.shop.Client;
import com.kademika.day13.shop.watches.Watch;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {
    private static int port;
    private static Shop shop;
    private Object data;

    private ObjectInputStream inObj;
    private ObjectOutputStream outObj;
    private DataInputStream in;
    private DataOutputStream out;

    public static void main(String[] args) throws Exception {
        TestData td = new TestData();
        Report report = new Report();

        ArrayList<Client> clients = td.arrayClients();
        ArrayList<Personal> personal = td.arrayPersonal();
        ArrayList<Watch> watches = td.arrayWatches();

        shop = new Shop(clients, personal, watches);

        getTransactionsList(shop);

        port = 8080;
        Server server = new Server();
        server.startServer();
    }

    public static void getTransactionsList(Shop shop) {
        shop.setDay(Week.MONDAY.getId());
        shop.setTransaction(shop.getClients().get(0), shop.getWatches().get(1), 1,
                shop.getPersonal().get(0));
        shop.setTransaction(shop.getClients().get(1), shop.getWatches().get(3), 1,
                shop.getPersonal().get(1));

        shop.setDay(Week.TUESDAY.getId());
        shop.setTransaction(shop.getClients().get(2), shop.getWatches().get(11), 2,
                shop.getPersonal().get(2));
        shop.setTransaction(shop.getClients().get(3), shop.getWatches().get(13), 1,
                shop.getPersonal().get(3));
        shop.setTransaction(shop.getClients().get(4), shop.getWatches().get(7), 1,
                shop.getPersonal().get(4));
        shop.setTransaction(shop.getClients().get(1), shop.getWatches().get(15), 1,
                shop.getPersonal().get(0));

        shop.setDay(Week.WEDNESDAY.getId());
        shop.setTransaction(shop.getClients().get(2), shop.getWatches().get(6), 1,
                shop.getPersonal().get(1));
        shop.setTransaction(shop.getClients().get(0), shop.getWatches().get(17), 1,
                shop.getPersonal().get(2));

        shop.setDay(Week.THURSDAY.getId());
        shop.setTransaction(shop.getClients().get(4), shop.getWatches().get(19), 1,
                shop.getPersonal().get(3));
        shop.setTransaction(shop.getClients().get(3), shop.getWatches().get(14), 1,
                shop.getPersonal().get(4));
        shop.setTransaction(shop.getClients().get(0), shop.getWatches().get(9), 1,
                shop.getPersonal().get(0));
        shop.setTransaction(shop.getClients().get(1), shop.getWatches().get(6), 1,
                shop.getPersonal().get(1));

        shop.setDay(Week.FRIDAY.getId());
        shop.setTransaction(shop.getClients().get(2), shop.getWatches().get(10), 1,
                shop.getPersonal().get(2));
        shop.setTransaction(shop.getClients().get(4), shop.getWatches().get(13), 1,
                shop.getPersonal().get(3));

        shop.setDay(Week.SATURDAY.getId());
        shop.setTransaction(shop.getClients().get(3), shop.getWatches().get(16), 1,
                shop.getPersonal().get(4));
        shop.setTransaction(shop.getClients().get(0), shop.getWatches().get(8), 1,
                shop.getPersonal().get(0));
        shop.setTransaction(shop.getClients().get(2), shop.getWatches().get(4), 1,
                shop.getPersonal().get(1));
        shop.setTransaction(shop.getClients().get(1), shop.getWatches().get(3), 1,
                shop.getPersonal().get(2));
        shop.setTransaction(shop.getClients().get(3), shop.getWatches().get(17), 1,
                shop.getPersonal().get(3));
        shop.setTransaction(shop.getClients().get(4), shop.getWatches().get(9), 1,
                shop.getPersonal().get(4));

        shop.setDay(Week.SUNDAY.getId());
        shop.setTransaction(shop.getClients().get(0), shop.getWatches().get(11), 1,
                shop.getPersonal().get(0));
        shop.setTransaction(shop.getClients().get(3), shop.getWatches().get(17), 1,
                shop.getPersonal().get(1));
        shop.setTransaction(shop.getClients().get(4), shop.getWatches().get(12), 1,
                shop.getPersonal().get(2));
        shop.setTransaction(shop.getClients().get(1), shop.getWatches().get(1), 1,
                shop.getPersonal().get(3));
        shop.setTransaction(shop.getClients().get(2), shop.getWatches().get(0), 1,
                shop.getPersonal().get(4));
        shop.setTransaction(shop.getClients().get(0), shop.getWatches().get(0), 1,
                shop.getPersonal().get(0));
    }

    public void startServer() {

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    ServerSocket ss = new ServerSocket(port); // создаем сокет сервера и привязываем его к вышеуказанному порту
//                    System.out.println("Waiting for a client...");

                    Socket socket = ss.accept(); // заставляем сервер ждать подключений и выводим сообщение когда кто-то связался с сервером
//                    System.out.println("Got a client :) ... Finally, someone saw me through all the cover!");
//                    System.out.println();
//
                    // Берем входной и выходной потоки сокета, теперь можем получать и отсылать данные клиенту.
                    InputStream sin = socket.getInputStream();
                    OutputStream sout = socket.getOutputStream();

                    inObj = new ObjectInputStream(sin);
                    outObj = new ObjectOutputStream(sout);

//                    // Конвертируем потоки в другой тип, чтоб легче обрабатывать текстовые сообщения.
//                    in = new DataInputStream(sin);
//                    out = new DataOutputStream(sout);

//                    String line = null;
                    while (true) {
                        data = inObj.readObject();
                        checkData(data);
                        data = null;
//                        line = inObj.readUTF(); // ожидаем пока клиент пришлет строку текста.
//                        System.out.println("The dumb client just sent me this line : " + line);
//                        System.out.println("I'm sending it back...");
//                        out.writeUTF(line); // отсылаем клиенту обратно ту самую строку текста.
//                        out.flush(); // заставляем поток закончить передачу данных.
//                        System.out.println("Waiting for the next line...");
//                        System.out.println();
                    }
                } catch (Exception x) {
                    x.printStackTrace();
                }
            }
        }).start();
    }

    private void checkData(Object data) {
        try {
            if (data instanceof String) {
                String command = (String) data;
                if (command.equals("start")) {
                    System.out.println("Start connection");
                    out.writeUTF("ready to transfer data");
//                    out.flush();
                } else if (command.equals("get data")) {
                    ArrayList<Transaction> trans = shop.getTransactionsPerDay()[6];
                    for (Transaction tr : trans) {
                        out.writeUTF("send data");
//                        out.flush();
                        outObj.writeObject(tr);
                    }
                    System.out.println("Transfer complete");
                    outObj.flush();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

