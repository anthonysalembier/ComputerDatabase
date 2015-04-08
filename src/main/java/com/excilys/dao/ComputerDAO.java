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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.excilys.connection.ComputerDatabaseConnection;
import com.excilys.exception.DAOException;
import com.excilys.exception.PersistenceException;
import com.excilys.mapper.ComputerMapper;
import com.excilys.model.Computer;
import com.excilys.util.Page;

@Repository
public class ComputerDAO implements DAO<Computer, Long> {
	
	private Properties properties;
	
	private String computerTable;
	private String companyTable;
	private String computerIdColumn;
	private String computerNameColumn;
	private String introducedColumn;
	private String discontinuedColumn;
	private String computerCompanyIdColumn;
	private String companyIdColumn;
	private String companyNameColumn;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ComputerDAO.class);
	
	@Autowired
	private ComputerDatabaseConnection connection;
	
	public ComputerDAO() {
		if (properties == null) {
			properties = new Properties();
			
			try (InputStream input = CompanyDAO.class.getClassLoader()
										.getResourceAsStream("tableConfig.properties")) {
				properties.load(input);
				computerTable = properties.getProperty("computer");
				companyTable = properties.getProperty("company");
				computerIdColumn = properties.getProperty("computerId");
				computerNameColumn = properties.getProperty("computerName");
				introducedColumn = properties.getProperty("introduced");
				discontinuedColumn = properties.getProperty("discontinued");
				computerCompanyIdColumn = properties.getProperty("computerCompanyId");
				companyIdColumn = properties.getProperty("companyId");
				companyNameColumn = properties.getProperty("companyName");
				
			} catch (IOException e) {
				// LOGGER.error("Error occurs while reading configuration file");
				throw new DAOException(e.getMessage());
			}
		}
	}
	
	@Override
	public List<Computer> getAll() {
		final List<Computer> computers = new ArrayList<>();
		final ComputerMapper computerMapper = new ComputerMapper();
		
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT * FROM ").append(computerTable);
		sql.append(" LEFT OUTER JOIN ").append(companyTable);
		sql.append(" ON ");
		sql.append(computerTable).append(".").append(computerCompanyIdColumn);
		sql.append(" = ");
		sql.append(companyTable).append(".").append(companyIdColumn);
		sql.append(" ORDER BY ");
		sql.append(computerTable).append(".").append(computerNameColumn);

		try (final PreparedStatement pStatement = connection.getInstance().prepareStatement(sql.toString())) {
			try (final ResultSet rs = pStatement.executeQuery()) {
				while (rs.next()) {
					computers.add(computerMapper.rowMap(rs));
				}
			}
		} catch (SQLException | PersistenceException e) {
			LOGGER.error("Error occurs while processing 'getAll()'");
			throw new DAOException(e.getMessage());
		} finally {
			connection.close();
		}

		return computers;
	}
	
	public List<Computer> getAll(Page page) {
		final List<Computer> computers = new ArrayList<>();
        final ComputerMapper computerMapper = new ComputerMapper();
        
        StringBuffer sql = new StringBuffer();
        sql.append("SELECT * FROM ").append(computerTable);
        sql.append(" LEFT OUTER JOIN ").append(companyTable); 
        sql.append(" ON ");
        sql.append(computerTable).append(".").append(computerCompanyIdColumn);
        sql.append(" = ");
        sql.append(companyTable).append(".").append(companyIdColumn);
        sql.append(" ORDER BY ? ? LIMIT ? OFFSET ?");
        
        try (final PreparedStatement pStatement = connection.getInstance().prepareStatement(sql.toString())) {
            pStatement.setString(1, page.getProperties());
            pStatement.setString(2, page.getSort().toString());
            pStatement.setInt(3, page.getSize());
            pStatement.setInt(4, page.getOffset());
            final ResultSet rs = pStatement.executeQuery();
            while (rs.next()) {
                computers.add(computerMapper.rowMap(rs));
            }
        } catch (SQLException | PersistenceException e) {
        	LOGGER.error("Error occurs while processing 'getAll(Page)'");
            throw new DAOException(e.getMessage());
        } finally {
			connection.close();
		}

        return computers;
	}

	@Override
	public Computer getById(Long id) {
		final ComputerMapper computerMapper = new ComputerMapper();
		
		StringBuffer sql = new StringBuffer(); 
		sql.append("SELECT * FROM ").append(computerTable);
		sql.append(" LEFT OUTER JOIN ").append(companyTable);
		sql.append(" ON ").append(computerTable).append(".").append(computerCompanyIdColumn);
		sql.append(" = ");
		sql.append(companyTable).append(".").append(companyIdColumn);
		sql.append(" WHERE ").append(computerTable).append(".").append(computerIdColumn).append(" = ?");

		try (final PreparedStatement pStatement = connection.getInstance().prepareStatement(sql.toString())) {
			pStatement.setLong(1, id);
			try (final ResultSet rs = pStatement.executeQuery()) {
				if (rs.first()) {
					return computerMapper.rowMap(rs);
				}
			}
		} catch (SQLException | PersistenceException e) {
			LOGGER.error("Error occurs while processing 'getById(Id:{})'", id);
			throw new DAOException(e.getMessage());
		} finally {
			connection.close();
		}

		return null;
	}
	
	@Override
	public List<Computer> getByName(String name, Page page) {
		final List<Computer> computers = new ArrayList<>();
        final ComputerMapper computerMapper = new ComputerMapper();
        
        StringBuffer sql = new StringBuffer();
        sql.append("SELECT * FROM ").append(computerTable);
        sql.append(" LEFT JOIN ").append(companyTable); 
        sql.append(" ON ");
        sql.append(computerTable).append(".").append(computerCompanyIdColumn);
        sql.append(" = ");
        sql.append(companyTable).append(".").append(companyIdColumn);
        sql.append(" WHERE ").append(computerTable).append(".").append(computerNameColumn);
        sql.append(" LIKE ");
        sql.append("?");
        sql.append(" OR ");
        sql.append(companyTable).append(".").append(companyNameColumn);
        sql.append(" LIKE ");
        sql.append("?");
        
        sql.append(" ORDER BY ? ? LIMIT ? OFFSET ?");
        
        try (final PreparedStatement pStatement = connection.getInstance().prepareStatement(sql.toString())) {
        	pStatement.setString(1, "%" + name + "%");
        	pStatement.setString(2, "%" + name + "%");
            pStatement.setString(3, page.getProperties());
            pStatement.setString(4, page.getSort().toString());
            pStatement.setInt(5, page.getSize());
            pStatement.setInt(6, page.getOffset());
            final ResultSet rs = pStatement.executeQuery();
            while (rs.next()) {
                computers.add(computerMapper.rowMap(rs));
            }
        } catch (SQLException | PersistenceException e) {
        	LOGGER.error("Error occurs while processing 'getByName(Name:{}, Page)'", name);
            throw new DAOException(e.getMessage());
        } finally {
			connection.close();
		}

        return computers;
	}

	@Override
	public void create(Computer entity) {
		StringBuffer sql = new StringBuffer();
		sql.append("INSERT INTO ").append(computerTable).append(" VALUES (?, ?, ?, ?, ?)");

		try (final PreparedStatement pStatement = connection.getInstance()
													.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS)) {
			pStatement.setObject(1, null);
			if (entity.getName() != null) {
				String name = entity.getName().trim();
				if (!name.isEmpty()) {
					pStatement.setString(2, name);
				} else {
					throw new DAOException("Name is empty");
				}
			} else {
				throw new SQLException();
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
			if (entity.getCompany() == null || entity.getCompany().getId() == 0) {
				pStatement.setObject(5, null);
			} else {
				pStatement.setLong(5, entity.getCompany().getId());
			}
			pStatement.execute();
			
			LOGGER.info("Computer successfully added");
		} catch (SQLException | PersistenceException e) {
			LOGGER.error("Error occurs while processing 'create(Computer)'");
			throw new DAOException(e.getMessage());
		} finally {
			connection.close();
		}
	}

	@Override
	public void update(Computer entity) {
		StringBuffer sql = new StringBuffer();
		sql.append("UPDATE ").append(computerTable);
		sql.append(" SET ").append(computerNameColumn).append(" = ?, ");
		sql.append(introducedColumn).append(" = ?, ");
		sql.append(discontinuedColumn).append(" = ?, ");
		sql.append(computerCompanyIdColumn).append(" = ? ");
		sql.append("WHERE ").append(computerIdColumn).append(" = ?");
		
		try (final PreparedStatement pStatement = connection.getInstance().prepareStatement(sql.toString())) {
			if (entity.getName() != null) {
				String name = entity.getName().trim();
				if (!name.isEmpty()) {
					pStatement.setString(1, name);
				} else {
					throw new SQLException();
				}
			} else {
				throw new SQLException();
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
			if (entity.getCompany() == null || entity.getCompany().getId() == 0) {
				pStatement.setObject(4, null);
			} else {
				pStatement.setLong(4, entity.getCompany().getId());
			}
			pStatement.setLong(5, entity.getId());
			pStatement.execute();
			
			LOGGER.info("Computer (Id:{}) successfully updated", entity.getId());
		} catch (SQLException | PersistenceException e) {
			LOGGER.error("Error occurs while processing 'update(Computer (Id:{})'", entity.getId());
			throw new DAOException(e.getMessage());
		} finally {
			connection.close();
		}
	}

	@Override
	public void delete(Long id) {
		StringBuffer sql = new StringBuffer();
		sql.append("DELETE FROM ").append(computerTable).append(" WHERE ").append(computerIdColumn).append(" = ?");
		
		try (final PreparedStatement pStatement = connection.getInstance().prepareStatement(sql.toString())) {
			pStatement.setLong(1, id);
			pStatement.execute();
			
			LOGGER.info("Computer (id:{}) successfully deleted", id);
		} catch (SQLException | PersistenceException e) {
			LOGGER.error("Error occurs while processing 'delete(Id:{})'", id);
			throw new DAOException(e.getMessage());
		} finally {
			connection.close();
		}
	}
	
	@Override
	public int count() {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT COUNT(*) FROM ").append(computerTable);
		
		try (final Statement state = connection.getInstance().createStatement()) {
            final ResultSet rs = state.executeQuery(sql.toString());
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException | PersistenceException e) {
        	LOGGER.error("Error occurs while processing 'count()'");
            throw new DAOException(e.getMessage());
		} finally {
			connection.close();
		}
        return 0;
	}
	
	@Override
	public int countByName(String name) {
		StringBuffer sql = new StringBuffer();
		
		sql.append("SELECT COUNT(*) FROM ").append(computerTable);
        sql.append(" LEFT JOIN ").append(companyTable); 
        sql.append(" ON ");
        sql.append(computerTable).append(".").append(computerCompanyIdColumn);
        sql.append(" = ");
        sql.append(companyTable).append(".").append(companyIdColumn);
        sql.append(" WHERE ").append(computerTable).append(".").append(computerNameColumn);
        sql.append(" LIKE ");
        sql.append("?");
        sql.append(" OR ");
        sql.append(companyTable).append(".").append(companyNameColumn);
        sql.append(" LIKE ");
        sql.append("?");
		
		try (final PreparedStatement pState = connection.getInstance().prepareStatement(sql.toString())) {
			pState.setString(1, "%" + name + "%");
        	pState.setString(2, "%" + name + "%");
			
            final ResultSet rs = pState.executeQuery();
            
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException | PersistenceException e) {
        	LOGGER.error("Error occurs while processing 'countByName(Name:{})'", name);
            throw new DAOException(e.getMessage());
		} finally {
			connection.close();
		}
		return 0;
	}

}
