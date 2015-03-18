package com.excilys.service;

import java.util.List;

import com.excilys.dao.ComputerDAO;
import com.excilys.exception.DAOException;
import com.excilys.exception.ServiceException;
import com.excilys.model.Computer;
import com.excilys.util.SimplePage;

public enum ComputerService {
	INSTANCE;
	
	public List<Computer> getAll() throws ServiceException {
		try {
			return ComputerDAO.INSTANCE.getAll();
		} catch (DAOException e) {
			throw new ServiceException(e.getMessage());
		}
	}
	
	public List<Computer> getAll(SimplePage page) throws ServiceException {
		if (page == null) {
			throw new IllegalArgumentException();
		}
		try {
			return ComputerDAO.INSTANCE.getAll(page);
		} catch (DAOException e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public Computer getById(long id) throws ServiceException {
		if (id <= 0) {
			throw new IllegalArgumentException();
		}
		try {
			return ComputerDAO.INSTANCE.getById(id);
		} catch (DAOException e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public void create(Computer computer) throws ServiceException {
		if (computer == null) {
			throw new IllegalArgumentException();
		}
		try {
			ComputerDAO.INSTANCE.create(computer);
		} catch (DAOException e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public void update(Computer computer) throws ServiceException {
		if (computer == null) {
			throw new IllegalArgumentException();
		}
		try {
			ComputerDAO.INSTANCE.update(computer);
		} catch (DAOException e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public void delete(long id) throws ServiceException {
		if (id <= 0) {
			throw new IllegalArgumentException();
		}
		try {
			ComputerDAO.INSTANCE.delete(id);
		} catch (DAOException e) {
			throw new ServiceException(e.getMessage());
		}
	}
}
