package com.bookstore.dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.bookstore.entity.Book;

// This DAO class provides CRUD database operations for the table book in the database.

public class BookDAO {
	private String jdbcURL = "jdbc:mysql://localhost:3306/bookstoredb";
	private String jdbcUsername = "root";
	private String jdbcPassword = "12345";
	private Connection jdbcConnection;
	
	public BookDAO(String jdbcURL, String jdbcUsername, String jdbcPassword) {
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
			jdbcConnection = DriverManager.getConnection(
										jdbcURL, jdbcUsername, jdbcPassword);
		}
	}
	
	protected void disconnect() throws SQLException {
		if (jdbcConnection != null && !jdbcConnection.isClosed()) {
			jdbcConnection.close();
		}
	}
	
	public boolean insertBook(Book book) throws SQLException {
		String sql = "INSERT INTO book (title, author, price) VALUES (?, ?, ?)";
		connect();
		
		PreparedStatement statement = jdbcConnection.prepareStatement(sql);
		statement.setString(1, book.getTitle());
		statement.setString(2, book.getAuthor());
		statement.setFloat(3, book.getPrice());
		
		boolean rowInserted = statement.executeUpdate() > 0;
		statement.close();
		disconnect();
		return rowInserted;
	}
	
	public List<Book> listAllBooks() throws SQLException {
		List<Book> listBook = new ArrayList<>();
		
		String sql = "SELECT * FROM book";
		
		connect();
		
		Statement statement = jdbcConnection.createStatement();
		ResultSet resultSet = statement.executeQuery(sql);
		
		while (resultSet.next()) {
			int id = resultSet.getInt("book_id");
			String title = resultSet.getString("title");
			String author = resultSet.getString("author");
			float price = resultSet.getFloat("price");
			
			Book book = new Book(id, title, author, price);
			listBook.add(book);
		}
		
		resultSet.close();
		statement.close();
		
		disconnect();
		
		return listBook;
	}
	
	public boolean deleteBook(Integer bookId) throws SQLException {
		String sql = "DELETE FROM book where book_id = ?";
		
		connect();
		
		Book book = new Book();
		
		PreparedStatement statement = jdbcConnection.prepareStatement(sql);
		statement.setInt(1, book.getId());
		
		boolean rowDeleted = statement.executeUpdate() > 0;
		statement.close();
		disconnect();
		return rowDeleted;		
	}
	
	public boolean updateBook(Book book) throws SQLException {
		String sql = "UPDATE book SET title = ?, author = ?, price = ? WHERE book_id = ?";
		connect();
		
		PreparedStatement statement = jdbcConnection.prepareStatement(sql);
		statement.setString(1, book.getTitle());
		statement.setString(2, book.getAuthor());
		statement.setFloat(3, book.getPrice());
		statement.setInt(4, book.getId());
		
		boolean rowUpdated = statement.executeUpdate() > 0;
		statement.close();
		disconnect();
		return rowUpdated;		
	}
	
	public Book getBook(int id) throws SQLException {
		Book book = null;
		String sql = "SELECT * FROM book WHERE book_id = ?";
		
		connect();
		
		PreparedStatement statement = jdbcConnection.prepareStatement(sql);
		statement.setInt(1, id);
		
		ResultSet resultSet = statement.executeQuery();
		
		if (resultSet.next()) {
			String title = resultSet.getString("title");
			String author = resultSet.getString("author");
			float price = resultSet.getFloat("price");
			
			book = new Book(id, title, author, price);
		}
		
		resultSet.close();
		statement.close();
		
		return book;
	}

	public Book findByTitle(String title) throws SQLException {
		Book book = null;
		String sql = "SELECT * FROM book WHERE title = ?";
		
		connect();
		
		PreparedStatement statement = jdbcConnection.prepareStatement(sql);
		statement.setString(1, title);
		
		ResultSet resultSet = statement.executeQuery();
		List<Book> result = new ArrayList<>(); 
		
		if (resultSet.next()) {
			int bookId = resultSet.getInt("book_id");
			String author = resultSet.getString("author");
			float price = resultSet.getFloat("price");
			book = new Book(bookId, title, author, price);
			result.add(book);
		}
		
		if(!result.isEmpty()) {
			return result.get(0);
		}
		return null;
	}

	public List<Book> listByCategory(int categoryId) throws SQLException {
		Book book = null;
		String sql = "SELECT * FROM book JOIN category "
				   + "ON category.category_id = ? AND book.category_id = ?";
		
		connect();
		
		PreparedStatement statement = jdbcConnection.prepareStatement(sql);
		statement.setInt(1, categoryId);
		statement.setInt(2, categoryId);
		
		ResultSet resultSet = statement.executeQuery();
		List<Book> result = new ArrayList<>(); 
		
		if (resultSet.next()) {
			int bookId = resultSet.getInt("book_id");
			String title = resultSet.getString("title");
			String author = resultSet.getString("author");
			float price = resultSet.getFloat("price");
			book = new Book(bookId,categoryId, title, author, price);
			result.add(book);
		}
		return result;
	}

	public List<Book> search(String keyword) throws SQLException {
		Book book = null;
		String sql = "SELECT * FROM book WHERE title LIKE '?' OR author LIKE '?'";
		
		connect();
		
		PreparedStatement statement = jdbcConnection.prepareStatement(sql);
		statement.setString(1, keyword);

		ResultSet resultSet = statement.executeQuery();
		List<Book> result = new ArrayList<>(); 
		
		if (resultSet.next()) {
			int bookId = resultSet.getInt("book_id");
			String title = resultSet.getString("title");
			String author = resultSet.getString("author");
			float price = resultSet.getFloat("price");
			book = new Book(bookId, title, author, price);
			result.add(book);
		}
		return result;
	}

	public long count() throws SQLException {
		String sql = "SELECT COUNT(*) FROM book";
		
		connect();
		
		PreparedStatement statement = jdbcConnection.prepareStatement(sql);
		
		long rowCount = statement.executeUpdate();
		statement.close();
		disconnect();
		return rowCount;
	}
}
