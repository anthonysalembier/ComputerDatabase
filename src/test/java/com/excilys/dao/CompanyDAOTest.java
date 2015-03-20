package com.excilys.dao;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.File;
import java.nio.charset.Charset;
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
	public void getAllCompaniesShouldReturnAllCompaniesInDB() throws Exception {
		importDataSet("src/test/resources/scripts/datasets/companyDAO/getAllCompaniesDataset.xml");
		
		// GIVEN
		final int nbOfCompaniesInDataset = 3;
		
		final Company company01InTheDataset = new Company();
		company01InTheDataset.setId(666);
		company01InTheDataset.setName("CompanyTest01");

		final Company company02InTheDataset = new Company();
		company02InTheDataset.setId(667);
		company02InTheDataset.setName("CompanyTest02");

		final Company company03InTheDataset = new Company();
		company03InTheDataset.setId(668);
		company03InTheDataset.setName("CompanyTest03");

		final Company company04InTheDataset = new Company();
		company04InTheDataset.setId(0);
		company04InTheDataset.setName("CompanyTest02");

		final Company company01NotInTheDataset = new Company();
		company01NotInTheDataset.setId(0);
		company01NotInTheDataset.setName("DoesNotExist");
		
		// WHEN
		List<Company> companies = CompanyDAO.INSTANCE.getAll();
		
		// THEN
		assertThat(companies.size()).isEqualTo(nbOfCompaniesInDataset);
		
		assertThat(companies).contains(company01InTheDataset);
		assertThat(companies).contains(company02InTheDataset);
		assertThat(companies).contains(company03InTheDataset);
		assertThat(companies).contains(company04InTheDataset);
		
		assertThat(companies).doesNotContain(company01NotInTheDataset);
		
	}
	
	@Test
	public void getCompanyByIdShouldReturnTheCorrectCompanyInDB() throws Exception {
		importDataSet("src/test/resources/scripts/datasets/companyDAO/getCompanyByIdDataset.xml");
		
		// GIVEN
		final long id = 111;
		
		final Company companyInTheDataset = new Company();
		companyInTheDataset.setId(id);
		companyInTheDataset.setName("CompanyTest01");

		final Company companyNotInTheDataset = new Company();
		companyNotInTheDataset.setId(0);
		companyNotInTheDataset.setName("DoesNotExist");
		
		// WHEN
		Company company = CompanyDAO.INSTANCE.getById(id);
		
		// THEN
		assertThat(company).isEqualTo(companyInTheDataset);
		assertThat(company).isNotEqualTo(companyNotInTheDataset);
	}
	
}
