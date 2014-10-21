package com.kademika.day13.shop.f15to17shop;

import java.awt.Dimension;
import java.text.NumberFormat;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

public class ShopUI {

	private Shop shop;

	public ShopUI(Shop shop) {
		this.shop = shop;
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
		panel.setLayout(null);

		JLabel lFormName = new JLabel("Make a purchase");
		lFormName.setBounds(20, 20, 100, 20);

		JLabel lClientName = new JLabel("Client");
		JComboBox<String> cbClientName = new JComboBox<String>(
				shop.getStringClient());
		lClientName.setBounds(20, 50, 100, 20);
		cbClientName.setBounds(70, 50, 120, 20);

		JLabel lProduct = new JLabel("Product");
		JComboBox<String> cbProduct = new JComboBox<String>(
				shop.getStringProduct());
		lProduct.setBounds(20, 80, 100, 20);
		cbProduct.setBounds(70, 80, 700, 20);

		JLabel lNumber = new JLabel("Number");
		NumberFormat nf=NumberFormat.getInstance();
		JFormattedTextField ftfNumber=new JFormattedTextField(nf);
		ftfNumber.setValue(1);		
		lNumber.setBounds(20, 110, 100, 20);
		ftfNumber.setBounds(70, 110, 40, 20);
		
		JLabel lConsultant = new JLabel("Consultant");
		JComboBox<String> cbConsultant = new JComboBox<String>(
				shop.getStringConsultant());
		lConsultant.setBounds(20, 140, 100, 20);
		cbConsultant.setBounds(90, 140, 140, 20);

		JLabel lPaymaster = new JLabel("Paymaster");
		JComboBox<String> cbPaymaster = new JComboBox<String>(
				shop.getStringPaymaster());
		lPaymaster.setBounds(20, 170, 100, 20);
		cbPaymaster.setBounds(90, 170, 140, 20);
		
		JButton bBuy=new JButton("Buy");
		bBuy.setBounds(20, 220, 100, 20);

		panel.add(lFormName);
		panel.add(cbClientName);
		panel.add(lProduct);
		panel.add(lClientName);
		panel.add(cbProduct);
		panel.add(lNumber);
		panel.add(ftfNumber);
		panel.add(lConsultant);
		panel.add(cbConsultant);
		panel.add(lPaymaster);
		panel.add(cbPaymaster);
		panel.add(bBuy);

		return panel;
	}
}
