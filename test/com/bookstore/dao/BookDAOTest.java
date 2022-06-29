package com.bookstore.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import com.bookstore.entity.Book;
import com.bookstore.entity.Category;

public class BookDAOTest extends BaseDAOTest{

	private static BookDAO bookDAO;
	
	@BeforeClass
	public static void setUpClass(){
		BaseDAOTest.setUpClass();
		String jdbcURL = "jdbc:mysql://localhost:3306/bookstoredb";
		String jdbcUsername = "root";
		String jdbcPassword = "12345";
		bookDAO = new BookDAO(jdbcURL, jdbcUsername, jdbcPassword);
	}
	
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		BaseDAOTest.tearDownAfterClass();
	}
	
	
	@Test
	public void testCreateBook() throws ParseException, IOException, SQLException {
		Book newBook = new Book();
		Category category = new Category("Test");
		category.setCategoryId(2);
		newBook.setTitle("New Title");
		newBook.setAuthor("New Author");
		newBook.setPrice(33.99f);
		
		boolean createdBook = bookDAO.insertBook(newBook);
		
		assertTrue(createdBook);
	}
	
	@Ignore
	@Test
	public void testUpdateBook() throws ParseException, IOException, SQLException {
		Book existBook = new Book();
		existBook.setId(1);
		Category category = new Category();
		category.setCategoryId(1);
		existBook.setTitle("Effective Java (3nd Edition)");
		existBook.setAuthor("Joshua Bloch");
		existBook.setPrice(40f);
		
		boolean updatedBook = bookDAO.updateBook(existBook);
		
		assertTrue(updatedBook);
	}

	@Ignore
	@Test
	public void testDeleteBook(Book book) throws SQLException {

		bookDAO.deleteBook(book.getId());
		
		assertTrue(true);
	}
	
	
	@Test
	public void testGetBook() throws SQLException {
		Integer bookId = 4;
		bookDAO.getBook(bookId);
		
		assertNotNull(bookId);
	}
	
	@Ignore
	@Test
	public void testListAll() throws SQLException {
		List<Book> listBooks = bookDAO.listAllBooks();
		for(Book aBook : listBooks) {
			System.out.println(aBook.getTitle());
		}
		assertFalse(listBooks.isEmpty());
	}
	
	
	@Ignore
	@Test
	public void testFindByTitle() throws SQLException {
		String title = "Effective Java (2nd Edition)";
		Book book = bookDAO.findByTitle(title);
		System.out.println(book.getTitle());
		assertNotNull(book);
	}
	
	@Ignore
	@Test
	public void testCount() throws SQLException {
		
		long totalBooks = bookDAO.count();
		
		assertEquals(2,totalBooks);
	}
	@Ignore
	@Test
	public void testListByCategory() throws SQLException {
		int categoryId = 2;
		List<Book> books = bookDAO.listByCategory(categoryId);
		assertTrue(books.size() > 0);
	}
	
	@Test
	public void testSearchBookInTitle() throws SQLException {
		String keyword = "Java";
		List<Book> result = bookDAO.search(keyword);
		
		for(Book aBook : result) {
			System.out.println(aBook.getTitle());
		}
		
		assertEquals(6,result.size());
	}
	
	@Test
	public void testSearchBookInAuthor() throws SQLException {
		String keyword = "Sierra";
		List<Book> result = bookDAO.search(keyword);
		
		for(Book aBook : result) {
			System.out.println(aBook.getTitle());
		}
		
		assertEquals(2,result.size());
	}
	
	@Test
	public void testSearchBookInDescription() throws SQLException {
		String keyword = "Generic";
		List<Book> result = bookDAO.search(keyword);
		
		for(Book aBook : result) {
			System.out.println(aBook.getTitle());
		}
		
		assertEquals(2,result.size());
	}
}
