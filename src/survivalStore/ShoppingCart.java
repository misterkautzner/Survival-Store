package survivalStore;

import java.util.ArrayList;

public class ShoppingCart {
	private static ArrayList<Product> shoppingCart = new ArrayList<Product>();
	
	public void display() {
		System.out.println("");
		System.out.println("Shopping Cart:");
		System.out.println("");
		for (Product product: shoppingCart) {
			System.out.println(product);
		}
		System.out.println("");
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
	
	public void add(Product product, int quantity) {
		//  The items in the cart are also counted in stock.  Let's not double count!
		//  We can only add items to the cart if they aren't already in it.
		if (product.num_in_stock - numInCart(product) >= quantity) {
			for (int i = 0; i < quantity; i++) {
				shoppingCart.add(product);
				// Must take away from Database
			}
		} else {System.out.println("Sorry.  We don't have that many in stock.");}
	}
	
	public void remove(Product product, int quantity) {
		for (int i = 0; i < quantity; i++) {
			shoppingCart.remove(product);
		}
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
