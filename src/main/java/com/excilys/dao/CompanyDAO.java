package com.excilys.dao;

import java.io.IOException;
import java.io.InputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.excilys.exception.DAOException;
import com.excilys.exception.PersistenceException;
import com.excilys.mapper.CompanyMapper;
import com.excilys.model.Company;
import com.excilys.persistence.ComputerDatabaseConnection;

public enum CompanyDAO implements DAO<Company, Long> {
	INSTANCE;
	
	private Properties properties;
	
	private String company;
	private String id;
	private String name;
	
	private CompanyDAO() {
		if (properties == null) {
			properties = new Properties();
			
			try (InputStream input = CompanyDAO.class.getClassLoader().getResourceAsStream("tableConfig.properties")) {
				properties.load(input);
				company = properties.getProperty("company");
				id = properties.getProperty("companyId");
				name = properties.getProperty("companyName");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public List<Company> getAll() throws DAOException {
		final List<Company> companies = new ArrayList<>();
		final CompanyMapper companyMapper = new CompanyMapper();

		String sql = "SELECT * FROM " + company;
		try (final PreparedStatement pStatement = ComputerDatabaseConnection.INSTANCE
													.getInstance().prepareStatement(sql)) {
			try (final ResultSet rs = pStatement.executeQuery()) {
				while (rs.next()) {
					companies.add(companyMapper.rowMap(rs));
				}
			}
		} catch (SQLException | PersistenceException e) {
			throw new DAOException(e.getMessage());
		}

		return companies;
	}

	@Override
	public Company getById(Long id) throws DAOException {
		final CompanyMapper companyMapper = new CompanyMapper();
		final String sql = "SELECT * FROM " + company + " WHERE " + id + " = ?";

		try (final PreparedStatement pStatement = ComputerDatabaseConnection.INSTANCE
													.getInstance().prepareStatement(sql)) {
			pStatement.setLong(1, id);
			try (final ResultSet rs = pStatement.executeQuery()) {
				if (rs.first()) {
					return companyMapper.rowMap(rs);
				}
			}
		} catch (SQLException | PersistenceException e) {
				throw new DAOException(e.getMessage());
		}
		return null;
	}
}
