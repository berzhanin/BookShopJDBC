package com.bookstore.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.sql.SQLException;
import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.bookstore.entity.Users;

public class UserDAOTest extends BaseDAOTest{

	private static UsersDAO userDAO;

	@BeforeClass
	public static void setUpClass() {
		BaseDAOTest.setUpClass();
		userDAO = new UsersDAO();
	}

	@Test
	public void testCreateUser() throws SQLException {
		Users user = new Users();
		user.setEmail("test2@gmail.com");
		user.setFullName("Test Test");
		user.setPassword("tst");
		userDAO.createUser(user);
		assertNotNull(user.getEmail());
	}

	@Test
	public void testUpdateUser() throws SQLException {
		Users user = new Users();
		user.setUserId(12);
		user.setEmail("test.email@apache.net");
		user.setFullName("Test person");
		user.setPassword("mysecret");
		userDAO.updateUser(user);
		String expected = "mysecret";
		String actual = user.getPassword();
		assertEquals(expected, actual);
	}

	@Test
	public void testGetUsers() throws SQLException {
		Integer userId = 58;
		Users user = userDAO.getUser(userId);
		if (user != null) {
			System.out.println(user.getEmail());
		}
		assertNotNull(user);
	}

	@Test
	public void testDeleteUsers() throws SQLException {
		Integer userId = 70;
		Users user = new Users(userId);
		userDAO.deleteUser(user);
		assertNull(user.getPassword());
	}

	@Test
	public void testListAll() throws SQLException {
		List<Users> listUsers = userDAO.listAllUsers();
		for (Users user : listUsers) {
			System.out.println(user.getEmail());
		}
		assertTrue(listUsers.size() > 0);
	}

	@Test
	public void testFindUserByEmail() throws SQLException {
		String email = "james.cameron@web.com";
		Users user = userDAO.findUserByEmail(email);
		System.out.println("FindByEmal:" + user.getEmail());
		assertNotNull(user);
	}
	
	@Test
	public void testCheckLogin() throws SQLException {
		String email = "james.cameron@web.com";
		String password = "secret";
		Users user = userDAO.checkLogin(email, password);
		assertNotNull(user);
	}
	
	@AfterClass
	public static void tearDownClass() throws Exception {
		BaseDAOTest.tearDownAfterClass();
	}

}
