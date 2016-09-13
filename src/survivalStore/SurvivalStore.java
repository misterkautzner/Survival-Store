package survivalStore;

import java.util.ArrayList;
import java.util.Scanner;

public class SurvivalStore {
	public static ArrayList<Product> allProducts = new ArrayList<Product>();
	public static Wallet wallet = new Wallet();
	public static ShoppingCart shoppingCart = new ShoppingCart();
	public static ProductsDAO productsDAO = new ProductsDAOImpl();
	public static Scanner custInput = new Scanner(System.in);
	
	
	public static void main(String[] args) {
		allProducts = (ArrayList<Product>) productsDAO.listBy(1);
		System.out.println("Welcome to the Survival Store!");
		
		int custChoice = 0;
		int secondChoice = 0;
		int thirdChoice = 0;
		int idNum = 0;
		Product p = null;
		
		while(custChoice != 5) {
			secondChoice = 0;
			giveMainOptions();
			custChoice = custInput.nextInt();
			//selectMainOptions(custChoice, productsDAO, wallet, shoppingCart, custInput);
			
			switch (custChoice) {
			// Case 1 and 2 are the same except for the number to be fed into the listBy() method
			// Case 2 needs to feed in 3 (not 2) so we do a quick switch before moving forward.
			case 2: custChoice = 3;
			case 1: productsDAO.printBy(custChoice); // List products by id
				while(secondChoice != 5) {
					secondChoice = giveOptions(secondChoice);
					
				}
				break;
			case 3: shoppingCart.display(custInput, wallet, productsDAO); // View Shopping Cart
			
				break;
			case 4: System.out.println(wallet); // View Wallet
				break;
			case 5: System.out.println("Exiting program...");
				break;
			default: System.out.println(custChoice + " is not a valid option.");
			}
			
		}

		
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
			case 1: productsDAO.listBy(1); // List products by id
					giveOptions(choice);
					//selectOptions(productsDAO, wallet, shoppingCart, in);
				break;
			case 2: productsDAO.listBy(3); // List products by Category
					giveOptions(choice);
					//selectOptions(productsDAO, wallet, shoppingCart, in);
				break;
			case 3: shoppingCart.display(custInput, wallet, productsDAO); // View Shopping Cart
				break;
			case 4: System.out.println(wallet); // View Wallet
				break;
			case 5: System.out.println("Exiting program...");
				break;
			default: System.out.println(choice + " is not a valid option.");
		}
	}
	
	static int giveOptions(int choice) {
		System.out.println("");
		System.out.println("Choose one of the following options:");
		System.out.println("");
		System.out.println("1  Sort the products in a different way");
		System.out.println("2  Add something to your shopping cart");
		System.out.println("3  Display your shopping cart");
		System.out.println("4  Display amount of money in your Wallet ");
		System.out.println("5  Main menu");
		System.out.println("");
		
		int decision = custInput.nextInt();
		selectOptions(decision);
		return decision;
	}
	
	static void sortOptions() {
		System.out.println("");
		System.out.println("Select an option to sort the products by:");
		System.out.println("");
		System.out.println("1  ID");
		System.out.println("2  Name");
		System.out.println("3  Category");
		System.out.println("4  Price");
	}
	
	public static Product findByID(int id) {
		for (Product prod: allProducts) {
			if(prod.id == id) {
				System.out.println("You chose: " + prod.name);
				return prod;
			}
		}
		System.out.println("That ID number does not exist in our system.");
		return null;

		
	}
	
	static void selectOptions(int choice) {
		switch (choice) {
		case 1: sortOptions(); // Sort products differently
				int sortChoice;
				sortChoice = custInput.nextInt();
				productsDAO.printBy(sortChoice); // The number entered is what printBy()
												  //  uses to choose the field.
			//break;
			return;
		case 2: System.out.println("Which product would you like to add to the shopping cart?");
				System.out.println("Please enter the ID number:");
				System.out.println("");
				int idNum;
				idNum = custInput.nextInt();
				// Find the product they want.
				Product p;
				p = findByID(idNum);
				if (p == null)
					break;
				System.out.println("How many " + p.name + "s would you like to add to your cart?");
				int addToCartNum;
				addToCartNum = custInput.nextInt();
				addToCartNum = shoppingCart.add(p, addToCartNum);
				System.out.println(addToCartNum + " added to shopping cart.");
			//break;
			return;
		case 3: shoppingCart.display(custInput, wallet, productsDAO);
			//break;
			return;
		case 4:  System.out.println(wallet);
		default: //break;
			return;
		}
	}
	



}
