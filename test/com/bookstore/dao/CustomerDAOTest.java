package com.bookstore.dao;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import com.bookstore.entity.Customer;

public class CustomerDAOTest extends BaseDAOTest {
	
	private static CustomerDAO customerDAO;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception{
		CustomerDAOTest.setUpClass();
		customerDAO = new CustomerDAO();
	}
	
	@AfterClass
	public static void tearDownAfterClass() throws Exception{
		CustomerDAOTest.tearDownAfterClass();
	}
	

	//@Ignore
	@Test
	public void testCreateCustomer() throws SQLException {
		Customer customer = new Customer();
		customer.setEmail("jim.bean@gmail.com");
		customer.setFullname("Jim Bean");
		customer.setPassword("secret");
		
		boolean savedCustomer =  customerDAO.createCustomer(customer);
		
		assertNotNull(savedCustomer);
	}
	
	@Ignore
	@Test
	public void testGet() throws SQLException {
		Integer customerId = 6;
		Customer customer = customerDAO.getCustomer(customerId);
		
		assertNotNull(customer);
	}
	
	@Ignore
	@Test
	public void testUpdateCustomer() throws SQLException {
		Integer customerId = 6;
		Customer customer = customerDAO.getCustomer(customerId);
		String fullName = "Tom Tom Tom";
		customer.setFullname(fullName);
		boolean updatedCustomer = customerDAO.updateCustomer(customer);
		
		assertNotNull(updatedCustomer);
	}
	
	@Ignore
	@Test
	public void testDeleteCustomer() throws SQLException {
		Integer customerId = 6;
		Customer customer = new Customer(customerId);
		customerDAO.deleteCustomer(customer);
		
		assertNull(customer);
	}
	
	
	@Test
	public void testListAll() throws SQLException {
		List<Customer> listCustomers = customerDAO.listAllCustomers();  

		assertFalse(listCustomers.isEmpty());
	}
	
	@Ignore
	@Test
	public void testCountAll() {
		int totalCustomers = (int) customerDAO.countCustomer();  

		assertEquals(4, totalCustomers);
	}
	
	@Test
	public void testfindByEmail() throws SQLException {
		String email = "sam@gmail.com";
		Customer customer = customerDAO.findCustomerByEmail(email);  

		assertNotNull(customer);
	}
	
	@Test
	public void checkLogin() throws SQLException {
		String email = "epam@web.com";
		String password = "123456";
		
		Customer customer = customerDAO.checkLogin(email, password);
		
		assertNotNull(customer);
	}
}
