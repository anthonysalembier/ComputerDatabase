package com.excilys.connection;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.excilys.exception.PersistenceException;
import com.jolbox.bonecp.BoneCP;
import com.jolbox.bonecp.BoneCPConfig;

@Component
public class ComputerDatabaseConnection {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ComputerDatabaseConnection.class);

	private static final ThreadLocal<Connection> CONNECTION = new ThreadLocal<Connection>() {
		@Override
	    protected Connection initialValue() {
	        try {
				return connectionPool.getConnection();
			} catch (SQLException e) {
				LOGGER.error("Error occurs while initiate local thread");
				throw new PersistenceException(e);
			}
	    }
	};
	private static BoneCP connectionPool = null;
	private Properties properties;

	public ComputerDatabaseConnection() {
		try {
			loadConfigFile();
		} catch (Exception e) {
			throw new PersistenceException(e);
		}
	}

	/**
	 * @return A connection to the database
	 */
	public Connection getInstance() {
		try {
			if (connectionPool == null) {
				Class.forName(properties.getProperty("driver")).newInstance();
				
				BoneCPConfig config = new BoneCPConfig();
				
				config.setJdbcUrl(properties.getProperty("url"));
				config.setUsername(properties.getProperty("user"));
				config.setPassword(properties.getProperty("password"));
				config.setMinConnectionsPerPartition(Integer.valueOf(properties
						.getProperty("minConnectionsPerPartition")));
				config.setMaxConnectionsPerPartition(Integer.valueOf(properties
						.getProperty("maxConnectionsPerPartition")));
				config.setPartitionCount(Integer.valueOf(properties
						.getProperty("partitionCount")));
				
				connectionPool = new BoneCP(config);
			}
		} catch (SQLException | InstantiationException | IllegalAccessException
				| ClassNotFoundException e) {
			LOGGER.error("Error occurs while trying to get a connection instance");
			throw new PersistenceException(e);
		}

		return getConnection();
	}
	
	/**
	 * Starts a new transaction.
	 * We must call commit() to commit modifications.
	 */
	public void startTransaction() {
		final Connection connection = CONNECTION.get();
		if (connection != null) {
			try {
				connection.setAutoCommit(false);
			} catch (SQLException e) {
				LOGGER.error("Error occurs while starting a transaction");
				throw new PersistenceException(e);
			}
		}
	}
	
	/**
	 * Finishes current transaction.
	 * All modifications will be committed right now.
	 */
	public void endTransaction() {
		final Connection connection = CONNECTION.get();
		if (connection != null) {
			try {
				connection.setAutoCommit(true);
			} catch (SQLException e) {
				LOGGER.error("Error occurs while ending a transaction");
				throw new PersistenceException(e);
			}
		}
	}
	
	/**
	 * Commits modifications done by an open transaction.
	 */
	public void commit() {
		final Connection connection = CONNECTION.get();
		if (connection != null) {
			try {
				connection.commit();
			} catch (SQLException e) {
				LOGGER.error("Error occurs while commiting a transaction");
				throw new PersistenceException(e);
			}
		}
	}
	
	/**
	 * Rollbacks modifications done by an open transaction.
	 */
	public void rollback() {
		final Connection connection = CONNECTION.get();
		if (connection != null) {
			try {
				connection.rollback();
			} catch (SQLException e) {
				LOGGER.error("Error occurs while rollbacking a transaction");
				throw new PersistenceException(e);
			}
		}
	}
	
	/**
	 * Closes the current opened connection.
	 */
	public void close() {
		final Connection connection = CONNECTION.get();
		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				LOGGER.error("Error occurs while closing a transaction");
				throw new PersistenceException(e);
			}
			CONNECTION.remove();
		}
	}

	/*
	 * Retrieve a connection for the current thread.
	 */
	private Connection getConnection() {
		return CONNECTION.get();
	}

	/*
	 * Load config.properties.
	 */
	private void loadConfigFile() throws IOException {
		properties = new Properties();
		try (final InputStream is = ComputerDatabaseConnection.class
				.getClassLoader().getResourceAsStream("config.properties")) {
			properties.load(is);
		}
	}
}
