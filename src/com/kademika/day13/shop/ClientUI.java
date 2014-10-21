package com.kademika.day13.shop;

import com.kademika.day13.shop.f15to17shop.*;
import com.kademika.day13.shop.f15to17shop.Report;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.text.NumberFormat;
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
                ObjectInputStream inFile = new ObjectInputStream(new BufferedInputStream(socket.getInputStream()));
        ) {
            out.write("get data\n".getBytes());
            out.write(13);
            int data;
            int c = 0;
            while ((data = in.read()) != -1) {
                c = data;
//                data = transmogrify(c);


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
//                    if (command.equals("ok")) {
//                        out.write("process started".getBytes());
//                        ArrayList<Transaction> trans = shop.getTransactions();
//                        for (Transaction tr : trans) {
//                            outInFile.writeObject(tr);
//                        }
//                        outInFile.flush();
//                        outInFile.close();
//                    }

                } else {
                    stringBuilder.append(data + " ");
                }

            }
//            try {
//                while (inFile.readObject() != null) {
//                    list.add((Transaction) inFile.readObject());
//                }
//            } catch (ClassNotFoundException e) {
//                e.printStackTrace();
//            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

//    private Shop shop;
//    private Report report;
//    private JLabel lResult;
//    private JTextArea lStr;
//    private JComboBox<String> cbClientName;
//    private JComboBox<String> cbProduct;
//    private JFormattedTextField ftfNumber;
//    private JComboBox<String> cbConsultant;
//    private JComboBox<String> cbPaymaster;

//    public ClientUI(Shop shop, Report report) {
//        this.shop = shop;
//        this.report = report;
//        JFrame frame = new JFrame("SHOP, DAY 8");
//        frame.setLocation(300, 100);
//        frame.setMinimumSize(new Dimension(800, 600));
//
//        frame.getContentPane().add(createSellingPanel());
//
//        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
//        frame.pack();
//        frame.setVisible(true);
//    }
//
//    private JPanel createSellingPanel() {
//        JPanel panel = new JPanel();
//        panel.setLayout(new GridBagLayout());
//
//        JLabel lFormName = new JLabel("Make a purchase");
//        panel.add(lFormName, new GridBagConstraints(0, 0, 1, 1, 0, 0,
//                GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(
//                5, 0, 5, 0), 0, 0));
//
//        JLabel lClientName = new JLabel("Client");
//        panel.add(lClientName, new GridBagConstraints(0, 1, 1, 1, 0, 0,
//                GridBagConstraints.LINE_START, 0, new Insets(0, 0, 0, 0), 0, 0));
//
//        cbClientName = new JComboBox<String>(shop.getStringClient());
//        cbClientName.setEditable(true);
//        panel.add(cbClientName, new GridBagConstraints(1, 1, 1, 1, 0, 0,
//                GridBagConstraints.LINE_START, 0, new Insets(0, 0, 0, 0), 0, 0));
//
//        JLabel lProduct = new JLabel("Product");
//        panel.add(lProduct, new GridBagConstraints(0, 2, 1, 1, 0, 0,
//                GridBagConstraints.LINE_START, 0, new Insets(0, 0, 0, 0), 0, 0));
//
//        cbProduct = new JComboBox<String>(shop.getStringWatch());
//        cbProduct.setPreferredSize(new Dimension(300, 20));
//        // cbProduct.setBounds(0, 0, 100, 10);
//        panel.add(cbProduct, new GridBagConstraints(1, 2, 1, 1, 0, 0,
//                GridBagConstraints.LINE_START, GridBagConstraints.NONE,
//                new Insets(0, 0, 0, 0), 0, 0));
//
//        JLabel lNumber = new JLabel("Number");
//        panel.add(lNumber, new GridBagConstraints(0, 3, 1, 1, 0, 0,
//                GridBagConstraints.LINE_START, 0, new Insets(5, 0, 5, 0), 0, 0));
//
//        NumberFormat nf = NumberFormat.getInstance();
//        ftfNumber = new JFormattedTextField(nf);
//        ftfNumber.setValue(1);
//        ftfNumber.setPreferredSize(new Dimension(30, 20));
//        panel.add(ftfNumber, new GridBagConstraints(1, 3, 1, 1, 0, 0,
//                GridBagConstraints.LINE_START, 0, new Insets(5, 0, 5, 0), 0, 0));
//
//        JLabel lConsultant = new JLabel("Consultant");
//        panel.add(lConsultant, new GridBagConstraints(0, 4, 1, 1, 0, 0,
//                GridBagConstraints.LINE_START, 0, new Insets(0, 0, 0, 0), 0, 0));
//
//        cbConsultant = new JComboBox<String>(shop.getStringConsultant());
//        panel.add(cbConsultant, new GridBagConstraints(1, 4, 1, 1, 0, 0,
//                GridBagConstraints.LINE_START, 0, new Insets(0, 0, 0, 0), 0, 0));
//
//        JLabel lPaymaster = new JLabel("Paymaster");
//        panel.add(lPaymaster, new GridBagConstraints(0, 5, 1, 1, 0, 0,
//                GridBagConstraints.LINE_START, GridBagConstraints.HORIZONTAL,
//                new Insets(5, 0, 5, 0), 0, 0));
//
//        cbPaymaster = new JComboBox<String>(shop.getStringPaymaster());
//        panel.add(cbPaymaster, new GridBagConstraints(1, 5, 1, 1, 0, 0,
//                GridBagConstraints.LINE_START, 0, new Insets(5, 0, 5, 0), 0, 0));
//
//        lResult = new JLabel("Complete");
//        lResult.setVisible(false);
//        panel.add(lResult, new GridBagConstraints(0, 7, 1, 1, 0, 0,
//                GridBagConstraints.LINE_START, GridBagConstraints.NONE,
//                new Insets(10, 0, 0, 0), 0, 0));
//
//        lStr = new JTextArea();
//        lStr.append(" ");
//        panel.add(lStr, new GridBagConstraints(0, 8, 2, 1, 0, 0,
//                GridBagConstraints.LINE_START, GridBagConstraints.NONE,
//                new Insets(10, 0, 0, 0), 0, 0));
//
//        JButton bBuy = new JButton("Buy");
//        panel.add(bBuy, new GridBagConstraints(0, 6, 1, 1, 0, 0,
//                GridBagConstraints.LINE_START, GridBagConstraints.NONE,
//                new Insets(0, 0, 0, 0), 40, 0));
//
//        bBuy.addActionListener(new BuyButton());
//
//        return panel;
//    }
//
//    class BuyButton implements ActionListener {
//
//        public void actionPerformed(ActionEvent e) {
//            boolean res = false;
//            int cn = cbClientName.getSelectedIndex();
//            int p = cbProduct.getSelectedIndex();
//            int num = Integer.parseInt(ftfNumber.getText());
//            int con = cbConsultant.getSelectedIndex();
//            int pay = cbPaymaster.getSelectedIndex();
//            try {
//                shop.setTransaction(shop.getClients()[cn],
//                        shop.getProducts()[p], num, shop.getConsultants()[con],
//                        shop.getPaymasters()[pay]);
//                res = true;
//            } catch (Exception err) {
//                System.out.println(err);
//            }
//
//            lStr.append(report.getLastTransaction(shop.getTransactionsArray(),
//                    shop) + "\n");
//            lStr.setCaretPosition(lStr.getDocument().getLength());
//            if (res) {
//                lResult.setVisible(true);
//            }
//        }
//    }

}
