package com.excilys.mapper;

import java.io.IOException;
import java.io.InputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.excilys.dao.CompanyDAO;
import com.excilys.model.Company;
import com.excilys.model.Computer;

public class ComputerMapper implements Mapper<Computer> {

	private Properties properties;

	private String computer;
	private String company;
	private String computerId;
	private String companyId;
	private String computerName;
	private String companyName;
	private String introduced;
	private String discontinued;
	private String computerCompanyId;

	public ComputerMapper() {
		properties = new Properties();

		try (InputStream input = CompanyDAO.class.getClassLoader()
				.getResourceAsStream("tableConfig.properties")) {
			properties.load(input);
			computer = properties.getProperty("computer");
			company = properties.getProperty("company");
			computerId = properties.getProperty("computerId");
			companyId = properties.getProperty("companyId");
			computerName = properties.getProperty("computerName");
			companyName = properties.getProperty("companyName");
			introduced = properties.getProperty("introduced");
			discontinued = properties.getProperty("discontinued");
			computerCompanyId = properties.getProperty("computerCompanyId");

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
		
		computer.setId(res.getLong("cpt." + computerId));
		
		computer.setName(res.getString("cpt." + computerName));
		
		Timestamp introduced = res.getTimestamp("introduced");
		if (introduced != null) {
			computer.setIntroduced(introduced.toLocalDateTime());
		}
		
		Timestamp discontinued = res.getTimestamp("discontinued");
		if (discontinued != null) {
			computer.setDiscontinued(discontinued.toLocalDateTime());
		}
		
		Company company;
		long companyId = res.getLong("cpy." + this.companyId);
		if (companyId > 0) {
			company = new Company();
			company.setId(companyId);
			String companyName = res.getString("cpy." + this.companyName);
			if (companyName != null) {
				company.setName(companyName);
			}
			
			computer.setCompany(company);
		}
		
		return computer;
	}

}
