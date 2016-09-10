package survivalStore;

public class Product {
	//private static int idGenerator = 1;
	public int id;
	public String category;
	public String name;
	public Double price;
	public int num_in_stock;
	
	Product(int id, String category, String name, Double price, int num_in_stock) {
		//this.id = idGenerator;
		//idGenerator++;
		this.id = id;
		this.category = category;
		this.name = name;
		this.price = price;
		this.num_in_stock = num_in_stock;
	}
	
	public String toString() {
		
		// First, I'm adding spaces to shorter id's, categories, names, etc
		//     so everything lines up in nice columns.  Then printing.
		String idSpaces = "";
		if (id < 10) { // if there is only one digit
			idSpaces = " "; // then add an extra space
		}
		
		/*
		  Explanation of the following code:
		  
		  For generality, I'll use "field" in place of the abbreviated Product field
		  
		  fieldCharMax is the max number of characters of the field in the Database
		  fieldSpaces is a string of spaces that WILL have appropriate length to buffer
		  		fieldSpaces will be appended after field in the final string
		  numFieldSpaces is the number of spaces needed for the buffer
		*/
		int catCharMax = 23;
		String categorySpaces = "";
		int numCatSpaces = catCharMax - category.length();
		for (int i = 0; i < numCatSpaces; i++) {
			categorySpaces += " ";
		}
		
		int nameCharMax = 53;
		String nameSpaces = "";
		int numNameSpaces = nameCharMax - name.length();
		for (int i = 0; i < numNameSpaces; i++) {
			nameSpaces += " ";
		}
		
		int priceCharMax = 7;
		String priceSpaces = "";
		int numPriceSpaces = priceCharMax - Double.toString(price).length();
		for (int i = 0; i < numPriceSpaces; i++) {
			priceSpaces += " ";
		}
		
		int numStockCharMax = 4;
		String numStockSpaces = "";
		int numNumStockSpaces = numStockCharMax - Integer.toString(num_in_stock).length();
		for (int i = 0; i < numNumStockSpaces; i++) {
			numStockSpaces += " ";
		}
		
		
		//  The following composes the string to be printed.
		//  Some fields have their pieces in different orders
		//     depending on whether it should be right or left aligned.
		String str = "ID:  " + id + idSpaces + 
				"    Category:  " + category + categorySpaces + 
				"    Name:  " + name + nameSpaces + 
				"    Price:  " + priceSpaces + "$" + price +
				"    Num_In_Stock:  " + numStockSpaces + num_in_stock;
		return str;
		// Damn I'm good!
	}
	

//	sellProduct(Product item, int number) {
//		
//	}

}
