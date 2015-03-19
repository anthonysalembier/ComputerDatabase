package com.excilys.dao;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.File;
import java.nio.charset.Charset;
import java.time.LocalDateTime;
import java.util.List;

import org.dbunit.IDatabaseTester;
import org.dbunit.JdbcDatabaseTester;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.h2.tools.RunScript;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.excilys.exception.DAOException;
import com.excilys.model.Company;
import com.excilys.model.Computer;

public class ComputerDAOTest {
	
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
		RunScript.execute(JDBC_URL, USER, PASSWORD, "classpath:scripts/schema.sql", Charset.forName("UTF8"), false);
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
		return new FlatXmlDataSetBuilder().build(new File("src/test/resources/scripts/dataset.xml"));
	}
	
	@Test
	public void getAllComputersShouldReturnAllComputersInDB() throws DAOException {
		// GIVEN
		final int nbOfComputersInDataset = 3;
		
		Company company01 = new Company();
		company01.setId(666);
		company01.setName("CompanyTest01");
		
		final Computer computer01InTheDataset = new Computer();
		computer01InTheDataset.setId(666);
		computer01InTheDataset.setName("ComputerTest01");
		computer01InTheDataset.setIntroduced(LocalDateTime.of(2001, 01, 01, 00, 00, 00));
		computer01InTheDataset.setDiscontinued(LocalDateTime.of(2001, 01, 01, 00, 00, 00));
		computer01InTheDataset.setCompany(company01);

		final Computer computer02InTheDataset = new Computer();
		computer02InTheDataset.setId(667);
		computer02InTheDataset.setName("ComputerTest02");

		final Computer computer03InTheDataset = new Computer();
		computer03InTheDataset.setId(668);
		computer03InTheDataset.setName("ComputerTest03");

		final Computer computer04InTheDataset = new Computer();
		computer04InTheDataset.setId(669);
		computer04InTheDataset.setName("ComputerTest04");

		final Computer computer05InTheDataset = new Computer();
		computer05InTheDataset.setId(670);
		computer05InTheDataset.setName("ComputerTest05");

		final Computer computer06InTheDataset = new Computer();
		computer06InTheDataset.setId(0);
		computer06InTheDataset.setName("ComputerTest02");

		final Computer computerNotInTheDataset = new Computer();
		computerNotInTheDataset.setId(0);
		computerNotInTheDataset.setName("DoesNotExist");
		
		// WHEN
		List<Computer> computers = ComputerDAO.INSTANCE.getAll();
		
		// THEN
		assertThat(computers.size()).isEqualTo(nbOfComputersInDataset);
		
		assertThat(computers).contains(computer01InTheDataset);
		assertThat(computers).contains(computer02InTheDataset);
		assertThat(computers).contains(computer03InTheDataset);
		assertThat(computers).contains(computer04InTheDataset);
		
		assertThat(computers).doesNotContain(computerNotInTheDataset);
		
	}
	
	

}
