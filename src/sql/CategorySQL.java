package sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CategorySQL {
	public static void main(String[] args) {
		String url = "jdbc:mysql://localhost:3306/bookstoredb";
		String user = "root";
		String password = "12345";
		try (Connection conn = DriverManager.getConnection(url, user, password)) {

			if (conn != null) {
				System.out.println("Connected to the database bookstoredb");
			}
			
			addCategory(conn);
			outputAllCategories(conn);
			updateCategory(conn);
			deleteCategory(conn);

		} catch (SQLException ex) {
			System.out.println("An error occurred. Maybe user/password is invalid");
			ex.printStackTrace();
		}
	}
	
	private static void addCategory(Connection conn) throws SQLException {
		String sql = "INSERT INTO category (name) VALUES (?)";

		PreparedStatement statement = conn.prepareStatement(sql);
		statement.setString(1, "TestCategory");

		int rowsInserted = statement.executeUpdate();
		if (rowsInserted > 0) {
			System.out.println("A new category was inserted successfully!");
		}
	}
	
	private static void outputAllCategories(Connection conn) throws SQLException{
		String sql = "SELECT * FROM category";
		 
		Statement statement = conn.createStatement();
		ResultSet result = statement.executeQuery(sql);
		 
		int count = 0;
		 
		while (result.next()){
		    Integer categoryId = result.getInt(1);
		    String name = result.getString(2);
		    	 
		    String output = "Category #%d: %s - %s";
		    System.out.println(String.format(output, ++count, categoryId, name));
		}
	}
	
	private static void updateCategory(Connection conn) throws SQLException{
		String sql = "UPDATE category SET name=? WHERE category_id=?";
		 
		PreparedStatement statement = conn.prepareStatement(sql);
		statement.setString(1, "TestCategory222");
		statement.setInt(2, 79);
			 
		int rowsUpdated = statement.executeUpdate();
		if (rowsUpdated > 0) {
		    System.out.println("An existing category was updated successfully!");
		}
	}
	
	private static void deleteCategory(Connection conn) throws SQLException{
		String sql = "DELETE FROM category WHERE category_id=?";
		 
		PreparedStatement statement = conn.prepareStatement(sql);
		statement.setInt(1, 79);
		 
		int rowsDeleted = statement.executeUpdate();
		if (rowsDeleted > 0) {
		    System.out.println("A category was deleted successfully!");
		}
	}
}
