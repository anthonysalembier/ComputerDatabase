package com.excilys.computerdatabase.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public enum ComputerDatabaseConnection {
	INSTANCE;

//	private Connection connection;

	/**
	 * Create and return a connection to the database with a login and a password.
	 */
	public Connection createConnection(String dbURL, String login, String password) {
		Connection connection = null;
		
		// Driver instanciation
		// Driver doesn't need to be instancied with JDBC4
		
		// Connection creation
		try {
			connection = DriverManager.getConnection(dbURL, login, password);
			if (connection != null) {
			}
		} catch (SQLException e) {
            System.out.println("... Connection failed ...");
			e.printStackTrace();
		}
		return connection;
	}
	
	/**
	 * Create and return a connection to the database.
	 */
	public Connection createConnection(String dbURL) {
		Connection connection = null;
		try {
			connection = DriverManager.getConnection(dbURL);
			if (connection != null) {
			}
		} catch (SQLException e) {
            System.out.println("... Connection failed ...");
			e.printStackTrace();
		}
		return connection;
	}
}
