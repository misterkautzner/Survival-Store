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
					giveOptions();
					secondChoice = custInput.nextInt();
					switch (secondChoice) {
					case 1: sortOptions(); // Sort products differently
							thirdChoice = custInput.nextInt();
							productsDAO.printBy(thirdChoice); // The number entered is what printBy()
															  //  uses to choose the field.
						break;
					case 2: System.out.println("Which product would you like to add to the shopping cart?");
							System.out.println("Please enter the ID number:");
							System.out.println("");
							idNum = custInput.nextInt();
							// Find the product they want.
							p = findByID(idNum);
							if (p == null)
								break;
							System.out.println("How many " + p.name + "s would you like to add to your cart?");
							int addToCartNum;
							addToCartNum = custInput.nextInt();
							addToCartNum = shoppingCart.add(p, addToCartNum);
							System.out.println(addToCartNum + " added to shopping cart.");
						break;
						
					case 3: shoppingCart.display();
							System.out.println("What would you like to do?");
							System.out.println("");
							System.out.println("1  Buy a product in the cart");
							System.out.println("2  Remove a product from the cart");
							System.out.println("3  Return to previous menu");
							System.out.println("");
							thirdChoice = custInput.nextInt();
							if (thirdChoice == 1) {
								System.out.println("Enter the ID of the product you would like to buy:");
								System.out.println("");
								idNum = custInput.nextInt();
								// Find the product they want.
								p = findByID(idNum);
								if (p == null)
									break;
								System.out.println("How many " + p.name + "s would you like to buy?");
								int buyNum;
								buyNum = custInput.nextInt();
								shoppingCart.buy(p, buyNum, wallet, productsDAO);
							}
							if (thirdChoice == 2) {
								System.out.println("Enter the ID of the product you would like to remove:");
								System.out.println("");
								idNum = custInput.nextInt();
								// Find the product they want.
								p = findByID(idNum);
								if (p == null)
									break;
								System.out.println("How many " + p.name + "s would you like to remove?");
								int removeNum;
								removeNum = custInput.nextInt();
								removeNum = shoppingCart.remove(p, removeNum);
								System.out.println(removeNum + " removed from cart.");
							}
						break;
					case 4:  System.out.println(wallet);
					default: break;
					}
				}
				break;
			case 3: shoppingCart.display(); // View Shopping Cart
				break;
			case 4: System.out.println(wallet); // View Wallet
				break;
			case 5: System.out.println("Exiting program...");
				break;
			default: System.out.println(custChoice + " is not a valid option.");
			}
			
		}
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
	
	static void selectMainOptions(int choice, ProductsDAO productsDAO, Wallet wallet, ShoppingCart shoppingCart, Scanner in) {
		switch (choice) {
			case 1: productsDAO.listBy(1); // List products by id
					giveOptions();
					selectOptions(productsDAO, wallet, shoppingCart, in);
				break;
			case 2: productsDAO.listBy(3); // List products by Category
					giveOptions();
					selectOptions(productsDAO, wallet, shoppingCart, in);
				break;
			case 3: shoppingCart.display(); // View Shopping Cart
				break;
			case 4: System.out.println(wallet); // View Wallet
				break;
			case 5: System.out.println("Exiting program...");
				break;
			default: System.out.println(choice + " is not a valid option.");
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
		System.out.println("5  Main menu");
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
	
	static Product findByID(int id) {
		for (Product prod: allProducts) {
			if(prod.id == id) {
				System.out.println("You chose: " + prod.name);
				return prod;
			}
		}
		System.out.println("That ID number does not exist in our system.");
		return null;

		
	}
	
	static void selectOptions(ProductsDAO productsDAO, Wallet wallet, ShoppingCart shoppingCart, Scanner in) {
		
	}
	



}
