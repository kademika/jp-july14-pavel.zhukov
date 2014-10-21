package com.kademika.day13.shop.f15to17shop;

import com.kademika.day13.shop.f15to17shop.personal.JobRole;
import com.kademika.day13.shop.f15to17shop.personal.Personal;
import com.kademika.day13.shop.f15to17shop.products.*;

public class Shop {
	private Personal[][] personal;
	private Client[] clients;
	private Audio[] audios;
	private Computer[] computers;
	private Component[] components;
	private Phone[] phones;
	private Video[] videos;
	private Report report;
	private Personal consultant;
	private Personal paymaster;
	private Product[] products;
	private Product[] deleteProducts;
	private Product[][] boughtProducts;
	private int[][] numberBoughtProduct;
	private Transaction[][] transactionsArray;
	private int idBuyProduct = 100;
	private int day;
	private boolean isBought = false;
	private int idNullCopy = 100;
	private int idTransaction;

	public Shop(Client[] arrayClients, Personal[] arrayPersonal,
			Product[] arrayProducts) {
		personal = new Personal[2][20];
		clients = new Client[20];
		audios = new Audio[20];
		computers = new Computer[20];
		components = new Component[20];
		phones = new Phone[20];
		videos = new Video[20];
		deleteProducts = new Product[30];
		boughtProducts = new Product[7][30];
		numberBoughtProduct = new int[7][30];
		transactionsArray = new Transaction[7][30];
		report = new Report();

		products = arrayProducts;
		createClients(arrayClients);
		createPersonal(arrayPersonal);
		createProducts(arrayProducts);
	}

	// ****************************************************************************

	public void createClients(Client[] clients) {
		this.clients = clients;
	}

	public void createPersonal(Personal[] personal) {
		for (Personal pers : personal) {
			addPersonal(pers);
		}
	}

	private void addPersonal(Personal pers) {
		if (pers.getRole() == JobRole.CONSULTANT) {
			for (int i = 0; i < personal[0].length; i++) {
				if (personal[0][i] == null) {
					personal[0][i] = pers;
					return;
				}
			}
		} else {
			for (int i = 0; i < personal[1].length; i++) {
				if (personal[1][i] == null) {
					personal[1][i] = pers;
					return;
				}
			}
		}
	}

	public void createProducts(Product[] products) {
		for (Product prod : products) {
			addProducts(prod);
		}
	}

	private void addProducts(Product prod) {
		if (prod instanceof Audio) {
			addToCategory(audios, prod);
		} else if (prod instanceof Computer) {
			addToCategory(computers, prod);
		} else if (prod instanceof Phone) {
			addToCategory(phones, prod);
		} else if (prod instanceof Video) {
			addToCategory(videos, prod);
		} else {
			addToCategory(components, prod);
		}
	}

	private void addToCategory(Product[] products, Product product) {
		for (int i = 0; i < products.length; i++) {
			if (products[i] == null) {
				products[i] = product;
				return;
			}
		}
	}

	// ****************************************************************************

	public void deleteProduct(Product prod) {
		if (prod instanceof Audio) {
			deleteProductFromCategory(audios, prod);
		} else if (prod instanceof Computer) {
			deleteProductFromCategory(computers, prod);
		} else if (prod instanceof Phone) {
			deleteProductFromCategory(phones, prod);
		} else if (prod instanceof Video) {
			deleteProductFromCategory(videos, prod);
		} else {
			deleteProductFromCategory(components, prod);
		}
	}

	private Product[] deleteProductFromCategory(Product[] arrayProducts,
			Product prod) {
		int id = 0;
		for (int i = 0; i < arrayProducts.length; i++) {
			if (arrayProducts[i] != null
					&& arrayProducts[i].getId().equals(prod.getId())) {
				id = i;
				break;
			}
		}
		setDeleteProduct(arrayProducts[id]);
		swapArray(arrayProducts, arrayProducts[id], id);
		arrayProducts[idNullCopy].getNullCopy();
		return arrayProducts;
	}

	private void setDeleteProduct(Product prod) {
		for (int i = 0; i < deleteProducts.length; i++) {
			if (deleteProducts[i] == null) {
				deleteProducts[i] = prod;
				return;
			}
		}
	}

	private Product[] swapArray(Product[] products, Product tmp, int x) {
		if (products instanceof Audio[]) {
			return swap(audios, audios[x], x);
		} else if (products instanceof Computer[]) {
			return swap(computers, computers[x], x);
		} else if (products instanceof Phone[]) {
			return swap(phones, phones[x], x);
		} else if (products instanceof Video[]) {
			return swap(videos, videos[x], x);
		} else {
			return swap(components, components[x], x);
		}

	}

	private Product[] swap(Product[] products, Product tmp, int x) {
		for (int i = x; i < products.length - 1; i++) {
			if (products[i + 1] != null) {
				tmp = products[i];
				products[i] = products[i + 1];
				products[i + 1] = tmp;
				idNullCopy = i + 1;
			}
		}
		return products;
	}

