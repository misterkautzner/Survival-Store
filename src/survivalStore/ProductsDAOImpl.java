package survivalStore;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductsDAOImpl implements ProductsDAO {

	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		}
		catch (ClassNotFoundException ex) {
		}
	}
	
	private Connection getConnection() throws SQLException {
		// 
		return DriverManager.getConnection("jdbc:mysql://localhost:3306/survival_store?autoReconnect=true&useSSL=false",
				"john", "john");
	}
	
	private void closeConnection(Connection connection) {
		if (connection == null)
			return;
		try {
			connection.close();
		}
		catch (SQLException ex) {
		}
	}

	@Override
	public List<Product> listBy(int sorter) {
		List<Product> result = new ArrayList<Product>();
		
		String sortBy = " ORDER BY ";
		switch (sorter) {
			case 2: sortBy += "name";
				break;
			case 3: sortBy += "category";
				break;
			case 4: sortBy += "price";
				break;
			default: sortBy = "";
		}

		String sql = "SELECT * FROM products" + sortBy;

		Connection connection = null;
		try {
			connection = getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				Product product = new Product(resultSet.getInt("id"), 
						resultSet.getString("category"),
						resultSet.getString("name"),
						resultSet.getDouble("price"),
						resultSet.getInt("Num_in_Stock"));
				
				result.add(product);
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			closeConnection(connection);
		}
		return result;
	}
	
	public void printBy(int sorter) {
		List<Product> list = listBy(sorter);
		for (Product product: list) {
			System.out.println(product);
		}
	}
	
	public void buyUpdate(Product prod) {

		String sql = "UPDATE products SET num_in_stock = " + prod.num_in_stock + " WHERE id = " + prod.id + ";";

		Connection connection = null;
		try {
			connection = getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.executeUpdate();

		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			closeConnection(connection);
		}
	}

}
