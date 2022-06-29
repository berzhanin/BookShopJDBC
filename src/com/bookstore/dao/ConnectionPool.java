package com.bookstore.dao;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class ConnectionPool { // singletone

	private ConnectionPool() { // private constructor

	}

	private static ConnectionPool instance = null;

	public static ConnectionPool getInstance() { // static method return one object from class
		if (instance == null)
			instance = new ConnectionPool();
		return instance;
	}

	// receive a connection with DB, but not directly, through a connection pool
	public Connection getConnection() {
		Context ctx;
		Connection c = null;
		try {
			ctx = new InitialContext(); // allow us to have access to name and directory services - JNDI
			DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/mydatabase"); // mydatabase - pool name
			c = ds.getConnection();
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return c;
	}
}
