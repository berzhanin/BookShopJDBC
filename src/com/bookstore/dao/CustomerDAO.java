package com.bookstore.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.bookstore.entity.Customer;

public class CustomerDAO {

	private String jdbcURL = "jdbc:mysql://localhost:3306/bookstoredb";
	private String jdbcUsername = "root";
	private String jdbcPassword = "12345";
	private Connection jdbcConnection;

	public CustomerDAO() {
	}

	public CustomerDAO(String jdbcURL, String jdbcUsername, String jdbcPassword) {
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

	public List<Customer> listAllCustomers() throws SQLException {
		List<Customer> listCustomers = new ArrayList<>();

		String sql = "SELECT * FROM customer";

		connect();

		Statement statement = jdbcConnection.createStatement();
		ResultSet resultSet = statement.executeQuery(sql);

		while (resultSet.next()) {
			int customerId = resultSet.getInt("customer_id");
			String email = resultSet.getString("email");
			String password = resultSet.getString("password");
			String fullName = resultSet.getString("fullname");

			Customer customer = new Customer(customerId, email, password, fullName);
			listCustomers.add(customer);
		}

		resultSet.close();
		statement.close();

		disconnect();

		return listCustomers;
	}

	public boolean createCustomer(Customer customer) throws SQLException {
		String sql = "INSERT INTO customer (email, password,fullname) VALUES (?, ?, ?)";
		connect();

		PreparedStatement statement = jdbcConnection.prepareStatement(sql);
		statement.setString(1, customer.getEmail());
		statement.setString(2, customer.getPassword());
		statement.setString(3, customer.getFullname());

		boolean rowInserted = statement.executeUpdate() > 0;
		statement.close();
		disconnect();
		return rowInserted;
	}

	public Customer getCustomer(int customerId) throws SQLException {
		Customer customer = null;
		String sql = "SELECT * FROM customer WHERE customer_id = ?";

		connect();

		PreparedStatement statement = jdbcConnection.prepareStatement(sql);
		statement.setInt(1, customerId);

		ResultSet resultSet = statement.executeQuery();

		if (resultSet.next()) {
			String email = resultSet.getString("email");
			String password = resultSet.getString("password");
			String fullName = resultSet.getString("fullname");

			customer = new Customer(customerId, email, password, fullName);
		}

		resultSet.close();
		statement.close();

		return customer;
	}

	public boolean updateCustomer(Customer customer) throws SQLException {
		String sql = "UPDATE customer SET email = ?, password = ?, fullname = ?";
		sql += " WHERE customer_id = ?";
		connect();

		PreparedStatement statement = jdbcConnection.prepareStatement(sql);
		statement.setString(1, customer.getEmail());
		statement.setString(2, customer.getPassword());
		statement.setString(3, customer.getFullname());
		statement.setInt(4, customer.getCustomerId());

		boolean rowUpdated = statement.executeUpdate() > 0;
		statement.close();
		disconnect();
		return rowUpdated;
	}

	public boolean deleteCustomer(Customer customer) throws SQLException {
		String sql = "DELETE FROM customer where customer_id = ?";

		connect();

		PreparedStatement statement = jdbcConnection.prepareStatement(sql);
		statement.setInt(1, customer.getCustomerId());

		boolean rowDeleted = statement.executeUpdate() > 0;
		statement.close();
		disconnect();
		return rowDeleted;
	}

	public Customer findCustomerByEmail(String email) throws SQLException {
		Customer customer = null;
		String sql = "SELECT * FROM customer WHERE email = ?";

		connect();

		PreparedStatement statement = jdbcConnection.prepareStatement(sql);
		statement.setString(1, email);

		ResultSet resultSet = statement.executeQuery();

		if (resultSet.next()) {
			email = resultSet.getString("email");
			String password = resultSet.getString("password");
			String fullName = resultSet.getString("fullname");

			customer = new Customer(email, password, fullName);
		}

		resultSet.close();
		statement.close();

		return customer;
	}
	
	public Customer checkLogin(String email, String password) throws SQLException {
		Customer customer = null;
		String sql = "SELECT * FROM customer WHERE email = ? AND password = ?";

		connect();

		PreparedStatement statement = jdbcConnection.prepareStatement(sql);
		statement.setString(1, email);
		statement.setString(2, password);

		ResultSet resultSet = statement.executeQuery();

		if (resultSet.next()) {
			String fullName = resultSet.getString("fullname");
			customer = new Customer(email, password, fullName);
		}
		resultSet.close();
		statement.close();

		return customer;
	}
	

	public int countCustomer() {
		// TODO Auto-generated method stub
		return 0;
	}
}
