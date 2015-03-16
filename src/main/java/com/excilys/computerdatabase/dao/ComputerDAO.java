package com.excilys.computerdatabase.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.excilys.computerdatabase.model.Company;
import com.excilys.computerdatabase.model.Computer;
import com.excilys.computerdatabase.model.ICompany;
import com.excilys.computerdatabase.model.IComputer;
import com.excilys.computerdatabase.util.ComputerDatabaseConnection;

public enum ComputerDAO {
	INSTANCE;

	/*
	 * The columns names of the company table
	 */
	private final String ID = "id";
	private final String NAME = "name";
	private final String INTRODUCED = "introduced";
	private final String DISCONTINUED = "discontinued";
	private final String COMPANY_ID = "company_id";

	private Connection connection;
	private String dbUrl = "";
	private String login = "";
	private String password = "";

	/**
	 * Get a list of all computers
	 * 
	 * @return an ArrayList of IComputer
	 */
	public List<IComputer> getAllComputers() {
		List<IComputer> computers = new ArrayList<>();
		String query = "SELECT * FROM computer;";

		setConnection();

		try (final Statement s = connection.createStatement()) {
			System.out.println("... Getting all computers ...");

			try (final ResultSet rs = s.executeQuery(query)) {
				if (rs != null) {
					while (rs.next()) {
						computers.add(new Computer(rs.getLong(ID), rs
								.getString(NAME), rs.getTimestamp(INTRODUCED),
								rs.getTimestamp(DISCONTINUED), rs
										.getLong(COMPANY_ID)));
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		closeConnection();

		return computers;
	}

	/**
	 * Find a computer by its ID
	 * 
	 * @param id
	 * @return an IComputer
	 */
	public IComputer getComputerById(long id) {
		IComputer computer = new Computer();
		String query = "SELECT * FROM computer WHERE id = ?;";

		setConnection();

		try (final PreparedStatement ps = connection.prepareStatement(query)) {
			ps.setLong(1, id);

			try (final ResultSet rs = ps.executeQuery()) {
				if (rs != null) {
					while (rs.next()) {
						computer.setId(rs.getLong(ID));
						computer.setName(rs.getString(NAME));
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		closeConnection();

		return computer;
	}

	/**
	 * Delete a computer
	 * 
	 * @param id
	 */
	public void deleteComputer(long id) {
		String query = "DELETE FROM computer WHERE id = ?;";

		setConnection();

		try (final PreparedStatement ps = connection.prepareStatement(query)) {
			ps.setLong(1, id);
			int deletedRows = ps.executeUpdate();
			System.out.println(deletedRows + " row(s) has been deleted.");
		} catch (SQLException e) {
			e.printStackTrace();
		}

		closeConnection();
	}

	/**
	 * Add a computer
	 * 
	 * @param computer
	 */
	public void addComputer(IComputer computer) {
		String query = "INSERT INTO computer VALUES(?, ?, ?, ?, ?);";

		setConnection();

		try (final PreparedStatement ps = connection.prepareStatement(query)) {
			ps.setLong(1, computer.getId());
			ps.setString(2, computer.getName());
			ps.setTimestamp(3, computer.getIntroducedDate());
			ps.setTimestamp(4, computer.getDiscontinuedDate());
			ps.setLong(5, computer.getCompanyId());

			int addedRows = ps.executeUpdate();
			System.out.println(addedRows + " row(s) has been added.");
		} catch (SQLException e) {
			e.printStackTrace();
		}

		closeConnection();
	}

	/**
	 * Update informations of a computer
	 * 
	 * @param computer
	 */
	public void updateComputer(long id, IComputer computer) {
		String query = "UPDATE computer SET id = ?, name = ?, introduced = ?, discontinued = ?, company_id = ? WHERE id = ?;";

		setConnection();

		try (final PreparedStatement ps = connection.prepareStatement(query)) {
			ps.setLong(1, computer.getId());
			ps.setString(2, computer.getName());
			ps.setTimestamp(3, computer.getIntroducedDate());
			ps.setTimestamp(4, computer.getDiscontinuedDate());
			ps.setLong(5, computer.getCompanyId());
			ps.setLong(6, computer.getCompanyId());

			int updatedRows = ps.executeUpdate();
			System.out.println(updatedRows + " row(s) has been updated.");
		} catch (SQLException e) {
			e.printStackTrace();
		}

		closeConnection();
	}

	/**
	 * Set the connection to the database.
	 */
	private void setConnection() {
		if (dbUrl.equals("") || login.equals("") || password.equals("")) {
			throw new IllegalStateException(
					"Url, login and password must be setted");
		}
		connection = ComputerDatabaseConnection.INSTANCE.createConnection(
				dbUrl, login, password);
	}

	/**
	 * Close the connection to the database.
	 */
	private void closeConnection() {
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Set the database URL.
	 * This must be done before trying to connect to the database.
	 * @param dbUrl
	 */
	public void setDbUrl(String dbUrl) {
		this.dbUrl = dbUrl;
	}

	/**
	 * Set the login to connect to the database.
	 * This must be done before trying to connect to the database.
	 * @param login
	 */
	public void setLogin(String login) {
		this.login = login;
	}

	/**
	 * Set the password to connect to the database.
	 * This must be done before trying to connect to the database.
	 * @param password
	 */
	public void setPassword(String password) {
		this.password = password;
	}
}
