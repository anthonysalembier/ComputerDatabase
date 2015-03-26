package com.excilys.dao;

import java.io.IOException;
import java.io.InputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.excilys.connection.ComputerDatabaseConnection;
import com.excilys.exception.DAOException;
import com.excilys.exception.PersistenceException;
import com.excilys.mapper.CompanyMapper;
import com.excilys.model.Company;

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

		StringBuffer sql = new StringBuffer();
		sql.append("SELECT * FROM ");
		sql.append(company);
		sql.append(" ORDER BY ").append(name);
		
		try (final PreparedStatement pStatement = ComputerDatabaseConnection.INSTANCE
													.getInstance().prepareStatement(sql.toString())) {
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
		
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT * FROM ").append(company);
		sql.append(" WHERE ").append(this.id).append(" = ?");

		try (final PreparedStatement pStatement = ComputerDatabaseConnection.INSTANCE
													.getInstance().prepareStatement(sql.toString())) {
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
	
	@Override
	public int count() throws DAOException {
		final String sql = "SELECT COUNT(*) FROM " + company;
		try (final Statement state = ComputerDatabaseConnection.INSTANCE.getInstance().createStatement()) {
            final ResultSet rs = state.executeQuery(sql);
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException | PersistenceException e) {
            throw new DAOException(e);
		}
        return 0;
	}
}
