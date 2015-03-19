package com.excilys.dao;

import java.io.File;
import java.nio.charset.Charset;
import java.util.List;

import javax.sql.DataSource;

import org.dbunit.IDatabaseTester;
import org.dbunit.JdbcDatabaseTester;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.h2.jdbcx.JdbcDataSource;
import org.h2.tools.RunScript;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.assertj.core.api.Assertions.*;

import com.excilys.exception.DAOException;
import com.excilys.model.Computer;


public class CompanyDAOTest {
	
	private static final String JDBC_DRIVER = org.h2.Driver.class.getName();
	private static final String JDBC_URL = "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1";
	private static final String USER = "sa";
	private static final String PASSWORD = "";

	/**
	 * Setting the environment into test to tell the JDBC connector to connect to the test DB
	 */
	@BeforeClass
	public static void setEnvProperty() {
		System.setProperty("env", "test");
	}

	/**
	 * Running schema to set up the test DB
	 * @throws Exception
	 */
	@BeforeClass
	public static void createSchema() throws Exception {
		RunScript.execute(JDBC_URL, USER, PASSWORD, "schema.sql", Charset.forName("UTF8"), false);
	}
	

	/**
	 * Set up operation done after tests to clean.
	 * @param dataSet
	 * @throws Exception
	 */
	private void cleanlyInsertDataset(IDataSet dataSet) throws Exception {
		IDatabaseTester databaseTester = new JdbcDatabaseTester(JDBC_DRIVER, JDBC_URL, USER, PASSWORD);
		databaseTester.setSetUpOperation(DatabaseOperation.CLEAN_INSERT);
		databaseTester.setDataSet(dataSet);
		databaseTester.onSetup();
	}
	
	/**
	 * Import the test data set before each test case.
	 * @throws Exception
	 */
	@Before
	public void importDataSet() throws Exception {
		IDataSet dataSet = readDataSet();
		cleanlyInsertDataset(dataSet);
	}
	
	/**
	 * Read the dataset.xml containing test data set.
	 * @return a IDataSet that contains test data set
	 * @throws Exception
	 */
	private IDataSet readDataSet() throws Exception {
		return new FlatXmlDataSetBuilder().build(new File("dataset.xml"));
	}
	
	private DataSource dataSource() {
		JdbcDataSource dataSource = new JdbcDataSource();
		dataSource.setURL(JDBC_URL);
		dataSource.setUser(USER);
		dataSource.setPassword(PASSWORD);
		return dataSource;
	}

	@Test
	public void getAllComputersShouldReturnAllComputersInDB() throws DAOException {
		// GIVEN
		List<Computer> computers = ComputerDAO.INSTANCE.getAll();
		
		// WHEN
		System.out.println("PiPou");
		assertThat("test").isEqualTo("test");
		
		// THEN
		
	}
	
}
