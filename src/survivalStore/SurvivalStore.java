package survivalStore;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SurvivalStore {
	public static ArrayList<Product> allProducts = new ArrayList<Product>();
	
	public static void main(String[] args) {
		Wallet wallet = new Wallet();
		ShoppingCart shoppingCart = new ShoppingCart();
		ProductsDAO productsDAO = new ProductsDAOImpl();
		allProducts = (ArrayList<Product>) productsDAO.listBy(1);
		System.out.println("Welcome to the Survival Store!");
		
		Scanner custInput = new Scanner(System.in);
		giveMainOptions();
		int custChoice = custInput.nextInt();
		selectMainOptions(custChoice);
//		shoppingCart.add(allProducts.get(1), 2);
//		shoppingCart.add(allProducts.get(5), 3);
//		shoppingCart.add(allProducts.get(8), 5);
//		
//		shoppingCart.display();
//		shoppingCart.remove(allProducts.get(8), 2);
		
		//shoppingCart.buy(allProducts.get(5), 2, wallet, productsDAO);
		allProducts = (ArrayList<Product>) productsDAO.listBy(1);
//		shoppingCart.display();
//		System.out.println(wallet);

//
//		
//		Scanner custInput = new Scanner(System.in);
//		
//		giveOriginalOptions();
//		int custChoice = custInput.nextInt();
//		productsDAO.printBy(custChoice);
		
	}
	
	
	static void giveMainOptions() {
		System.out.println("");
		System.out.println("What would you like to do?");
		System.out.println("Please enter a number corresponding to one of the options below:");
		System.out.println("");
		System.out.println("1  See all products");
		System.out.println("2  See products by category");
		System.out.println("3  View shopping Cart");
		System.out.println("4  View wallet");
		System.out.println("5  Exit program");
		System.out.println("");
		System.out.println("");
	}
	
	static void selectMainOptions(int choice) {
		switch (choice) {
			case 1:
				break;
			default: break;
		}
	}
	
	static void giveOptions() {
		System.out.println("");
		System.out.println("Choose one of the following options:");
		System.out.println("");
		System.out.println("1  Sort the products in a different way");
		System.out.println("2  Add something to your shopping cart");
		System.out.println("3  Display your shopping cart");
		System.out.println("4  Display amount of money in your Wallet ");
	}
	



}
