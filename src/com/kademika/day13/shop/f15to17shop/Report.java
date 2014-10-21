package com.kademika.day13.shop.f15to17shop;

import com.kademika.day13.shop.f15to17shop.Transaction;
import com.kademika.day13.shop.f15to17shop.products.Product;

public class Report {
	private int[] productsOnWeek;

	public Report() {
		productsOnWeek = new int[7];
	}

	private int getLengthArrayProducts(Product[] products) {
		int t = 0;
		for (int i = 0; i < products.length; i++) {
			if (products[i] != null) {
				t = i;
			}
		}
		return t + 1;
	}

	private int getLengthArrayTransactions(Transaction[] transaction) {
		int t = 0;
		for (int i = 0; i < transaction.length; i++) {
			if (transaction[i] != null) {
				t = i;
			}
		}
		return t + 1;
	}

	private boolean isNullCopy(Product prod) {
		if (prod.getId().equals("0000")) {
			return true;
		}
		return false;
	}

	public boolean isNullStr(String str) {
		if (str.equals("Null copy") || str.equals("Null transaction")) {
			return true;
		}
		return false;
	}

	public String[] getPriceProducts(Product[] products) {
		int p = 0;
		String[] info = new String[getLengthArrayProducts(products)];
		for (int i = 0; i < getLengthArrayProducts(products); i++) {
			if (!isNullCopy(products[i]) && !products[i].isDeleted()) {
				info[i] = "ID: " + products[i].getId() + "	Model: "
						+ products[i].getModel() + "	Producer: "
						+ products[i].getProducer() + "	\nDepartment: "
						+ products[i].getDept() + "	Price: "
						+ products[i].getPrice() + " RUB";
				p++;
			} else {
				info[i] = "Null copy";
			}
		}
		System.out.println("In the shop found " + p + " types of products");
		System.out.println("-----------------------------------------");
		return info;
	}

	public String[] getNumberProducts(Product[] products) {
		int count = 0;
		String[] info = new String[getLengthArrayProducts(products)];
		for (int i = 0; i < getLengthArrayProducts(products); i++) {
			if (!isNullCopy(products[i]) && !products[i].isDeleted()) {
				info[i] = "ID: " + products[i].getId() + "	Model: "
						+ products[i].getModel() + "	\nNumber: "
						+ products[i].getNumber();
				count += products[i].getNumber();
			} else {
				info[i] = "Null copy";
			}
		}
		System.out.println("In the shop found " + count + " products");
		System.out.println("-----------------------------------------");
		return info;
	}

	public String[] printDeleteProducts(Product[] deleteProducts) {
		String[] info = new String[getLengthArrayProducts(deleteProducts)];
		for (int i = 0; i < getLengthArrayProducts(deleteProducts); i++) {
			info[i] = "ID: " + deleteProducts[i].getId() + "	Model: "
					+ deleteProducts[i].getModel() + "	 Producer: "
					+ deleteProducts[i].getProducer() + " \nDepartment: "
					+ deleteProducts[i].getDept() + "	Price: "
					+ deleteProducts[i].getPrice();
		}
		return info;
	}

	public String[] getOneDayTransactions(Transaction[] transactions) {
		double oneDayTotalPrice = 0;
		int oneDayProducts = 0;
		if (transactions[0] != null) {
			String[] info = new String[getLengthArrayTransactions(transactions)];
			for (int i = 0; i < getLengthArrayTransactions(transactions); i++) {
				if (transactions[i] != null) {
					oneDayProducts += transactions[i].getNumber();
					oneDayTotalPrice += transactions[i].getTotalPrice();
					info[i] = "ID: " + transactions[i].getNumTransaction()
							+ "	Client: "
							+ transactions[i].getClient().getFio()
							+ "	ID product: " + transactions[i].getIdProd()
							+ "	Model: " + transactions[i].getModel()
							+ "\nPrice: " + transactions[i].getPrice()
							+ "		Number: " + transactions[i].getNumber()
							+ "	Total: " + transactions[i].getTotalPrice()
							+ "\nConsultant: "
							+ transactions[i].getConsultant().getFio()
							+ "	Paymaster: "
							+ transactions[i].getPaymaster().getFio();
				} else {
					info[i] = "Null transaction";
				}
			}
			System.out.println("Total:	In this day sold " + oneDayProducts
					+ " pcs. of products for the total amount "
					+ oneDayTotalPrice + " RUB");
			System.out.println("-----------------------------------------");
			return info;
		} else {
			String[] info = new String[1];
			info[0] = "No transactions";
			return info;
		}
	}

