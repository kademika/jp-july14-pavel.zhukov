package com.kademika.day11.f16to17fromday9;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.WindowConstants;

public class ShopUI_v2 {
    private Shop shop;
    private Report report;
    private JLabel lResult;
    private JTextArea lStr;
    private JComboBox<String> cbClientName;
    private JComboBox<String> cbProduct;
    private JFormattedTextField ftfNumber;
    private JComboBox<String> cbConsultant;
    private JComboBox<String> cbPaymaster;

    public ShopUI_v2(Shop shop, Report report) {
        this.shop = shop;
        this.report = report;
        JFrame frame = new JFrame("SHOP, DAY 8");
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

        cbClientName = new JComboBox<String>(shop.getStringClient());
        cbClientName.setEditable(true);
        panel.add(cbClientName, new GridBagConstraints(1, 1, 1, 1, 0, 0,
                GridBagConstraints.LINE_START, 0, new Insets(0, 0, 0, 0), 0, 0));

        JLabel lProduct = new JLabel("Product");
        panel.add(lProduct, new GridBagConstraints(0, 2, 1, 1, 0, 0,
                GridBagConstraints.LINE_START, 0, new Insets(0, 0, 0, 0), 0, 0));

        cbProduct = new JComboBox<String>(shop.getStringProduct());
        cbProduct.setPreferredSize(new Dimension(300, 20));
        // cbProduct.setBounds(0, 0, 100, 10);
        panel.add(cbProduct, new GridBagConstraints(1, 2, 1, 1, 0, 0,
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

        JLabel lConsultant = new JLabel("Consultant");
        panel.add(lConsultant, new GridBagConstraints(0, 4, 1, 1, 0, 0,
                GridBagConstraints.LINE_START, 0, new Insets(0, 0, 0, 0), 0, 0));

        cbConsultant = new JComboBox<String>(shop.getStringConsultant());
        panel.add(cbConsultant, new GridBagConstraints(1, 4, 1, 1, 0, 0,
                GridBagConstraints.LINE_START, 0, new Insets(0, 0, 0, 0), 0, 0));

        JLabel lPaymaster = new JLabel("Paymaster");
        panel.add(lPaymaster, new GridBagConstraints(0, 5, 1, 1, 0, 0,
                GridBagConstraints.LINE_START, GridBagConstraints.HORIZONTAL,
                new Insets(5, 0, 5, 0), 0, 0));

        cbPaymaster = new JComboBox<String>(shop.getStringPaymaster());
        panel.add(cbPaymaster, new GridBagConstraints(1, 5, 1, 1, 0, 0,
                GridBagConstraints.LINE_START, 0, new Insets(5, 0, 5, 0), 0, 0));

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
            int p = cbProduct.getSelectedIndex();
            int num = Integer.parseInt(ftfNumber.getText());
            int con = cbConsultant.getSelectedIndex();
            int pay = cbPaymaster.getSelectedIndex();
            try {
                shop.setTransaction(shop.getClients()[cn],
                        shop.getProducts()[p], num, shop.getConsultants()[con],
                        shop.getPaymasters()[pay]);
                res = true;
            } catch (Exception err) {
                System.out.println(err);
            }

            lStr.append(report.getLastTransaction(shop.getTransactionsArray(),
                    shop) + "\n");
            lStr.setCaretPosition(lStr.getDocument().getLength());
            if (res) {
                lResult.setVisible(true);
            }
        }
    }
}