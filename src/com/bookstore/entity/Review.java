package com.bookstore.entity;

import java.util.Date;

public class Review implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private int rewievId;
	private Book book;
	private Customer customer;
	private int rating;
	private String headline;
	private String comment;
	private Date reviewTime;

	public Review() {
	}

	public Review(int rewievId, Book book, Customer customer, int rating, String headline, String comment,
			Date reviewTime) {
		this.rewievId = rewievId;
		this.book = book;
		this.customer = customer;
		this.rating = rating;
		this.headline = headline;
		this.comment = comment;
		this.reviewTime = reviewTime;
	}

	public int getRewievId() {
		return this.rewievId;
	}

	public void setRewievId(int rewievId) {
		this.rewievId = rewievId;
	}

	public Book getBook() {
		return this.book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public Customer getCustomer() {
		return this.customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public int getRating() {
		return this.rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public String getHeadline() {
		return this.headline;
	}

	public void setHeadline(String headline) {
		this.headline = headline;
	}

	public String getComment() {
		return this.comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Date getReviewTime() {
		return this.reviewTime;
	}

	public void setReviewTime(Date reviewTime) {
		this.reviewTime = reviewTime;
	}
}