	public String[][] getWeekTransactions(Transaction[][] transactionsArray) {
		int weekProducts = 0;
		double weekTotalPrice = 0;
		String[][] infoArray = new String[7][30];
		for (int i = 0; i < 7; i++) {
			if (transactionsArray[i] != null) {
				for (int j = 0; j < 30; j++) {
					if (transactionsArray[i][j] != null) {
						weekProducts += transactionsArray[i][j].getNumber();
						weekTotalPrice += transactionsArray[i][j]
								.getTotalPrice();
						infoArray[i][j] = "ID: "
								+ transactionsArray[i][j].getNumTransaction()
								+ "	Client: "
								+ transactionsArray[i][j].getClient().getFio()
								+ "	ID product: "
								+ transactionsArray[i][j].getIdProd()
								+ "	Model: "
								+ transactionsArray[i][j].getModel()
								+ "\nPrice: "
								+ transactionsArray[i][j].getPrice()
								+ "		Number: "
								+ transactionsArray[i][j].getNumber()
								+ "	Total: "
								+ transactionsArray[i][j].getTotalPrice()
								+ "\nConsultant: "
								+ transactionsArray[i][j].getConsultant()
										.getFio()
								+ "	Paymaster: "
								+ transactionsArray[i][j].getPaymaster()
										.getFio();
					} else {
						infoArray[i][j] = "Null transaction";
					}
				}
			}
		}
		System.out.println("Total:	In this week sold " + weekProducts
				+ " pcs. of products for the total amount " + weekTotalPrice
				+ " RUB");
		System.out.println("-----------------------------------------");
		return infoArray;
	}

	public String getNumWeekSoldProducts(Transaction[][] transactionsArray) {
		String tmp = "";
		for (int i = 0; i < transactionsArray.length; i++) {
			if (transactionsArray[i] != null) {
				for (int j = 0; j < transactionsArray[i].length; j++) {
					if (transactionsArray[i][j] != null) {
						productsOnWeek[i] += transactionsArray[i][j]
								.getNumber();
					}
				}
				tmp += " " + productsOnWeek[i];
			}
		}
		return "Number of products sold per week " + tmp;
	}

	public String getNumWeekTransactions(Transaction[][] transactionsArray) {
		String tmp = "";
		int count = 0;
		for (int i = 0; i < transactionsArray.length; i++) {
			if (transactionsArray[i] != null) {
				for (int j = 0; j < transactionsArray[i].length; j++) {
					if (transactionsArray[i][j] != null) {
						count++;
					}
				}
				tmp += " " + count;
			}
			count = 0;
		}
		return "Number of purchases per week " + tmp;
	}

	public String[] getCategoryProducts(Product[] products) {
		String[] info = new String[getLengthArrayProducts(products)];
		for (int i = 0; i < getLengthArrayProducts(products); i++) {
			info[i] = products[i].getInfo();
		}
		return info;
	}

	public String getLastTransaction(Transaction[][] transactionsArray,
			Shop shop) {
		Transaction[] tmp = transactionsArray[shop.getDay()-1];
		int k = -1;
		String res;
		for (int i = tmp.length - 1; i >= 0; i--) {
			if (tmp[i] == null) {
				k = i;
			}
		}
		res = "ID: " + tmp[k - 1].getNumTransaction() + "	Client: "
				+ tmp[k - 1].getClient().getFio() + "	ID product: "
				+ tmp[k - 1].getIdProd() + "	Model: " + tmp[k - 1].getModel()
				+ "\nPrice: " + tmp[k - 1].getPrice() + "		Number: "
				+ tmp[k - 1].getNumber() + "	Total: "
				+ tmp[k - 1].getTotalPrice() + "\nConsultant: "
				+ tmp[k - 1].getConsultant().getFio() + "	Paymaster: "
				+ tmp[k - 1].getPaymaster().getFio();
		return res;
	}
}
