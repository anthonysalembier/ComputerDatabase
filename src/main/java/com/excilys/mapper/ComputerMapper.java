package com.excilys.mapper;

import java.io.IOException;
import java.io.InputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Properties;

import com.excilys.dao.CompanyDAO;
import com.excilys.model.Company;
import com.excilys.model.Computer;

public class ComputerMapper implements Mapper<Computer> {

	private Properties properties;

	private String computerTable;
	private String companyTable;
	private String computerIdColumn;
	private String companyIdColumn;
	private String computerNameColumn;
	private String companyNameColumn;
	private String introduced;
	private String discontinued;
	private String computerCompanyIdColumn;

	public ComputerMapper() {
		properties = new Properties();

		try (InputStream input = CompanyDAO.class.getClassLoader()
				.getResourceAsStream("tableConfig.properties")) {
			properties.load(input);
			computerTable = properties.getProperty("computer");
			companyTable = properties.getProperty("company");
			computerIdColumn = properties.getProperty("computerId");
			companyIdColumn = properties.getProperty("companyId");
			computerNameColumn = properties.getProperty("computerName");
			companyNameColumn = properties.getProperty("companyName");
			introduced = properties.getProperty("introduced");
			discontinued = properties.getProperty("discontinued");
			computerCompanyIdColumn = properties.getProperty("computerCompanyId");

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Computer rowMap(ResultSet res) throws SQLException {
		if (res == null) {
			throw new IllegalArgumentException();
		}
		final Computer computer = new Computer();
		
		computer.setId(res.getLong(computerTable + "." + computerIdColumn));
		
		computer.setName(res.getString(computerTable + "." + computerNameColumn));
		
		Timestamp introduced = res.getTimestamp(this.introduced);
		if (introduced != null) {
			computer.setIntroduced(introduced.toLocalDateTime());
		}
		
		Timestamp discontinued = res.getTimestamp(this.discontinued);
		if (discontinued != null) {
			computer.setDiscontinued(discontinued.toLocalDateTime());
		}
		
		Company company;
		long companyId = res.getLong(companyTable + "." + this.companyIdColumn);
		if (companyId > 0) {
			company = new Company();
			company.setId(companyId);
			String companyName = res.getString(companyTable + "." + this.companyNameColumn);
			if (companyName != null) {
				company.setName(companyName);
			}
			
			computer.setCompany(company);
		}
		
		return computer;
	}

}
