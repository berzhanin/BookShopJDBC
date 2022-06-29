package sql;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class BookSQL {
	public static void main(String[] args) throws IOException {
		String url = "jdbc:mysql://localhost:3306/bookstoredb";
		String user = "root";
		String password = "12345";
		try (Connection conn = DriverManager.getConnection(url, user, password)) {

			if (conn != null) {
				System.out.println("Connected to the database bookstoredb");
			}
			
			addBook(conn);
			updateBook(conn);
			deleteBook(conn);
			outputAllBooks(conn);

		} catch (SQLException ex) {
			System.out.println("An error occurred. Maybe user/password is invalid");
			ex.printStackTrace();
		}
	}
	
    public static void addBook(Connection conn) throws SQLException, IOException {
        
        String sql = "INSERT INTO book (title, author, description, isbn, price) "
        		+ "VALUES (?,?,?,?,?)";
        PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1,"Fahrenheit 451");
            statement.setString(2,"Ray Bradbury");
            statement.setString(3,"This is Book description");
            statement.setString(4,"100200300");
            statement.setFloat(5, 20.33F);
            
    		int rowsInserted = statement.executeUpdate();
    		if (rowsInserted > 0) {
    			System.out.println("A new book was inserted successfully!");
    		}
    }
	
	private static void outputAllBooks(Connection conn) throws SQLException{
		String sql = "SELECT book_id, title, author, isbn, price FROM book";
		 
		Statement statement = conn.createStatement();
		ResultSet result = statement.executeQuery(sql);
		 
		int count = 0;
		 
		while (result.next()){
		    Integer bookId = result.getInt(1);
		    String author = result.getString("author");
            String title = result.getString("title");
            String isbn = result.getString("isbn");
            Float price = result.getFloat("price");
		    	 
		    String output = "Book #%d: BookID: %d - Title: %s - Author: %s - ISBN: %s - Price: %.2f";
		    System.out.println(String.format(output, ++count, bookId, title, author, isbn, price));
		}
	}
	
	private static void updateBook(Connection conn) throws SQLException{
		String sql = "UPDATE book SET title=?,author=?,description=?,isbn=?,price=? WHERE book_id=?";
		 
		PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1,"Animal farm");
        statement.setString(2,"George Orwell");
        statement.setString(3,"This is Book description");
        statement.setString(4,"5698115555");
        statement.setFloat(5, 24.22F);
        statement.setInt(6, 32);
			 
		int rowsUpdated = statement.executeUpdate();
		if (rowsUpdated > 0) {
		    System.out.println("An existing book was updated successfully!");
		}
	}
	
	private static void deleteBook(Connection conn) throws SQLException{
		String sql = "DELETE FROM book WHERE book_id=?";
		 
		PreparedStatement statement = conn.prepareStatement(sql);
		statement.setInt(1, 31);
		 
		int rowsDeleted = statement.executeUpdate();
		if (rowsDeleted > 0) {
		    System.out.println("A book was deleted successfully!");
		}
	}

/*  
    method bookFindByTitle" = "SELECT * FROM book WHERE title = ?";
	method bookFindByCategory = "SELECT * FROM book JOIN category "
			+ "ON book.category_id = category.category_id AND category.category_id = ?";
	method bookSearch = "SELECT * FROM book WHERE title LIKE '%?%'"
				+ " OR author LIKE '%?%' OR description LIKE '%?%'";
*/
}
