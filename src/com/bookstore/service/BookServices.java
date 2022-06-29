package com.bookstore.service;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bookstore.dao.BookDAO;
import com.bookstore.dao.CategoryDAO;
import com.bookstore.entity.Book;
import com.bookstore.entity.Category;

public class BookServices {
	private HttpServletRequest request;
	private HttpServletResponse response;
	private BookDAO bookDAO;
	private CategoryDAO categoryDAO;

	public BookServices(HttpServletRequest request, HttpServletResponse response) {
		super();
		this.request = request;
		this.response = response;

		bookDAO = new BookDAO(null, null, null);
	}
	
	public void listBooks() throws ServletException, IOException, SQLException {
		listBooks(null);
	}

	public void listBooks(String message) throws ServletException, IOException, SQLException {

		List<Book> listBooks = bookDAO.listAllBooks();
		request.setAttribute("listBooks", listBooks);

		if(message != null) {
			request.setAttribute("message", message);
		}
		
		String listPage = "book_list.jsp";
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(listPage);
		requestDispatcher.forward(request, response);
	}

	public void showBookNewForm() throws ServletException, IOException, SQLException {
		List<Category> listCategory = categoryDAO.listAllCategory();
		request.setAttribute("listCategory", listCategory);

		String newPage = "book_form.jsp";
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(newPage);
		requestDispatcher.forward(request, response);
	}

	public void createBook() throws ParseException, IOException, ServletException, SQLException {
		String title = request.getParameter("title");
		Book existBook = bookDAO.findByTitle(title);

		if (existBook != null) {
			String message = "Could not create new book because the title " + title + " alredy exist.";
			listBooks(message);
			return;
		} else {
			Book newBook = new Book();
			readBookFields(newBook);

			boolean createdBook = bookDAO.insertBook(newBook);

			if (createdBook) {
				String message = "A new book has been created successfully";
				request.setAttribute("message", message);
				listBooks(message);
				return;
			}
		}
	}

	public void editBook() throws ServletException, IOException, SQLException {
		Integer bookId = Integer.parseInt(request.getParameter("id"));
		Book book = bookDAO.getBook(bookId);
		List<Category> listCategory = categoryDAO.listAllCategory();
		
		request.setAttribute("book", book);
		request.setAttribute("listCategory", listCategory);
		String editPage = "book_form.jsp";
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(editPage);
		requestDispatcher.forward(request, response);
	}

	public void deleteBook() throws ServletException, IOException, SQLException {
		Integer bookId = Integer.parseInt(request.getParameter("id"));
		bookDAO.deleteBook(bookId);
		
		String message = "The book has been deleted successfully";
		listBooks(message);
	}

	public void updateBook() throws ParseException, IOException, ServletException, SQLException {
		Integer bookId = Integer.parseInt(request.getParameter("bookId"));
		String title = request.getParameter("title");
		
		Book existBook = bookDAO.getBook(bookId);
		Book bookByTitle = bookDAO.findByTitle(title);
		
		if(!existBook.equals(bookByTitle)) {
			String message = "Could not update book because there's another book having same title.";
			listBooks(message);
			return;
		} else {
		
		readBookFields(existBook);
		bookDAO.updateBook(existBook);
		
		String message = "The book has been updated successfully";
		listBooks(message);
		return;
		}
	}
	
	public void readBookFields(Book book) throws ParseException, IOException, ServletException {
		String title = request.getParameter("title");
		String author = request.getParameter("author");
		float price = Float.parseFloat(request.getParameter("price"));

		book.setTitle(title);
		book.setAuthor(author);
		book.setPrice(price);
	}

	public void listBooksByCategory() throws ServletException, IOException, SQLException {
		int categoryId = Integer.parseInt(request.getParameter("id"));
		List<Book> listBook = bookDAO.listByCategory(categoryId);
		Category category = categoryDAO.getCategory(categoryId);
		List<Category> listCategory = categoryDAO.listAllCategory();
		
		request.setAttribute("listCategory", listCategory);
		request.setAttribute("listBook", listBook);
		request.setAttribute("category", category);
		String listPage = "frontend/book_list_by_category.jsp";
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(listPage);
		requestDispatcher.forward(request, response);
		
	}

	public void viewBookDetail() throws ServletException, IOException, SQLException {
		Integer bookId = Integer.parseInt(request.getParameter("id"));
		Book book = bookDAO.getBook(bookId);
		List<Category> listCategory = categoryDAO.listAllCategory();
		
		request.setAttribute("listCategory", listCategory);
		request.setAttribute("book", book);
		
		String detailPage = "frontend/book_detail.jsp";
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(detailPage);
		requestDispatcher.forward(request, response);
	}

	public void search() throws ServletException, IOException, SQLException {
		String keyword = request.getParameter("keyword");
		List<Book> result = null;
		
		if(keyword.contentEquals("")) {
			result = bookDAO.listAllBooks();
		} else {
			result = bookDAO.search(keyword);
		}
		request.setAttribute("result", result);
		request.setAttribute("keyword", keyword);
		
		String listPage = "frontend/search_result.jsp";
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(listPage);
		requestDispatcher.forward(request, response);
		
	}
}
