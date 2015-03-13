package com.excilys.computerdatabase.main;

import java.sql.Connection;
import java.sql.SQLException;

import com.excilys.computerdatabase.dao.ComputerDatabaseConnection;

public class ComputerDatabase {
	
	private static final String DB_URL = "jdbc:mysql://localhost:3306";
	private static final String LOGIN = "admincdb";
	private static final String PASSWORD = "qwerty1234";
	
	public static void main(String[] args) {
		Connection connection = ComputerDatabaseConnection.INSTANCE.createConnection(DB_URL, LOGIN, PASSWORD);
		
		try {
			connection.close();
			System.out.println("... Connection closed ...");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
