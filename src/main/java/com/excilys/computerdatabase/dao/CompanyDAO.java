package com.excilys.computerdatabase.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.excilys.computerdatabase.model.Company;
import com.excilys.computerdatabase.model.ICompany;
import com.excilys.computerdatabase.util.ComputerDatabaseConnection;

public enum CompanyDAO {
	INSTANCE;

	// The columns names of the company table
	private final String ID = "id";
	private final String NAME = "name";

	private Connection connection;
	private String dbUrl = "";
	private String login = "";
	private String password = "";

	/**
	 * Get a list of all companies
	 * 
	 * @return an ArrayList of ICompany
	 */
	public List<ICompany> getAllCompanies() {
		List<ICompany> companies = new ArrayList<>();
		String query = "SELECT * FROM company;";

		setConnection();
		
		try (final Statement s = connection.createStatement()){
			System.out.println("... Getting all companies ...");
			
			try (final ResultSet rs = s.executeQuery(query)) {
				if (rs != null) {
					while (rs.next()) {
						companies.add(new Company(rs.getLong(ID),
	                                              rs.getString(NAME)));
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		closeConnection();
		
		return companies;
	}

	/**
	 * Find a company by its ID
	 * 
	 * @param id
	 * @return an ICompany
	 */
	public ICompany getCompanyById(long id) {
		ICompany company = new Company();
		String query = "SELECT * FROM company WHERE id = ?;";
		
		setConnection();
		
		try (final PreparedStatement ps = connection.prepareStatement(query)) {
			ps.setLong(1, id);
			
			try (final ResultSet rs = ps.executeQuery()) {
				if (rs != null) {
					while (rs.next()) {
						company.setId(rs.getLong(ID));
						company.setName(rs.getString(NAME));
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		closeConnection();
		
		return company;
	}

	/**
	 * Create a connection to a database with a login and a password
	 */
	private void setConnection() {
		if (dbUrl.equals("") || login.equals("") || password.equals("")) {
			throw new IllegalStateException(
					"Url, login and password must be setted");
		}
		connection = ComputerDatabaseConnection.INSTANCE.createConnection(
				dbUrl, login, password);
	}
	
	private void closeConnection() {
    	try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
    }

	public void setDbUrl(String dbUrl) {
		this.dbUrl = dbUrl;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * Connect to the given database, send the query and return the result
	 * @param query
	 * @return a ResultSet
	 */
	private ResultSet getResults(String query) {
		ResultSet resultSet = null;

		// Connection and query sending
		try {
			setConnection();

			Statement statement = connection.createStatement();

			resultSet = statement.executeQuery(query);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return resultSet;
	}
}
