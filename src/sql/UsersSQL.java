package sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UsersSQL {
	public static void main(String[] args) {
		String url = "jdbc:mysql://localhost:3306/bookstoredb";
		String user = "root";
		String password = "12345";
		try (Connection conn = DriverManager.getConnection(url, user, password)) {

			if (conn != null) {
				System.out.println("Connected to the database bookstoredb");
			}
			
			addUser(conn);
			updateUser(conn);
			deleteUser(conn);
			outputAllUsers(conn);
		} catch (SQLException ex) {
			System.out.println("An error occurred. Maybe user/password is invalid");
			ex.printStackTrace();
		}
	}
	
	private static void addUser(Connection conn) throws SQLException {
		String sql = "INSERT INTO users (email, password, full_name) VALUES (?, ?, ?)";

		PreparedStatement statement = conn.prepareStatement(sql);
		statement.setString(1, "avengers@gmail.com");
		statement.setString(2, "filme");
		statement.setString(3, "Marvel film");

		int rowsInserted = statement.executeUpdate();
		if (rowsInserted > 0) {
			System.out.println("A new user was inserted successfully!");
		}
	}
	
	private static void outputAllUsers(Connection conn) throws SQLException{
		String sql = "SELECT * FROM users";
		 
		Statement statement = conn.createStatement();
		ResultSet result = statement.executeQuery(sql);
		 
		int count = 0;
		 
		while (result.next()){
		    Integer userId = result.getInt(1);
		    String email = result.getString("email");
		    String password = result.getString("password");
		    String fullName = result.getString("full_name");
		    	 
		    String output = "User #%d: %s - %s - %s - %s";
		    System.out.println(String.format(output, ++count, userId, email, password, fullName));
		}
	}
	
	private static void updateUser(Connection conn) throws SQLException{
		String sql = "UPDATE users SET email=?, password=?, full_name=? WHERE user_id=?";
		 
		PreparedStatement statement = conn.prepareStatement(sql);
		statement.setString(1, "avengers@gmail.com");
		statement.setString(2, "cinema");
		statement.setString(3, "Marvel film");
		statement.setInt(4, 67);
			 
		int rowsUpdated = statement.executeUpdate();
		if (rowsUpdated > 0) {
		    System.out.println("An existing user was updated successfully!");
		}
	}
	
	private static void deleteUser(Connection conn) throws SQLException{
		String sql = "DELETE FROM users WHERE user_id=?";
		 
		PreparedStatement statement = conn.prepareStatement(sql);
		statement.setInt(1, 66);
		 
		int rowsDeleted = statement.executeUpdate();
		if (rowsDeleted > 0) {
		    System.out.println("A user was deleted successfully!");
		}
	}
}
