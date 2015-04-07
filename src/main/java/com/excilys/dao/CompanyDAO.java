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
	
	private ComputerDatabaseConnection connection = ComputerDatabaseConnection.INSTANCE;
	
	private CompanyDAO() {
		if (properties == null) {
			properties = new Properties();
			
			try (InputStream input = CompanyDAO.class.getClassLoader().getResourceAsStream("tableConfig.properties")) {
				properties.load(input);
				company = properties.getProperty("company");
				id = properties.getProperty("companyId");
				name = properties.getProperty("companyName");
			} catch (IOException e) {
				throw new DAOException(e.getMessage());
			}
		}
	}

	@Override
	public List<Company> getAll() {
		final List<Company> companies = new ArrayList<>();
		final CompanyMapper companyMapper = new CompanyMapper();

		StringBuffer sql = new StringBuffer();
		sql.append("SELECT * FROM ");
		sql.append(company);
		sql.append(" ORDER BY ").append(name);
		
		try (final PreparedStatement pStatement = connection.getInstance().prepareStatement(sql.toString())) {
			try (final ResultSet rs = pStatement.executeQuery()) {
				while (rs.next()) {
					companies.add(companyMapper.rowMap(rs));
				}
			}
		} catch (SQLException | PersistenceException e) {
			throw new DAOException(e.getMessage());
		} finally {
			connection.close();
		}

		return companies;
	}

	@Override
	public Company getById(Long id) {
		final CompanyMapper companyMapper = new CompanyMapper();
		
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT * FROM ").append(company);
		sql.append(" WHERE ").append(this.id).append(" = ?");

		try (final PreparedStatement pStatement = connection.getInstance().prepareStatement(sql.toString())) {
			pStatement.setLong(1, id);
			try (final ResultSet rs = pStatement.executeQuery()) {
				if (rs.first()) {
					return companyMapper.rowMap(rs);
				}
			}
		} catch (SQLException | PersistenceException e) {
				throw new DAOException(e.getMessage());
		} finally {
			connection.close();
		}
		return null;
	}
	
	@Override
	public int count() {
		final String sql = "SELECT COUNT(*) FROM " + company;
		try (final Statement state = connection.getInstance().createStatement()) {
            final ResultSet rs = state.executeQuery(sql);
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException | PersistenceException e) {
            throw new DAOException(e.getMessage());
		} finally {
			connection.close();
		}
        return 0;
	}
}
