package com.bookstore.controller.frontend.customer;

import com.bookstore.controller.BaseServlet;
import com.bookstore.service.CustomerServices;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/register_customer")
public class RegisterCustomerServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;

    public RegisterCustomerServlet() {
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		CustomerServices customerServices = new CustomerServices(entityManager, request, response);
		try {
			customerServices.registerCustomer();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
