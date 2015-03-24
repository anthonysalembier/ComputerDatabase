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
	public void importDataSet(String path) throws Exception {
		IDataSet dataSet = readDataSet(path);
		cleanlyInsertDataset(dataSet);
	}
	
	/**
	 * Read the dataset.xml containing test data set.
	 * @return a IDataSet that contains test data set
	 * @throws Exception
	 */
	private IDataSet readDataSet(String path) throws Exception {
		return new FlatXmlDataSetBuilder().build(new File(path));
	}
	
	@Test
	public void getAllComputersShouldReturnAllComputersInDB() throws Exception {
		importDataSet("src/test/resources/scripts/datasets/computerDAO/getAllComputersDataset.xml");
		
		// GIVEN
		final int nbOfComputersInDataset = 4;
		
		Company company01 = new Company();
		company01.setId(666);
		company01.setName("CompanyTest01");
		
		Company company02 = new Company();
		company02.setId(667L);
		company02.setName("CompanyTest02");
		
		Company company03 = new Company();
		company03.setId(668L);
		company03.setName("CompanyTest03");
		
		final Computer computer01InTheDataset = new Computer();
		computer01InTheDataset.setId(666L);
		computer01InTheDataset.setName("ComputerTest01");
		computer01InTheDataset.setIntroduced(LocalDateTime.of(2001, 01, 01, 00, 00, 00));
		computer01InTheDataset.setDiscontinued(LocalDateTime.of(2001, 01, 01, 00, 00, 00));
		computer01InTheDataset.setCompany(company01);

		final Computer computer02InTheDataset = new Computer();
		computer02InTheDataset.setId(667L);
		computer02InTheDataset.setName("ComputerTest02");
		computer02InTheDataset.setIntroduced(LocalDateTime.of(2002, 02, 02, 00, 00, 00));
		computer02InTheDataset.setDiscontinued(LocalDateTime.of(2002, 02, 02, 00, 00, 00));
		computer02InTheDataset.setCompany(company01);
		
		final Computer computer03InTheDataset = new Computer();
		computer03InTheDataset.setId(668L);
		computer03InTheDataset.setName("ComputerTest03");
		computer03InTheDataset.setIntroduced(LocalDateTime.of(2003, 03, 03, 00, 00, 00));
		computer03InTheDataset.setDiscontinued(LocalDateTime.of(2003, 03, 03, 00, 00, 00));
		computer03InTheDataset.setCompany(company02);

		final Computer computer04InTheDataset = new Computer();
		computer04InTheDataset.setId(669L);
		computer04InTheDataset.setName("ComputerTest04");
		computer04InTheDataset.setIntroduced(LocalDateTime.of(2004, 04, 04, 00, 00, 00));
		computer04InTheDataset.setDiscontinued(LocalDateTime.of(2004, 04, 04, 00, 00, 00));
		computer04InTheDataset.setCompany(company03);

		final Computer computer05InTheDataset = new Computer();
		computer05InTheDataset.setId(0L);
		computer05InTheDataset.setName("ComputerTest01");
		computer05InTheDataset.setIntroduced(LocalDateTime.of(2001, 01, 01, 00, 00, 00));
		computer05InTheDataset.setDiscontinued(LocalDateTime.of(2001, 01, 01, 00, 00, 00));
		computer05InTheDataset.setCompany(company01);

		// WHEN
		List<Computer> computers = ComputerDAO.INSTANCE.getAll();
		
		// THEN
		assertThat(computers.size()).isEqualTo(nbOfComputersInDataset);
		
		assertThat(computers).contains(computer01InTheDataset,
									computer02InTheDataset,
									computer03InTheDataset,
									computer04InTheDataset,
									computer05InTheDataset);
	}
	
	@Test
	public void getAllComputersShouldReturnOnlyComputersInDB() throws Exception {
		importDataSet("src/test/resources/scripts/datasets/computerDAO/getAllComputersDataset.xml");
		
		// GIVEN
		Company company01 = new Company();
		company01.setId(666);
		company01.setName("CompanyTest01");

		final Computer computerNotInTheDataset = new Computer();
		computerNotInTheDataset.setId(0L);
		computerNotInTheDataset.setName("DoesNotExist");
		computerNotInTheDataset.setIntroduced(LocalDateTime.of(2000, 11, 11, 00, 00, 00));
		computerNotInTheDataset.setDiscontinued(LocalDateTime.of(2000, 11, 11, 00, 00, 00));
		computerNotInTheDataset.setCompany(company01);
		
		// WHEN
		List<Computer> computers = ComputerDAO.INSTANCE.getAll();
				
		// THEN
		assertThat(computers).doesNotContain(computerNotInTheDataset);
	}
	
	@Test
	public void getComputerByIdShouldReturnTheCorrectComputerInDB() throws Exception {
		importDataSet("src/test/resources/scripts/datasets/computerDAO/getComputerByIdDataset.xml");
		
		// GIVEN
		final long id = 666L;
		
		Company company01 = new Company();
		company01.setId(666L);
		company01.setName("CompanyTest01");
		
		final Computer computerInTheDataset = new Computer();
		computerInTheDataset.setId(id);
		computerInTheDataset.setName("ComputerTest01");
		computerInTheDataset.setIntroduced(LocalDateTime.of(2001, 01, 01, 00, 00, 00));
		computerInTheDataset.setDiscontinued(LocalDateTime.of(2001, 01, 01, 00, 00, 00));
		computerInTheDataset.setCompany(company01);
		
		// WHEN
		Computer computer = ComputerDAO.INSTANCE.getById(id);
		
		// THEN
		assertThat(computer).isEqualTo(computerInTheDataset);
	}
	
	@Test
	public void createComputerShouldAddTheComputerInDB() throws Exception {
		importDataSet("src/test/resources/scripts/datasets/computerDAO/createComputerDataset.xml");
		
		// GIVEN
		Company company01 = new Company();
		company01.setId(666L);
		company01.setName("CompanyTest01");
		
		final Computer computer = new Computer();
		computer.setName("ComputerTest10");
		computer.setIntroduced(LocalDateTime.of(2010, 10, 10, 00, 00, 00));
		computer.setDiscontinued(LocalDateTime.of(2010, 10, 10, 00, 00, 00));
		computer.setCompany(company01);
		
		// WHEN
		ComputerDAO.INSTANCE.create(computer);
		List<Computer> computers = ComputerDAO.INSTANCE.getAll();
		
		// THEN
		assertThat(computers.size()).isEqualTo(1);
		assertThat(computers).contains(computer);
	}
	
	@Test
	public void updateComputerShouldUpdateTheComputerInDB() throws Exception {
		importDataSet("src/test/resources/scripts/datasets/computerDAO/updateComputerDataset.xml");
		
		// GIVEN
		Company company01 = new Company();
		company01.setId(666L);
		company01.setName("CompanyTest01");

		final Computer computer01 = new Computer();
		computer01.setId(666L);
		computer01.setName("ComputerTest10");
		computer01.setIntroduced(LocalDateTime.of(2010, 10, 10, 00, 00, 00));
		computer01.setDiscontinued(LocalDateTime.of(2010, 10, 10, 00, 00, 00));
		computer01.setCompany(company01);

		// WHEN
		ComputerDAO.INSTANCE.update(computer01);
		Computer computer02 = ComputerDAO.INSTANCE.getById(666L);
		List<Computer> computers = ComputerDAO.INSTANCE.getAll();
		
		// THEN
		assertThat(computers).contains(computer01);
		assertThat(computer02.getName()).isEqualTo("ComputerTest10");
		assertThat(computer02.getCompany()).isEqualTo(company01);
	}
	
	@Test
	public void deleteComputerShouldDeleteTheComputerInDB() throws Exception {
		importDataSet("src/test/resources/scripts/datasets/computerDAO/deleteComputerDataset.xml");
		
		// GIVEN

		// WHEN
		ComputerDAO.INSTANCE.delete(666L);
		List<Computer> computers = ComputerDAO.INSTANCE.getAll();
		
		// THEN
		assertThat(computers.size()).isEqualTo(0);
	}
	
	

}
