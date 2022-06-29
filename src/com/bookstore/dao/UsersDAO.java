package com.bookstore.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.bookstore.entity.Users;

public class UsersDAO {
	private String jdbcURL = "jdbc:mysql://localhost:3306/bookstoredb";
	private String jdbcUsername = "root";
	private String jdbcPassword = "12345";
	private Connection jdbcConnection;

	public UsersDAO() {
	}

	public UsersDAO(String jdbcURL, String jdbcUsername, String jdbcPassword) {
		this.jdbcURL = jdbcURL;
		this.jdbcUsername = jdbcUsername;
		this.jdbcPassword = jdbcPassword;
	}

	protected void connect() throws SQLException {
		if (jdbcConnection == null || jdbcConnection.isClosed()) {
			try {
				Class.forName("com.mysql.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				throw new SQLException(e);
			}
			jdbcConnection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
		}
	}

	protected void disconnect() throws SQLException {
		if (jdbcConnection != null && !jdbcConnection.isClosed()) {
			jdbcConnection.close();
		}
	}

	public List<Users> listAllUsers() throws SQLException {
		List<Users> listUsers = new ArrayList<>();

		String sql = "SELECT * FROM users";

		connect();

		Statement statement = jdbcConnection.createStatement();
		ResultSet resultSet = statement.executeQuery(sql);

		while (resultSet.next()) {
			int userId = resultSet.getInt("user_id");
			String email = resultSet.getString("email");
			String password = resultSet.getString("password");
			String fullName = resultSet.getString("full_name");

			Users user = new Users(userId, email, password, fullName);
			listUsers.add(user);
		}

		resultSet.close();
		statement.close();

		disconnect();

		return listUsers;
	}

	public boolean createUser(Users user) throws SQLException {
		String sql = "INSERT INTO users (email, full_name, password) VALUES (?, ?, ?)";
		connect();

		PreparedStatement statement = jdbcConnection.prepareStatement(sql);
		statement.setString(1, user.getEmail());
		statement.setString(2, user.getFullName());
		statement.setString(3, user.getPassword());

		boolean rowInserted = statement.executeUpdate() > 0;
		statement.close();
		disconnect();
		return rowInserted;
	}

	public Users getUser(int userId) throws SQLException {
		Users user = null;
		String sql = "SELECT * FROM users WHERE user_id = ?";

		connect();

		PreparedStatement statement = jdbcConnection.prepareStatement(sql);
		statement.setInt(1, userId);

		ResultSet resultSet = statement.executeQuery();

		if (resultSet.next()) {
			String email = resultSet.getString("email");
			String password = resultSet.getString("password");
			String fullName = resultSet.getString("full_name");

			user = new Users(userId, email, password, fullName);
		}

		resultSet.close();
		statement.close();

		return user;
	}

	public boolean updateUser(Users user) throws SQLException {
		String sql = "UPDATE users SET email = ?, password = ?, full_name = ?";
		sql += " WHERE user_id = ?";
		connect();

		PreparedStatement statement = jdbcConnection.prepareStatement(sql);
		statement.setString(1, user.getEmail());
		statement.setString(2, user.getPassword());
		statement.setString(3, user.getFullName());
		statement.setInt(4, user.getUserId());

		boolean rowUpdated = statement.executeUpdate() > 0;
		statement.close();
		disconnect();
		return rowUpdated;
	}

	public boolean deleteUser(Users user) throws SQLException {
		String sql = "DELETE FROM users where user_id = ?";

		connect();

		PreparedStatement statement = jdbcConnection.prepareStatement(sql);
		statement.setInt(1, user.getUserId());

		boolean rowDeleted = statement.executeUpdate() > 0;
		statement.close();
		disconnect();
		return rowDeleted;
	}

	public Users findUserByEmail(String email) throws SQLException {
		Users user = null;
		String sql = "SELECT * FROM users WHERE email = ?";

		connect();

		PreparedStatement statement = jdbcConnection.prepareStatement(sql);
		statement.setString(1, email);

		ResultSet resultSet = statement.executeQuery();

		if (resultSet.next()) {
			email = resultSet.getString("email");
			String password = resultSet.getString("password");
			String fullName = resultSet.getString("full_name");

			user = new Users(email, password, fullName);
		}

		resultSet.close();
		statement.close();

		return user;
	}
	
	public Users checkLogin(String email, String password) throws SQLException {
		Users user = null;
		String sql = "SELECT * FROM users WHERE email = ? AND password = ?";

		connect();

		PreparedStatement statement = jdbcConnection.prepareStatement(sql);
		statement.setString(1, email);
		statement.setString(2, password);

		ResultSet resultSet = statement.executeQuery();

		if (resultSet.next()) {
			String fullName = resultSet.getString("full_name");
			user = new Users(email, password, fullName);
		}
		resultSet.close();
		statement.close();

		return user;
	}
}
