package com.bookstore.controller.frontend;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bookstore.controller.BaseServlet;
import com.bookstore.dao.CategoryDAO;
import com.bookstore.entity.Category;

@WebServlet("")
public class HomeServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;

	public HomeServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		CategoryDAO categoryDAO = new CategoryDAO();
		List<Category> listCategory;
		try {
			listCategory = categoryDAO.listAllCategory();
			request.setAttribute("listCategory", listCategory);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		String homepage = "frontend/index.jsp";
		System.out.println("HomeServlet");
		RequestDispatcher dispatcher = request.getRequestDispatcher(homepage);
		dispatcher.forward(request, response);
	}

}
