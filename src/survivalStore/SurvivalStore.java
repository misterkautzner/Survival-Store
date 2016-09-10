package survivalStore;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class SurvivalStore {
	static ArrayList<Product> allProducts = new ArrayList<Product>();
	
	public static void main(String[] args) {
		
		Wallet wallet = new Wallet();
		System.out.println(wallet);

//		ProductsDAO productsDAO = new ProductsDAOImpl();
//
//		
//		Scanner custInput = new Scanner(System.in);
//		
//		giveOriginalOptions();
//		int custChoice = custInput.nextInt();
//		productsDAO.printBy(custChoice);
		
	}
	
	static void giveOriginalOptions() {
		System.out.println("Welcome to the Survival Store!");
		System.out.println("What would you like to do?");
		System.out.println("");
		System.out.println("");
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
