package com.bookstore.entity;

public class Book implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private int id;
	private int category;
	private String title;
	private String author;
	private float price;

	public Book() {
	}
	
	public Book(int bookId,  String title, String author, float price) {
		this.id = bookId;
		this.title = title;
		this.author = author;
		this.price = price;
	}

	public Book(int bookId, int categoryId, String title, String author, float price) {
		this.id = bookId;
		this.category = categoryId;
		this.title = title;
		this.author = author;
		this.price = price;
	}

		public int getId() {
		return this.id;
	}

	public void setId(int bookId) {
		this.id = bookId;
	}

	public int getCategory() {
		return this.category;
	}

	public void setCategory(int category) {
		this.category = category;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return this.author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public float getPrice() {
		return this.price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Book other = (Book) obj;
		if (id != other.id)
			return false;
		return true;
	}
}
