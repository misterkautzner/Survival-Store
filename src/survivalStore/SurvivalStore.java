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
		//generateInventory();
		//makeConnection();
		ProductsDAO productsDAO = new ProductsDAOImpl();
		//productsDAO.printById();
		productsDAO.printBy(5);
		
//		Scanner custInput = new Scanner(System.in);
//		
//		giveOptions();
//		int custChoice = custInput.nextInt();
//		printAllProducts();
		
		//selectOption(custChoice);
		
	}
	
	//  Gets inventory data from file and stores it as objects in the program
	//  I found this solution online
//	static void generateInventory() {
//		String csvFile = "survival_store_inventory.csv";
//		BufferedReader br = null;
//		//CSVReader br = null;
//		String line = "";
//		
//		try {
//			
//			br = new BufferedReader(new FileReader(csvFile));
//			//CSVReader br = new CSVReader(new StringReader(csvFile));
//			line = br.readLine();  // Throws out column headers
//			
//			while ((line = br.readLine()) != null) {
//				
//				String[] splitByQuotes = line.split("\"");
//				String lineNoQuotes;
//				// If more than one element, then there should be 3 elements
//				//    that were separated by the quotes.
//				if (splitByQuotes.length > 1) {
//					splitByQuotes[1] = splitByQuotes[1].replaceAll(",", "");
//					lineNoQuotes = splitByQuotes[0] + splitByQuotes[1] + splitByQuotes[2];
//				}
//				else {lineNoQuotes = splitByQuotes[0];}
//				
//				String[] product = lineNoQuotes.split(",");
//				
//				String priceString = product[2].substring(1);
//				double price = Double.parseDouble(priceString);
//				int number_in_stock = Integer.parseInt(product[3]);
//				
////				System.out.println("Product category = " + product[0] + "  name = " +
////						product[1] + "   price = " + product[2] + "   number = " + product[3]);
//				Product newProduct = new Product(product[0], product[1], price, number_in_stock);
//				allProducts.add(newProduct);
//			}
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		} finally {
//			if (br != null) {
//				try {
//					br.close();
//				} catch (IOException e) {
//					e.printStackTrace();
//				}
//			}
//		}
//			
//	}
	
	static void giveOptions() {
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
	
	void selectOption(int custChoice) {
		switch (custChoice) {
		case 1: printAllProducts();
			break;
		case 2:
			break;
		case 3:
			break;
		case 4:
			break;
		case 5:
			break;
		}
	}
	
	static void printAllProducts() {
		for (Product product: allProducts) {
			System.out.println(product);
			System.out.println("");
		}
	}
	
//	void sortProducts() {
//		allProducts.sort
//	}

}
