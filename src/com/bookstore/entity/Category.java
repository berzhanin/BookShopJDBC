package com.bookstore.entity;

import java.util.HashSet;
import java.util.Set;

public class Category implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private int categoryId;
	private String name;
	private Set<Book> books = new HashSet<Book>(0);

	public Category() {
	}

	public Category(String name) {
		this.name = name;
	}
	
	public Category(int categoryId, String name) {
		this.categoryId = categoryId;
		this.name = name;
	}

	public Category(int categoryId, String name, Set<Book> books) {
		this.categoryId = categoryId;
		this.name = name;
		this.books = books;
	}
	
	public int getCategoryId() {
		return this.categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<Book> getBooks() {
		return this.books;
	}

	public void setBooks(Set<Book> books) {
		this.books = books;
	}

	public void setCategoryName(String name) {
		this.name = name;
	}
}
