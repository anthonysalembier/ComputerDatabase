package com.excilys.computerdatabase.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.excilys.computerdatabase.model.Company;
import com.excilys.computerdatabase.model.ICompany;

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
		
		ResultSet resultSet = getResults("SELECT * FROM company;");

		// Construction of objects from query result
		try {
			if (resultSet != null) {
				while (resultSet.next()) {
					companies.add(new Company(resultSet.getLong(ID),
                                              resultSet.getString(NAME)));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return companies;
	}

	/**
	 * Find a company by its ID
	 * 
	 * @param id
	 * @return an ICompany
	 */
	public ICompany getCompanyById(int id) {
		ICompany company = new Company();
		ResultSet resultSet = getResults("SELECT * FROM company WHERE id=" + id + ";");
		
		// Construction of objects from query result
		try {
			if (resultSet != null) {
				while (resultSet.next()) {
					company.setId(resultSet.getLong(ID));
					company.setName(resultSet.getString(NAME));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
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
