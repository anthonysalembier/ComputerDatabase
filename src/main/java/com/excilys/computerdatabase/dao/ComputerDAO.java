package com.excilys.computerdatabase.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.excilys.computerdatabase.model.Computer;
import com.excilys.computerdatabase.model.IComputer;

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
	 * @return an ArrayList of IComputer
	 */
	public List<IComputer> getAllComputers() {
        List<IComputer> computers = new ArrayList<>();

        ResultSet resultSet = getResults("SELECT * FROM computer;");

        if (resultSet != null) {
            try {
                while (resultSet.next()) {
                    computers.add((new Computer(resultSet.getLong(ID),
                                                resultSet.getString(NAME),
                                                resultSet.getTimestamp(INTRODUCED),
                                                resultSet.getTimestamp(DISCONTINUED),
                                                resultSet.getLong(COMPANY_ID))));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return computers;
	}
	
	/**
	 * Find a computer by its ID
	 * @param id
	 * @return an IComputer
	 */
	public IComputer getComputerById(int id) {
		IComputer computer = new Computer();

        ResultSet resultSet = getResults("SELECT * FROM computer WHERE id=" + id +";");

        if (resultSet != null) {
            try {
                computer.setId(resultSet.getLong(ID));
                computer.setName(resultSet.getString(NAME));
                computer.setIntroducedDate(resultSet.getTimestamp(INTRODUCED));
                computer.setDiscontinuedDate(resultSet.getTimestamp(DISCONTINUED));
                computer.setCompanyId(resultSet.getLong(COMPANY_ID));
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return computer;
	}
	
	/**
	 * Delete a computer
	 * @param id
	 */
	public void deleteComputer(int id) {
		ResultSet resultSet = getResults("DELETE FROM computer WHERE id=" + id + ";");
	}
	
	
	/**
	 * Add a computer
	 * @param computer
	 */
	public void addComputer(IComputer computer) {
        String values;
        values = computer.getId() + ", ";
        values += "'" + computer.getName() + "', ";
        values += computer.getIntroducedDate() + ", ";
        values += computer.getDiscontinuedDate() + ", ";
        values += computer.getCompanyId();

		ResultSet resultSet = getResults("INSERT INTO computer VALUES(" + values + ");");
	}
	
	
	/**
	 * Update informations of a computer
	 * @param computer
	 */
	public void updateComputer(long id, IComputer computer) {
        String set;
        set = ID + "=" + computer.getId() + ", ";
        set += NAME + "='" + computer.getName() + "', ";
        set += INTRODUCED + "=" + computer.getIntroducedDate() + ", ";
        set += DISCONTINUED + "=" + computer.getDiscontinuedDate() + ", ";
        set += COMPANY_ID + "=" + computer.getCompanyId();

        ResultSet resultSet = getResults("UPDATE computer SET " + set + "WHERE " + ID + "=" + id + ";");
	}

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
