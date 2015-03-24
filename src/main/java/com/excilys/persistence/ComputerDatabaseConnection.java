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

	private ComputerDatabaseConnection() {
		try {
			if (System.getProperty("env") != null) {
				if (System.getProperty("env").equals("test")) {
					Class.forName("org.h2.Driver").newInstance();
				}
			} else {
				Class.forName("com.mysql.jdbc.Driver").newInstance();
			}
		} catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
			e.printStackTrace();
		}
	}

	public Connection getInstance() throws PersistenceException {
		Connection connection = null;

		try {
			if (System.getProperty("env") != null) {
				if (System.getProperty("env").equals("test")) {
					Class.forName("org.h2.Driver").newInstance();
				}
			} else {
				Class.forName("com.mysql.jdbc.Driver").newInstance();
			}
			loadConfigFile();
			connection = DriverManager.getConnection(url, properties);
		} catch (SQLException | IOException | InstantiationException
				| IllegalAccessException | ClassNotFoundException e) {
			throw new PersistenceException(e.getMessage());
		}

		return connection;
	}

	private void loadConfigFile() throws IOException {
		if (properties == null) {
			properties = new Properties();
			if (System.getProperty("env") != null) {
				if (System.getProperty("env").equals("test")) {
					try (final InputStream is = ComputerDatabaseConnection.class
							.getClassLoader().getResourceAsStream(
									"testConfig.properties")) {
						properties.load(is);
						url = properties.getProperty("url");
					}
				}
			} else {
				try (final InputStream is = ComputerDatabaseConnection.class
						.getClassLoader().getResourceAsStream(
								"config.properties")) {
					properties.load(is);
					url = properties.getProperty("url");
				}
			}
		}
	}
}
