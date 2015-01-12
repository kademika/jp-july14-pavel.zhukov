package com.kademika.day14.shop;

import com.kademika.day14.shop.db.DBConnection;
import com.kademika.day14.shop.watches.Watch;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.text.NumberFormat;

public class ShopUI {
    private Shop shop;
    private Report report;
    private JLabel lResult;
    private JTextArea lStr;
    private JComboBox<String> cbClientName;
    private JComboBox<String> cbWatch;
    private JFormattedTextField ftfNumber;
    private JComboBox<String> cbPersonal;
    private DBConnection dbConnection;

    public ShopUI(Shop shop, Report report, DBConnection dbConnection) {
        this.dbConnection = dbConnection;
        this.shop = shop;
        this.report = report;
        JFrame frame = new JFrame("SHOP");
        frame.setLocation(300, 100);
        frame.setMinimumSize(new Dimension(800, 600));

        frame.getContentPane().add(createSellingPanel());

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    private JPanel createSellingPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());

        JLabel lFormName = new JLabel("Make a purchase");
        panel.add(lFormName, new GridBagConstraints(0, 0, 1, 1, 0, 0,
                GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(
                5, 0, 5, 0), 0, 0));

        JLabel lClientName = new JLabel("Client");
        panel.add(lClientName, new GridBagConstraints(0, 1, 1, 1, 0, 0,
                GridBagConstraints.LINE_START, 0, new Insets(0, 0, 0, 0), 0, 0));

        cbClientName = new JComboBox<String>(shop.getFioClientArray());
        cbClientName.setEditable(true);
        panel.add(cbClientName, new GridBagConstraints(1, 1, 1, 1, 0, 0,
                GridBagConstraints.LINE_START, 0, new Insets(0, 0, 0, 0), 0, 0));

        JLabel lProduct = new JLabel("Product");
        panel.add(lProduct, new GridBagConstraints(0, 2, 1, 1, 0, 0,
                GridBagConstraints.LINE_START, 0, new Insets(0, 0, 0, 0), 0, 0));

        cbWatch = new JComboBox<String>(shop.getNameWatchArray());
        cbWatch.setPreferredSize(new Dimension(300, 20));
        // cbWatch.setBounds(0, 0, 100, 10);
        panel.add(cbWatch, new GridBagConstraints(1, 2, 1, 1, 0, 0,
                GridBagConstraints.LINE_START, GridBagConstraints.NONE,
                new Insets(0, 0, 0, 0), 0, 0));

        JLabel lNumber = new JLabel("Number");
        panel.add(lNumber, new GridBagConstraints(0, 3, 1, 1, 0, 0,
                GridBagConstraints.LINE_START, 0, new Insets(5, 0, 5, 0), 0, 0));

        NumberFormat nf = NumberFormat.getInstance();
        ftfNumber = new JFormattedTextField(nf);
        ftfNumber.setValue(1);
        ftfNumber.setPreferredSize(new Dimension(30, 20));
        panel.add(ftfNumber, new GridBagConstraints(1, 3, 1, 1, 0, 0,
                GridBagConstraints.LINE_START, 0, new Insets(5, 0, 5, 0), 0, 0));

        JLabel lConsultant = new JLabel("Personal");
        panel.add(lConsultant, new GridBagConstraints(0, 4, 1, 1, 0, 0,
                GridBagConstraints.LINE_START, 0, new Insets(0, 0, 0, 0), 0, 0));

        cbPersonal = new JComboBox<String>(shop.getFioPerArray());
        panel.add(cbPersonal, new GridBagConstraints(1, 4, 1, 1, 0, 0,
                GridBagConstraints.LINE_START, 0, new Insets(0, 0, 0, 0), 0, 0));

        lResult = new JLabel("Complete");
        lResult.setVisible(false);
        panel.add(lResult, new GridBagConstraints(0, 7, 1, 1, 0, 0,
                GridBagConstraints.LINE_START, GridBagConstraints.NONE,
                new Insets(10, 0, 0, 0), 0, 0));

        lStr = new JTextArea();
        lStr.append(" ");
        panel.add(lStr, new GridBagConstraints(0, 8, 2, 1, 0, 0,
                GridBagConstraints.LINE_START, GridBagConstraints.NONE,
                new Insets(10, 0, 0, 0), 0, 0));

        JButton bBuy = new JButton("Buy");
        panel.add(bBuy, new GridBagConstraints(0, 6, 1, 1, 0, 0,
                GridBagConstraints.LINE_START, GridBagConstraints.NONE,
                new Insets(0, 0, 0, 0), 40, 0));

        bBuy.addActionListener(new BuyButton());

        return panel;
    }

    class BuyButton implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            boolean res = false;
            int cn = cbClientName.getSelectedIndex();
            int p = cbWatch.getSelectedIndex();
            int num = Integer.parseInt(ftfNumber.getText());
            int con = cbPersonal.getSelectedIndex();
            shop.setDate(new Date(System.currentTimeMillis()));
            try {
//                dbConnection = new DBConnection();
                shop.setTransaction((Client) shop.getClients().toArray()[cn],
                        (Watch) shop.getWatches().toArray()[p], num, (Personal) shop.getPersonal().toArray()[con]);
                res = true;
            } catch (Exception err) {
                System.out.println(err);
            }

//            lStr.append(report.getLastTransaction(shop.getTransactions(),
//                    shop) + "\n");
//            lStr.setCaretPosition(lStr.getDocument().getLength());
//            if (res) {
//                lResult.setVisible(true);
//            }
//            dbConnection.closeConnection();
        }
    }
}