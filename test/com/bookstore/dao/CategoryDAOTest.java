package com.bookstore.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.sql.SQLException;
import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import com.bookstore.entity.Category;

public class CategoryDAOTest extends BaseDAOTest {

	private static CategoryDAO categoryDAO;

	@BeforeClass
	public static void setUpClass() {
		BaseDAOTest.setUpClass();
		categoryDAO = new CategoryDAO();
	}

	@Ignore
	@Test
	public void testCreateCategory() throws SQLException {
		Category newCat = new Category(8,"Paper");
		boolean category = categoryDAO.createCategory(newCat);
		assertNull(category);
	}
	@Ignore
	@Test
	public void testUpdateCategory() throws SQLException {
		Integer catId = 80;
		Category cat = categoryDAO.getCategory(catId);
		cat.setCategoryName("News");
		boolean category = categoryDAO.updateCategory(cat);
		assertNull(category);
	}
	
	
	@Test
	public void testGet() throws SQLException {
		Integer catId = 1;
		Category cat = categoryDAO.getCategory(catId);
		assertNotNull(cat);
	}


	@Test
	public void testDeleteCategory() throws SQLException {
		Integer catId = 6;
		categoryDAO.deleteCategory(catId);
		Category cat = categoryDAO.getCategory(catId);
		assertNull(cat);
	}
	
	@Test
	public void testListAll() throws SQLException {
		List<Category> listCategory = categoryDAO.listAllCategory();
		listCategory.forEach(c -> System.out.println(c.getName() + " "));
		assertTrue(listCategory.size() > 0);
	}
	
	@Ignore
	@Test
	public void testCount() {
		long totalCategories = categoryDAO.count();
		assertEquals(5, totalCategories);
	}
	
	@Test
	public void testFindByName() throws SQLException {
		String name = "Paper";
		Category category = categoryDAO.findByName(name);
		assertNotNull(category);
	}
	
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		BaseDAOTest.tearDownAfterClass();
	}
}
