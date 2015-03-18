package com.excilys.service;

import java.util.List;

import com.excilys.dao.CompanyDAO;
import com.excilys.exception.DAOException;
import com.excilys.exception.ServiceException;
import com.excilys.model.Company;

public enum CompanyService {
	INSTANCE;

	public List<Company> getAll() throws ServiceException {
		try {
			return CompanyDAO.INSTANCE.getAll();
		} catch (DAOException e) {
			throw new ServiceException(e.getMessage());
		}
	}
}
