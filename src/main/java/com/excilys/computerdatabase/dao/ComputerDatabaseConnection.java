package com.excilys.computerdatabase.dao;

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
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			System.out.println("... Driver instancied ...");
		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// Connection creation
		try {
			connection = DriverManager.getConnection(dbURL, login, password);
			if (connection != null) {
				System.out.println("... Connection success ...");
			}
		} catch (SQLException e) {
			if (connection == null) {
				System.out.println("... Connection failed ...");
			}
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
				System.out.println("... Connection success ...");
			}
		} catch (SQLException e) {
			if (connection == null) {
				System.out.println("... Connection failed ...");
			}
			e.printStackTrace();
		}
		return connection;
	}

//	public Connection getConnection() {
//		return connection;
//	}
//
//	/**
//	 * Close the connection
//	 */
//	public void closeConnection() {
//		try {
//			connection.close();
//			System.out.println("... Connection closed ...");
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//	}

}
