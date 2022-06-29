package com.bookstore.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.bookstore.entity.Category;

public class CategoryDAO {
	private String jdbcURL = "jdbc:mysql://localhost:3306/bookstoredb";
	private String jdbcUsername = "root";
	private String jdbcPassword = "12345";
	private Connection jdbcConnection;
	
	public CategoryDAO() {
	}

	public CategoryDAO(String jdbcURL, String jdbcUsername, String jdbcPassword) {
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

	public Category getCategory(int categoryId) throws SQLException {
		Category ñategory = null;
		String sql = "SELECT * FROM category WHERE category_id = ?";
		
		connect();
		
		PreparedStatement statement = jdbcConnection.prepareStatement(sql);
		statement.setInt(1, categoryId);
		
		ResultSet resultSet = statement.executeQuery();
		
		if (resultSet.next()) {
			String name = resultSet.getString("name");
			
			ñategory = new Category(categoryId, name);
		}
		
		resultSet.close();
		statement.close();
		
		return ñategory;
	}

	public List<Category> listAllCategory() throws SQLException {
		List<Category> listCategorys = new ArrayList<>();

		String sql = "SELECT * FROM category";

		connect();

		Statement statement = jdbcConnection.createStatement();
		ResultSet resultSet = statement.executeQuery(sql);

		while (resultSet.next()) {
			int categoryId = resultSet.getInt("category_id");
			String name = resultSet.getString("name");

			Category category = new Category(categoryId, name);
			listCategorys.add(category);
		}
		resultSet.close();
		statement.close();
		disconnect();
		return listCategorys;
	}


	public boolean createCategory(Category category) throws SQLException {
		String sql = "INSERT INTO category (name) VALUES (?)";
		connect();

		PreparedStatement statement = jdbcConnection.prepareStatement(sql);
		statement.setString(1, category.getName());
		
		boolean rowInserted = statement.executeUpdate() > 0;
		statement.close();
		disconnect();
		return rowInserted;
	}


	public boolean updateCategory(Category category) throws SQLException {
		String sql = "UPDATE category SET name = ?";
		sql += " WHERE category_id = ?";
		connect();

		PreparedStatement statement = jdbcConnection.prepareStatement(sql);
		statement.setString(1, category.getName());
		statement.setInt(2, category.getCategoryId());

		boolean rowUpdated = statement.executeUpdate() > 0;
		statement.close();
		disconnect();
		return rowUpdated;
	}


	public boolean deleteCategory(int categoryId) throws SQLException {
		String sql = "DELETE FROM category where category_id = ?";
		connect();

		PreparedStatement statement = jdbcConnection.prepareStatement(sql);
		statement.setInt(1, categoryId);

		boolean rowDeleted = statement.executeUpdate() > 0;
		statement.close();
		disconnect();
		return rowDeleted;
	}

	public long count() {
		return 0;
	}
	
	public Category findByName(String name) throws SQLException {
		Category category = null;
		String sql = "SELECT * FROM category WHERE name = ?";

		connect();

		PreparedStatement statement = jdbcConnection.prepareStatement(sql);
		statement.setString(1, name);

		ResultSet resultSet = statement.executeQuery();

		if (resultSet.next()) {
			int categoryId = resultSet.getInt("category_id");

			category = new Category(categoryId, name);
		}

		resultSet.close();
		statement.close();

		return category;
	}
}
