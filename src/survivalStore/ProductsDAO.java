package survivalStore;

import java.util.List;

public interface ProductsDAO {

	public List<Product> listBy(int sorter);
	
	public void printBy(int sorter);
}
