package com.excilys.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.stereotype.Component;

import com.excilys.model.Company;

@Component
public class CompanyMapper implements Mapper<Company> {

	@Override
	public Company rowMap(ResultSet res) throws SQLException {
		if (res == null) {
			throw new IllegalArgumentException();
		}
		final Company company = new Company();
		company.setId(res.getLong("id"));
		company.setName(res.getString("name"));
		return company;
	}

}
