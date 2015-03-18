package com.excilys.persistence;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import com.excilys.exception.PersistenceException;

public enum ComputerDatabaseConnection {
	INSTANCE;

	private Properties properties;
	private String url;

	public Connection getInstance() throws PersistenceException {
		Connection connection = null;
		
		try {
			loadConfigFile();
			connection = DriverManager.getConnection(url, properties);
		} catch (SQLException | IOException e) {
			throw new PersistenceException(e.getMessage());
		}
		
		return connection;
	}

	private void loadConfigFile() throws IOException {
		if (properties == null) {
			properties = new Properties();
			try (final InputStream is = ComputerDatabaseConnection.class
					.getClassLoader().getResourceAsStream("config.properties")) {
				properties.load(is);
				url = properties.getProperty("url");
			}	
		}
	}
}
