package com.bookstore.controller.frontend.customer;

import com.bookstore.controller.BaseServlet;
import com.bookstore.service.CustomerServices;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/login")
public class CustomerLoginServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;

    public CustomerLoginServlet() {
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		CustomerServices customerServices = new CustomerServices(entityManager, request, response);
		customerServices.showLogin();
		String loginPage = "frontend/login.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(loginPage);
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		CustomerServices customerServices = new CustomerServices(entityManager, request, response);
		try {
			customerServices.doLogin();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
