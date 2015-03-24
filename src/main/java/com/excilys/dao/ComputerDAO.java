package com.excilys.dao;

import java.io.IOException;
import java.io.InputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.excilys.exception.DAOException;
import com.excilys.exception.PersistenceException;
import com.excilys.mapper.ComputerMapper;
import com.excilys.model.Computer;
import com.excilys.persistence.ComputerDatabaseConnection;
import com.excilys.util.Page;

public enum ComputerDAO implements DAO<Computer, Long> {
	INSTANCE;
	
	private Properties properties;
	
	private String computerTable;
	private String companyTable;
	private String computerIdColumn;
	private String computerNameColumn;
	private String introducedColumn;
	private String discontinuedColumn;
	private String computerCompanyIdColumn;
	private String companyIdColumn;
	
	private ComputerDAO() {
		if (properties == null) {
			properties = new Properties();
			
			try (InputStream input = CompanyDAO.class.getClassLoader().getResourceAsStream("tableConfig.properties")) {
				properties.load(input);
				computerTable = properties.getProperty("computer");
				companyTable = properties.getProperty("company");
				computerIdColumn = properties.getProperty("computerId");
				computerNameColumn = properties.getProperty("computerName");
				introducedColumn = properties.getProperty("introduced");
				discontinuedColumn = properties.getProperty("discontinued");
				computerCompanyIdColumn = properties.getProperty("computerCompanyId");
				companyIdColumn = properties.getProperty("companyId");
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	@Override
	public List<Computer> getAll() throws DAOException {
		final List<Computer> computers = new ArrayList<>();
		final ComputerMapper computerMapper = new ComputerMapper();
		
		final String sql = "SELECT *"
						+ " FROM " + computerTable 
						+ " LEFT OUTER JOIN " + companyTable
						+ " ON " + computerTable + "." + computerCompanyIdColumn + " = " + companyTable + "." + companyIdColumn 
						+ " ORDER BY " + computerTable + "." + computerIdColumn;

		try (final PreparedStatement pStatement = ComputerDatabaseConnection.INSTANCE
													.getInstance().prepareStatement(sql)) {
			try (final ResultSet rs = pStatement.executeQuery()) {
				while (rs.next()) {
					computers.add(computerMapper.rowMap(rs));
				}
			}
		} catch (SQLException | PersistenceException e) {
			throw new DAOException(e.getMessage());
		}

		return computers;
	}
	
	public List<Computer> getAll(Page page) throws DAOException {
		final List<Computer> computers = new ArrayList<>();
        final ComputerMapper computerMapper = new ComputerMapper();
        final String sql = "SELECT * FROM computer LEFT OUTER JOIN company"
                + " ON computer.company_id = company.id"
                + " ORDER BY ? ? LIMIT ? OFFSET ?";

        try (final PreparedStatement pStatement = ComputerDatabaseConnection.INSTANCE.getInstance().prepareStatement(sql)) {
            pStatement.setString(1, page.getProperties());
            pStatement.setString(2, page.getSort().toString());
            pStatement.setInt(3, page.getSize());
            pStatement.setInt(4, page.getOffset());
            final ResultSet rs = pStatement.executeQuery();
            while (rs.next()) {
                computers.add(computerMapper.rowMap(rs));
            }
        } catch (SQLException | PersistenceException e) {
            throw new DAOException(e);
        }

        return computers;
	}

	@Override
	public Computer getById(Long id) throws DAOException {
		final ComputerMapper computerMapper = new ComputerMapper();
		final String sql = "SELECT *"
						+ " FROM " + computerTable
						+ " LEFT OUTER JOIN " + companyTable
						+ " ON " + computerTable + "." + computerCompanyIdColumn + " = " + companyTable + "." + companyIdColumn 
						+ " WHERE " + computerTable + "." + computerIdColumn + " = ?";

		try (final PreparedStatement pStatement = ComputerDatabaseConnection.INSTANCE
													.getInstance().prepareStatement(sql)) {
			pStatement.setLong(1, id);
			try (final ResultSet rs = pStatement.executeQuery()) {
				if (rs.first()) {
					return computerMapper.rowMap(rs);
				}
			}
		} catch (SQLException | PersistenceException e) {
			throw new DAOException(e.getMessage());
		}

		return null;
	}

	@Override
	public void create(Computer entity) throws DAOException {
		final String sql = "INSERT INTO " + computerTable + " VALUES (?, ?, ?, ?, ?)";

		try (final PreparedStatement pStatement = ComputerDatabaseConnection.INSTANCE
													.getInstance().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
			pStatement.setObject(1, null);
			if (entity.getName() != null) {
				pStatement.setString(2, entity.getName());
			}
			if (entity.getIntroduced() != null) {
				pStatement.setTimestamp(3,
						Timestamp.valueOf(entity.getIntroduced()));
			} else {
				pStatement.setTimestamp(3, null);
			}
			if (entity.getDiscontinued() != null) {
				pStatement.setTimestamp(4,
						Timestamp.valueOf(entity.getDiscontinued()));
			} else {
				pStatement.setTimestamp(4, null);
			}
			if (entity.getCompany().getId() == 0) {
				pStatement.setObject(5, null);
			} else {
				pStatement.setLong(5, entity.getCompany().getId());
			}
			pStatement.execute();
		} catch (SQLException | PersistenceException e) {
			throw new DAOException(e.getMessage());
		}
	}

	@Override
	public void update(Computer entity) throws DAOException {
		final String sql = "UPDATE " + computerTable + 
							" SET " + computerNameColumn + " = ?, " + 
							introducedColumn + " = ?, " + 
							discontinuedColumn + " = ?, " + 
							computerCompanyIdColumn + " = ? " + 
							"WHERE " + computerIdColumn + " = ?";
		try (final PreparedStatement pStatement = ComputerDatabaseConnection.INSTANCE
													.getInstance().prepareStatement(sql)) {
			if (entity.getName() != null) {
				pStatement.setString(1, entity.getName());
			}
			if (entity.getIntroduced() != null) {
				pStatement.setTimestamp(2,
						Timestamp.valueOf(entity.getIntroduced()));
			} else {
				pStatement.setTimestamp(2, null);
			}
			if (entity.getDiscontinued() != null) {
				pStatement.setTimestamp(3,
						Timestamp.valueOf(entity.getDiscontinued()));
			} else {
				pStatement.setTimestamp(3, null);
			}
			if (entity.getCompany().getId() == 0) {
				pStatement.setObject(4, null);
			} else {
				pStatement.setLong(4, entity.getCompany().getId());
			}
			pStatement.setLong(5, entity.getId());
			pStatement.execute();
		} catch (SQLException | PersistenceException e) {
			throw new DAOException(e.getMessage());
		}
	}

	@Override
	public void delete(Long id) throws DAOException {
		final String sql = "DELETE FROM " + computerTable + " WHERE " + computerIdColumn + " = ?";
		try (final PreparedStatement pStatement = ComputerDatabaseConnection.INSTANCE
													.getInstance().prepareStatement(sql)) {
			pStatement.setLong(1, id);
			pStatement.execute();
		} catch (SQLException | PersistenceException e) {
			throw new DAOException(e.getMessage());
		}
	}
	
	@Override
	public int count() throws DAOException {
		final String sql = "SELECT COUNT(*) FROM " + computerTable;
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
