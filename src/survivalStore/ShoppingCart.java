package survivalStore;

import java.util.ArrayList;
import java.util.Scanner;

public class ShoppingCart {
	private static ArrayList<Product> shoppingCart = new ArrayList<Product>();
	
	public void display(Scanner input, Wallet wallet, ProductsDAO productsDAO) {
		System.out.println("");
		System.out.println("Shopping Cart:");
		System.out.println("");
		if (shoppingCart.isEmpty()) {
			System.out.println("The shopping cart is empty");
		}
		for (Product product: shoppingCart) {
			System.out.println(product);
		}
		System.out.println("");
		shoppingCartMenu(input, wallet, productsDAO);
	}
	
	public void shoppingCartMenu(Scanner input, Wallet wallet, ProductsDAO productsDAO) {
		System.out.println("What would you like to do?");
		System.out.println("");
		System.out.println("1  Buy a product in the cart");
		System.out.println("2  Remove a product from the cart");
		System.out.println("3  Return to previous menu");
		System.out.println("");
		int cartChoice = input.nextInt();
		int idNum;
		Product p;
		if (cartChoice == 1) {
			System.out.println("Enter the ID of the product you would like to buy:");
			System.out.println("");
			idNum = input.nextInt();
			// Find the product they want.
			p = SurvivalStore.findByID(idNum);
			if (p == null)
				return;
			System.out.println("How many " + p.name + "s would you like to buy?");
			int buyNum;
			buyNum = input.nextInt();
			buy(p, buyNum, wallet, productsDAO);
		}
		if (cartChoice == 2) {
			System.out.println("Enter the ID of the product you would like to remove:");
			System.out.println("");
			idNum = input.nextInt();
			// Find the product they want.
			p = SurvivalStore.findByID(idNum);
			if (p == null)
				return;
			System.out.println("How many " + p.name + "s would you like to remove?");
			int removeNum;
			removeNum = input.nextInt();
			removeNum = remove(p, removeNum);
			System.out.println(removeNum + " removed from cart.");
		}
	}
	
	//  Returns the number of this product that are already in the shopping cart
	public int numInCart(Product product) {
		int numInCart = 0;
		for (Product p: shoppingCart) {
			if (p.id == product.id) {
				numInCart++;
			}
		}
		return numInCart;
	}
	
	public int add(Product product, int quantity) {
		//  The items in the cart are also counted in stock.  Let's not double count!
		//  We can only add items to the cart if they aren't already in it.
		if (product.num_in_stock - numInCart(product) >= quantity) {
			for (int i = 0; i < quantity; i++) {
				shoppingCart.add(product);
				// Must take away from Database
			}
			return quantity;
		} else {System.out.println("Sorry.  We don't have that many in stock.");
		return 0;}
	}
	
	public int remove(Product product, int quantity) {
		int count = 0;
		for (int i = 0; i < quantity; i++) {
			if (shoppingCart.contains(product)) {
				shoppingCart.remove(product);
				count++;
			} else {break;}
		}
		return count;
	}
	public void buy(Product product, int quantity, Wallet wallet, ProductsDAO productsDAO) {
		System.out.println("");
		
		// make sure the quantity of the product is in the shopping cart
		if (quantity > numInCart(product)) {
			System.out.println("Not enough in shopping cart.  Please add enough to shopping cart first.");
			return;
		}
		// Calculate cost... = product.price * quantity
		double cost = product.price * quantity;
		// if cost is less than money in wallet, then approve
		if (cost > wallet.getBalance()) {
			System.out.println("Sorry.  Your wallet doesn't have enough money");
			return;
		}
		

		// remove quantity of product from shoppingCart
		for (int i = 0; i < quantity; i++) {
			shoppingCart.remove(product);
		}
		// reduce product.num_in_stock of product in allProducts by quantity
		product.num_in_stock -= quantity;
		// update the database with info from product in allProducts
		productsDAO.buyUpdate(product);
		// subtract cost from wallet
		wallet.spend(cost);
		String costStr = String.format("%.2f", cost);
		
		String multItems = "";
		if(quantity > 1) {
			multItems = "s";
		}
		System.out.println("You spent $" + costStr + " on " + quantity + " " + product.name + multItems);
		
	}

}
