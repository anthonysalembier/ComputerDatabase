package com.excilys.service;

import com.excilys.exception.ServiceException;
import com.excilys.model.Company;
import com.excilys.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;

@Service
@Transactional(rollbackFor=ServiceException.class)
public class CompanyService implements ICompanyService {

	@Autowired
	private CompanyRepository repo;
	
	/**
	 * @return a list of all companies.
	 */
	public List<Company> getAll() {
		List<Company> companies = new LinkedList<>();
		for (Company c : repo.findAll()) {
			companies.add(c);
		}
		
		return companies;
	}
	
	/**
	 * @param id Identifier
	 * @return the company referenced by the identifier.
	 */
	public Company getById(Long id) {
		if (id < 0) {
			throw new ServiceException("Id less than 0");
		}
		return repo.findOne(id);
	}
	
	/**
	 * Delete the company referenced by the identifier.
	 * @param id Identifier
	 */
	@Transactional
	public void delete(Long id) {
		if (id <= 0) {
			throw new ServiceException("Id less than 0");
		}
		repo.delete(id);
	}

	/**
	 * @return the total number of companies.
	 */
	public long count() {
		return repo.count();
	}
}
