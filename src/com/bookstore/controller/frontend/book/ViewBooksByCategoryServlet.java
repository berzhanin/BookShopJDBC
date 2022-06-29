package com.bookstore.controller.frontend.book;

import com.bookstore.controller.BaseServlet;
import com.bookstore.service.BookServices;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/view_category")
public class ViewBooksByCategoryServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;

    public ViewBooksByCategoryServlet() {
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BookServices bookServices = new BookServices(request, response);
		try {
			bookServices.listBooksByCategory();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
