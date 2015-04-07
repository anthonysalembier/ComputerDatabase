package com.excilys.service;

import java.util.List;

import com.excilys.dao.CompanyDAO;
import com.excilys.model.Company;

public enum CompanyService {
	INSTANCE;
	
	private CompanyDAO dao = CompanyDAO.INSTANCE;

	/**
	 * @return a list of all companies.
	 */
	public List<Company> getAll() {
		return dao.getAll();
	}
	
	/**
	 * @param id Identifier
	 * @return the company referenced by the identifier.
	 */
	public Company getById(Long id) {
		if (id <= 0) {
			throw new IllegalArgumentException();
		}
		return dao.getById(id);
	}
	
	/**
	 * Delete the company referenced by the identifier.
	 * @param id Identifier
	 */
	public void delete(Long id) {
		if (id <= 0) {
			throw new IllegalArgumentException();
		}
		dao.delete(id);
	}

	/**
	 * @return the total number of companies.
	 */
	public int count() {
		return dao.count();
	}
}
