package com.excilys.service;

import java.util.List;

import com.excilys.dao.CompanyDAO;
import com.excilys.model.Company;

public enum CompanyService {
	INSTANCE;
	
	private CompanyDAO dao = CompanyDAO.INSTANCE;

	public List<Company> getAll() {
		return dao.getAll();
	}
	
	public Company getById(Long id) {
		return dao.getById(id);
	}

	public int count() {
		return dao.count();
	}
}
