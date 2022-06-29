package com.bookstore.service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bookstore.dao.CustomerDAO;
import com.bookstore.entity.Customer;

public class CustomerServices {
	private HttpServletRequest request;
	private HttpServletResponse response;
	private CustomerDAO customerDAO;
	
	public CustomerServices(EntityManager entityManager, HttpServletRequest request, HttpServletResponse response) {
		super();
		this.request = request;
		this.response = response;
		this.customerDAO = new CustomerDAO();
	}
	
	public void listCustomers() throws ServletException, IOException, SQLException {
		listCustomers(null);
	}
	
	public void listCustomers(String message) throws ServletException, IOException, SQLException {
		List<Customer> listCustomer = customerDAO.listAllCustomers();
		
		if(message != null) {
			request.setAttribute("message", message);
		}
		
		request.setAttribute("listCustomer", listCustomer);
		
		String listPage = "customer_list.jsp";
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(listPage);
		requestDispatcher.forward(request, response);
	}
	
	public void createCustomer() throws ServletException, IOException, SQLException {
		String email = request.getParameter("email");
		
		Customer existCustomer = customerDAO.findCustomerByEmail(email);
		
		if(existCustomer != null) {
			String message = "Could not create customer: the email " + email + " is already registered by another customer.";
			listCustomers(message);
			
		} else {
			Customer newCustomer = new Customer();
			updateCustomerFieldsFromForm(newCustomer);
			customerDAO.createCustomer(newCustomer);
			
			String message = "New customer has been created successfully.";
			listCustomers(message);
		}
		
	}
	
	public void registerCustomer() throws ServletException, IOException, SQLException {
		String email = request.getParameter("email");
		Customer existCustomer = customerDAO.findCustomerByEmail(email);
		String message = "";
		
		if(existCustomer != null) {
			message = "Could not register. The email " + email + " is already registered by another customer.";
			
		} else {
			Customer newCustomer = new Customer();
			updateCustomerFieldsFromForm(newCustomer);
			customerDAO.createCustomer(newCustomer);
			
			message = "You have register successfully! Thank you.<br/>"
					+ "<a href='login'>Click here</a> to login";
		}

		String messagePage = "frontend/message.jsp";
		request.setAttribute("message", message);
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(messagePage);
		requestDispatcher.forward(request, response);
	}

	public void editCustomer() throws ServletException, IOException, SQLException {
		int customerId = Integer.parseInt(request.getParameter("id"));
		Customer customer = customerDAO.getCustomer(customerId);
		
		String editPage = "customer_form.jsp";
		request.setAttribute("customer", customer);
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(editPage);
		requestDispatcher.forward(request, response);
	}

	public void updateCustomer() throws ServletException, IOException, SQLException {
		int customerId = Integer.parseInt(request.getParameter("customerId"));
		String email = request.getParameter("email");
		Customer customerByEmail = customerDAO.findCustomerByEmail(email);
		String message = null;
		
		if(customerByEmail != null && customerByEmail.getCustomerId() != customerId) {
			message = "Could not update the customer ID " + customerId + " because there's an existing customer having the same email";
		} else {
			Customer customerById = customerDAO.getCustomer(customerId);
			updateCustomerFieldsFromForm(customerById);
			customerDAO.updateCustomer(customerById);
			
			message = "The customer has updated successfully";
		}
		
		listCustomers(message);
	}

	public void deleteCustomer() throws ServletException, IOException, SQLException {
		int customerId = Integer.parseInt(request.getParameter("id"));
		Customer customer = new Customer(customerId);
		customerDAO.deleteCustomer(customer);
		
		String message = "The customer has been deleted successfully.";
		listCustomers(message);
	}
	
	private void updateCustomerFieldsFromForm(Customer customer) {
		String email = request.getParameter("email");
		String fullName = request.getParameter("fullname");
		String password = request.getParameter("password");
		String address = request.getParameter("adress");
		
		customer.setEmail(email);
		customer.setFullname(fullName);
		customer.setPassword(password);
		customer.setAdress(address);
	}

	public void showLogin() throws ServletException, IOException {
		String loginPage = "frontend/login.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(loginPage);
		dispatcher.forward(request, response);
	}

	public void doLogin() throws ServletException, IOException, SQLException {
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		Customer customer = customerDAO.checkLogin(email, password);
		
		if(customer == null) {
			String message = "Login failed. Please check your email and password";
			request.setAttribute("message", message);
			showLogin();
		} else {
			request.getSession().setAttribute("loggedCustomer", customer);
			showCustomerProfile();
		}
	}
	
	public void showCustomerProfile() throws ServletException, IOException {
		String profilePage = "frontend/customer_profile.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(profilePage);
		dispatcher.forward(request, response);
	}
}
