package com.excilys.service;

import com.excilys.exception.ServiceException;
import com.excilys.model.Company;
import com.excilys.repository.CompanyRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(rollbackFor=ServiceException.class)
public class CompanyService implements ICompanyService {

	@Autowired
	private CompanyRepository repo;

    private static final Logger LOGGER = LoggerFactory.getLogger(CompanyService.class);
	
	/**
	 * @return a list of all companies.
	 */
	public List<Company> getAll() {
        LOGGER.info("Getting all companies");
		return repo.findAll();
	}
	
	/**
	 * @param id Identifier
	 * @return the company referenced by the identifier.
	 */
	public Company getById(Long id) {
        LOGGER.info("Getting company by ID : {}", id);
		if (id <= 0) {
            LOGGER.error("ID less than 0");
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
        LOGGER.info("Deleting company by ID : {}", id);
		if (id <= 0) {
            LOGGER.error("ID less than 0");
			throw new ServiceException("Id less than 0");
		}
		repo.delete(id);
	}

	/**
	 * @return the total number of companies.
	 */
	public long count() {
        LOGGER.info("Counting companies");
        return repo.count();
	}
}
