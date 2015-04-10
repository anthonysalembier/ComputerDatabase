package com.excilys.connection;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.excilys.exception.DAOException;

@Component
public class ComputerDatabaseConnection {
	
	@Autowired
	private BasicDataSource dataSource;
	
	private final ThreadLocal<Connection> CONNECTION = new ThreadLocal<Connection>() {
		@Override
        protected Connection initialValue()
        {
            try {
				return dataSource.getConnection();
			} catch (SQLException e) {
				throw new DAOException(e);
			}
        }
	};
	
	public void setDataSource(BasicDataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	public Connection getConnection() throws SQLException {
		return CONNECTION.get();
	}
}
