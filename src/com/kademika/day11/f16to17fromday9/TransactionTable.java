package com.kademika.day11.f16to17fromday9;

import java.awt.BorderLayout;
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
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.WindowConstants;

public class TransactionTable {
    private Shop shop;
    private Report report;
    private JFrame frame;
    private JPanel tablePanel;
    private JPanel sellingPanel;
    private JLabel lResult;
    private JComboBox<String> cbClientName;
    private JComboBox<String> cbProduct;
    private JFormattedTextField ftfNumber;
    private JComboBox<String> cbConsultant;
    private JComboBox<String> cbPaymaster;

    public TransactionTable(Shop shop, Report report) {
        this.shop = shop;
        this.report = report;

        frame = new JFrame("SHOP, DAY 9");
        frame.setLocation(300, 100);
        frame.setMinimumSize(new Dimension(800, 600));

        createTablePanel();
        createSellingPanel();

        JMenuBar mb = new JMenuBar();
        JMenu menu = new JMenu("File");
        JMenuItem menuItem = new JMenuItem("Buy Product");

        menuItem.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                showSellingForm();
            }
        });

        menu.add(menuItem);
        mb.add(menu);

        frame.getRootPane().setJMenuBar(mb);
        frame.getContentPane().add(tablePanel);

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    private void createTablePanel() {
        tablePanel = new JPanel();
        tablePanel.setLayout(new BorderLayout());

        JTable jTab = new JTable(report.getTransactionsForTable(shop
                .getTransactionsArray()[6]), report.getColumnsName());
        jTab.getColumnModel().getColumn(0).setPreferredWidth(20);
        jTab.getColumnModel().getColumn(1).setPreferredWidth(90);
        jTab.getColumnModel().getColumn(3).setPreferredWidth(160);
        jTab.getColumnModel().getColumn(5).setPreferredWidth(60);
        jTab.getColumnModel().getColumn(7).setPreferredWidth(90);
        jTab.getColumnModel().getColumn(8).setPreferredWidth(90);

        tablePanel.add(jTab.getTableHeader(), BorderLayout.PAGE_START);
        tablePanel.add(jTab, BorderLayout.CENTER);
        tablePanel.setVisible(true);
    }

    private void createSellingPanel() {
        sellingPanel = new JPanel();
        sellingPanel.setLayout(new GridBagLayout());

        JLabel lFormName = new JLabel("Make a purchase");
        sellingPanel.add(lFormName, new GridBagConstraints(0, 0, 1, 1, 0, 0,
                GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(
                5, 0, 5, 0), 0, 0));

        JLabel lClientName = new JLabel("Client");
        sellingPanel
                .add(lClientName, new GridBagConstraints(0, 1, 1, 1, 0, 0,
                        GridBagConstraints.LINE_START, 0,
                        new Insets(0, 0, 0, 0), 0, 0));

        cbClientName = new JComboBox<String>(shop.getStringClient());
        sellingPanel
                .add(cbClientName, new GridBagConstraints(1, 1, 1, 1, 0, 0,
                        GridBagConstraints.LINE_START, 0,
                        new Insets(0, 0, 0, 0), 0, 0));

        JLabel lProduct = new JLabel("Product");
        sellingPanel
                .add(lProduct, new GridBagConstraints(0, 2, 1, 1, 0, 0,
                        GridBagConstraints.LINE_START, 0,
                        new Insets(0, 0, 0, 0), 0, 0));

        cbProduct = new JComboBox<String>(shop.getStringProduct());
        cbProduct.setPreferredSize(new Dimension(300, 20));
        // cbProduct.setBounds(0, 0, 100, 10);
        sellingPanel.add(cbProduct, new GridBagConstraints(1, 2, 1, 1, 0, 0,
                GridBagConstraints.LINE_START, GridBagConstraints.NONE,
                new Insets(0, 0, 0, 0), 0, 0));

        JLabel lNumber = new JLabel("Number");
        sellingPanel
                .add(lNumber, new GridBagConstraints(0, 3, 1, 1, 0, 0,
                        GridBagConstraints.LINE_START, 0,
                        new Insets(5, 0, 5, 0), 0, 0));

        NumberFormat nf = NumberFormat.getInstance();
        ftfNumber = new JFormattedTextField(nf);
        ftfNumber.setValue(1);
        ftfNumber.setPreferredSize(new Dimension(30, 20));
        sellingPanel
                .add(ftfNumber, new GridBagConstraints(1, 3, 1, 1, 0, 0,
                        GridBagConstraints.LINE_START, 0,
                        new Insets(5, 0, 5, 0), 0, 0));

        JLabel lConsultant = new JLabel("Consultant");
        sellingPanel
                .add(lConsultant, new GridBagConstraints(0, 4, 1, 1, 0, 0,
                        GridBagConstraints.LINE_START, 0,
                        new Insets(0, 0, 0, 0), 0, 0));

        cbConsultant = new JComboBox<String>(shop.getStringConsultant());
        sellingPanel
                .add(cbConsultant, new GridBagConstraints(1, 4, 1, 1, 0, 0,
                        GridBagConstraints.LINE_START, 0,
                        new Insets(0, 0, 0, 0), 0, 0));

        JLabel lPaymaster = new JLabel("Paymaster");
        sellingPanel.add(lPaymaster, new GridBagConstraints(0, 5, 1, 1, 0, 0,
                GridBagConstraints.LINE_START, GridBagConstraints.HORIZONTAL,
                new Insets(5, 0, 5, 0), 0, 0));

        cbPaymaster = new JComboBox<String>(shop.getStringPaymaster());
        sellingPanel
                .add(cbPaymaster, new GridBagConstraints(1, 5, 1, 1, 0, 0,
                        GridBagConstraints.LINE_START, 0,
                        new Insets(5, 0, 5, 0), 0, 0));

        lResult = new JLabel("Complete");
        lResult.setVisible(false);
        sellingPanel.add(lResult, new GridBagConstraints(0, 7, 1, 1, 0, 0,
                GridBagConstraints.LINE_START, GridBagConstraints.NONE,
                new Insets(10, 0, 0, 0), 0, 0));

        JButton bBuy = new JButton("Buy");
        sellingPanel.add(bBuy, new GridBagConstraints(0, 6, 1, 1, 0, 0,
                GridBagConstraints.LINE_START, GridBagConstraints.NONE,
                new Insets(0, 0, 0, 0), 40, 0));

        bBuy.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                boolean res = false;
                int cn = cbClientName.getSelectedIndex();
                int p = cbProduct.getSelectedIndex();
                int num = Integer.parseInt(ftfNumber.getText());
                int con = cbConsultant.getSelectedIndex();
                int pay = cbPaymaster.getSelectedIndex();
                try {
                    shop.setTransaction(shop.getClients()[cn],
                            shop.getProducts()[p], num,
                            shop.getConsultants()[con],
                            shop.getPaymasters()[pay]);
                    res = true;
                } catch (Exception err) {
                    System.out.println(err);
                }

                if (res) {
                    lResult.setVisible(true);
                }

                showTableForm();
            }
        });
        sellingPanel.setVisible(true);
    }

    private void showSellingForm() {
        createSellingPanel();
        frame.getContentPane().removeAll();
        frame.getContentPane().add(sellingPanel);
        frame.getContentPane().repaint();
        frame.pack();
        frame.repaint();
    }

    private void showTableForm() {
        createTablePanel();
        frame.getContentPane().removeAll();
        frame.getContentPane().add(tablePanel);
        frame.getContentPane().repaint();
        frame.pack();
        frame.repaint();
    }
}