	// ****************************************************************************
	public void setTransaction(Client client, Product prod, int number,
			Personal consultant, Personal paymaster) {
		Product[] products = typeProduct(prod);
		if (isAvailable(products, prod)) {
			buyProduct(products, prod, number);
			if (isBought) {
				Transaction tr = new Transaction(client, prod, number,
						getConsultant(), getPaymaster(), number);
				idTransaction++;
				tr.setNumTransaction(idTransaction);
				addTransaction(tr, day);
			}
		} else {
			System.out.println("Transaction failed. Check the entered data");
		}
	}

	private void addTransaction(Transaction transaction, int day) {
		for (int i = 0; i < transactionsArray[day - 1].length; i++) {
			if (transactionsArray[day - 1][i] == null) {
				transactionsArray[day - 1][i] = transaction;
				return;
			}
		}
	}

	private boolean isAvailable(Product[] products, Product prod) {
		for (int i = 0; i < products.length; i++) {
			if (products[i].getId().equals(prod.getId())
					&& prod.getNumber() > 0 && !prod.isDeleted()) {
				if (products[i].getNumber() > 0) {
					idBuyProduct = i;
					return true;
				}
			}
		}
		return false;
	}

	public Product[] typeProduct(Product prod) {
		Product[] activeProducts;
		if (prod instanceof Audio) {
			activeProducts = getAudios();
		} else if (prod instanceof Computer) {
			activeProducts = getComputers();
		} else if (prod instanceof Phone) {
			activeProducts = getPhones();
		} else if (prod instanceof Video) {
			activeProducts = getVideos();
		} else {
			activeProducts = getComponents();
		}
		return activeProducts;
	}

	public Product[] buyProduct(Product[] products, Product prod, int number) {
		if (number <= products[idBuyProduct].getNumber()) {
			setBuyProduct(prod, number, day);
			products[idBuyProduct].setNumber(products[idBuyProduct].getNumber()
					- number);
			isBought = true;
			if (products[idBuyProduct].getNumber() == 0) {
				deleteProduct(products[idBuyProduct]);
			}
		} else {
			System.out.println("ID product: " + prod.getId()
					+ "	Sorry, we have only " + prod.getNumber()
					+ " copies of this product");
		}
		return products;
	}

	private void setBuyProduct(Product prod, int number, int day) {
		for (int i = 0; i < boughtProducts[day - 1].length; i++) {
			if (boughtProducts[day - 1][i] == null) {
				boughtProducts[day - 1][i] = prod;
				numberBoughtProduct[day - 1][i] = number;
				return;
			}
		}
	}

	// ****************************************************************************

	public String[] getProductsByCategory(Department dept) {
		if (dept == Department.AUDIO) {
			return report.getCategoryProducts(audios);
		} else if (dept == Department.COMPUTERS) {
			return report.getCategoryProducts(computers);
		} else if (dept == Department.PHONES) {
			return report.getCategoryProducts(phones);
		} else if (dept == Department.VIDEO) {
			return report.getCategoryProducts(videos);
		} else {
			return report.getCategoryProducts(components);
		}
	}

	// ****************************************************************************

	public int getDay() {
		return day;
	}

	public void setDay(int day) {
		this.day = day;
	}

	public Audio[] getAudios() {
		return audios;
	}

	public Computer[] getComputers() {
		return computers;
	}

	public Component[] getComponents() {
		return components;
	}

	public Phone[] getPhones() {
		return phones;
	}

	public Video[] getVideos() {
		return videos;
	}

	public Personal getConsultant() {
		return personal[0][0];
	}

	public Personal getPaymaster() {
		return personal[1][0];
	}

	public Personal[] getConsultants() {
		return personal[0];
	}

	public Personal[] getPaymasters() {
		return personal[1];
	}
	
	public Product[] getProducts() {
		return products;
	}

	public Product[] getDeleteProducts() {
		return deleteProducts;
	}

	public Product[][] getBoughtProducts() {
		return boughtProducts;
	}

	public int[][] getNumberBoughtProduct() {
		return numberBoughtProduct;
	}

	public Transaction[][] getTransactionsArray() {
		return transactionsArray;
	}

	public Client[] getClients() {
		return clients;
	}

	public String[] getStringClient() {
		String[] stringClient = new String[clients.length];
		for (int i = 0; i < clients.length; i++) {
			if (clients[i] != null) {
				stringClient[i] = clients[i].getFio();
			}
		}
		return stringClient;
	}

	public String[] getStringProduct() {
		String[] stringProduct = new String[products.length];
		for (int i = 0; i < products.length; i++) {
			if (products[i] != null) {
				stringProduct[i] = products[i].getModel();
			}
		}
		return stringProduct;
	}

	public String[] getStringConsultant() {
		String[] stringConsultant = new String[personal[0].length];
		for (int i = 0; i < personal[0].length; i++) {
			if (personal[0][i] != null) {
				stringConsultant[i] = personal[0][i].getFio();
			}
		}
		return stringConsultant;
	}
	
	public String[] getStringPaymaster() {
		String[] stringPaymaster = new String[personal[1].length];
		for (int i = 0; i < personal[1].length; i++) {
			if (personal[1][i] != null) {
				stringPaymaster[i] = personal[1][i].getFio();
			}
		}
		return stringPaymaster;
	}

	public int getIdTransaction() {
		return idTransaction;
	}		
	
}
