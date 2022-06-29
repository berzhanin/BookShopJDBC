package com.bookstore.entity;

public class OrderDetail implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private OrderDetailId id;
	private Book book;
	private BookOrder bookOrder;

	public OrderDetail() {
	}

	public OrderDetail(OrderDetailId id) {
		this.id = id;
	}

	public OrderDetail(OrderDetailId id, Book book, BookOrder bookOrder) {
		this.id = id;
		this.book = book;
		this.bookOrder = bookOrder;
	}

	public OrderDetailId getId() {
		return this.id;
	}

	public void setId(OrderDetailId id) {
		this.id = id;
	}

	public Book getBook() {
		return this.book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public BookOrder getBookOrder() {
		return this.bookOrder;
	}

	public void setBookOrder(BookOrder bookOrder) {
		this.bookOrder = bookOrder;
	}
}
