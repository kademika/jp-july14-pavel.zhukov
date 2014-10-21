package com.kademika.day13.shop.f15to17shop;

import com.kademika.day13.shop.f15to17shop.Week;
import com.kademika.day13.shop.f15to17shop.personal.Personal;
import com.kademika.day13.shop.f15to17shop.products.*;

public class Launcher {

	public static void main(String[] args) {
		TestData td = new TestData();
		Report report = new Report();

		Client[] clients = td.arrayClients();
		Personal[] personal = td.arrayPersonal();
		Product[] products = td.arrayProducts();

		Shop shop = new Shop(clients, personal, products);

		getTransactionsList(shop);

		 System.out.println(report.getLastTransaction(shop.getTransactionsArray(),shop));

		ShopUI_v2 ui = new ShopUI_v2(shop, report);

	}

	public static void getTransactionsList(Shop shop) {
		shop.setDay(Week.MONDAY.getId());
		shop.setTransaction(shop.getClients()[0], shop.getProducts()[10], 4,
				shop.getConsultant(), shop.getPaymaster());
		shop.setTransaction(shop.getClients()[1], shop.getProducts()[20], 1,
				shop.getConsultant(), shop.getPaymaster());

		shop.setDay(Week.TUESDAY.getId());
		shop.setTransaction(shop.getClients()[2], shop.getProducts()[26], 2,
				shop.getConsultant(), shop.getPaymaster());
		shop.setTransaction(shop.getClients()[1], shop.getProducts()[30], 1,
				shop.getConsultant(), shop.getPaymaster());
		shop.setTransaction(shop.getClients()[3], shop.getProducts()[40], 3,
				shop.getConsultant(), shop.getPaymaster());
		shop.setTransaction(shop.getClients()[4], shop.getProducts()[45], 1,
				shop.getConsultant(), shop.getPaymaster());

		shop.setDay(Week.WEDNESDAY.getId());
		shop.setTransaction(shop.getClients()[0], shop.getProducts()[36], 1,
				shop.getConsultant(), shop.getPaymaster());
		shop.setTransaction(shop.getClients()[2], shop.getProducts()[28], 2,
				shop.getConsultant(), shop.getPaymaster());

		shop.setDay(Week.THURSDAY.getId());
		shop.setTransaction(shop.getClients()[2], shop.getProducts()[16], 2,
				shop.getConsultant(), shop.getPaymaster());
		shop.setTransaction(shop.getClients()[1], shop.getProducts()[8], 1,
				shop.getConsultant(), shop.getPaymaster());

		shop.setDay(Week.FRIDAY.getId());
		shop.setTransaction(shop.getClients()[2], shop.getProducts()[22], 1,
				shop.getConsultant(), shop.getPaymaster());
		shop.setTransaction(shop.getClients()[1], shop.getProducts()[44], 2,
				shop.getConsultant(), shop.getPaymaster());

		shop.setDay(Week.SATURDAY.getId());
		shop.setTransaction(shop.getClients()[2], shop.getProducts()[46], 5,
				shop.getConsultant(), shop.getPaymaster());
		shop.setTransaction(shop.getClients()[1], shop.getProducts()[15], 1,
				shop.getConsultant(), shop.getPaymaster());
		shop.setTransaction(shop.getClients()[2], shop.getProducts()[48], 3,
				shop.getConsultant(), shop.getPaymaster());
		shop.setTransaction(shop.getClients()[1], shop.getProducts()[11], 1,
				shop.getConsultant(), shop.getPaymaster());
		shop.setTransaction(shop.getClients()[2], shop.getProducts()[33], 2,
				shop.getConsultant(), shop.getPaymaster());
		shop.setTransaction(shop.getClients()[1], shop.getProducts()[41], 1,
				shop.getConsultant(), shop.getPaymaster());

		shop.setDay(Week.SUNDAY.getId());
		shop.setTransaction(shop.getClients()[2], shop.getProducts()[5], 1,
				shop.getConsultant(), shop.getPaymaster());
		shop.setTransaction(shop.getClients()[1], shop.getProducts()[9], 1,
				shop.getConsultant(), shop.getPaymaster());
		shop.setTransaction(shop.getClients()[2], shop.getProducts()[19], 2,
				shop.getConsultant(), shop.getPaymaster());
		shop.setTransaction(shop.getClients()[1], shop.getProducts()[29], 1,
				shop.getConsultant(), shop.getPaymaster());
		shop.setTransaction(shop.getClients()[2], shop.getProducts()[39], 3,
				shop.getConsultant(), shop.getPaymaster());
	}

}
