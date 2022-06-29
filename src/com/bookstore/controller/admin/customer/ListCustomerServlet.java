package com.bookstore.controller.admin.customer;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bookstore.controller.BaseServlet;
import com.bookstore.service.CustomerServices;

@WebServlet("/admin/list_customer")
public class ListCustomerServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
       
    public ListCustomerServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		CustomerServices customerServices = new CustomerServices(entityManager,request, response);
		try {
			customerServices.listCustomers();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
