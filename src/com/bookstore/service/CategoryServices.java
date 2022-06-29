package com.bookstore.service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bookstore.dao.CategoryDAO;
import com.bookstore.entity.Category;



public class CategoryServices {
	private CategoryDAO categoryDAO;
	private HttpServletRequest request;
	private HttpServletResponse response;
	
	public CategoryServices(EntityManager entityManager,HttpServletRequest request, HttpServletResponse response) {
		this.request = request;
		this.response = response;
		categoryDAO = new CategoryDAO();
	}
	
	public void listCategory(String message) throws ServletException, IOException, SQLException {
		List<Category> listCategory = categoryDAO.listAllCategory();
		request.setAttribute("listCategory", listCategory);
		if(message != null) {
			request.setAttribute("message", message);
		}
		String listPage = "category_list.jsp";
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(listPage);
		requestDispatcher.forward(request, response);
	}
	
	public void listCategory() throws ServletException, IOException, SQLException {
		listCategory(null);
	}
	
	public void createCategory() throws ServletException, IOException, SQLException {
		String name = request.getParameter("name");
		Category existCategory = categoryDAO.findByName(name);
		
		if(existCategory != null) {
			String message = "Could not create category. A category with name "
								+ name + " already exists."; 
			request.setAttribute("message", message);
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("message.jsp");
			dispatcher.forward(request, response);
		} else {
			Category newCategory = new Category(name);
			categoryDAO.createCategory(newCategory);
			String message = "New category created successfully.";
			listCategory(message);
		}
	}
	
	public void editCategory() throws ServletException, IOException, SQLException {
		int categoryId = Integer.parseInt(request.getParameter("id"));
		Category category = categoryDAO.getCategory(categoryId);
		request.setAttribute("category", category);
		
		String editPage = "category_form.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(editPage);
		dispatcher.forward(request, response);
	}

	public void updateCategory() throws ServletException, IOException, SQLException {
		int categoryId = Integer.parseInt(request.getParameter("categoryId"));
		String categoryName = request.getParameter("name");
		
		Category categoryById = categoryDAO.getCategory(categoryId);
		Category categoryByName = categoryDAO.findByName(categoryName);
		
		if(categoryByName != null && categoryById.getCategoryId() != categoryByName.getCategoryId()) {
			String message = "Could not update category. A category with name " + categoryName + " alredy exist.";
			
			request.setAttribute("message", message);
			RequestDispatcher dispatcher = request.getRequestDispatcher("message.jsp");
			dispatcher.forward(request, response);
		} else {
			categoryById.setName(categoryName);
			categoryDAO.updateCategory(categoryById);
			String message = "Category has been updated successfully";
			listCategory(message);
		}
	}

	public void deleteCategory() throws ServletException, IOException, SQLException {
		int categoryId = Integer.parseInt(request.getParameter("id"));
		categoryDAO.deleteCategory(categoryId);
		
		String message = "The category " + categoryId + " has been removed syccessfully.";
		listCategory(message);
		
	}
}
