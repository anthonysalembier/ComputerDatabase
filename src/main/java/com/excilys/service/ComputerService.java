package com.excilys.service;

import java.util.List;

import com.excilys.dao.ComputerDAO;
import com.excilys.exception.ServiceException;
import com.excilys.model.Computer;
import com.excilys.util.Page;

public enum ComputerService {
	INSTANCE;
	
	private ComputerDAO dao = ComputerDAO.INSTANCE;

	public List<Computer> getAll() {
		return dao.getAll();
	}

	public List<Computer> getAll(Page page) {
		if (page == null) {
			throw new IllegalArgumentException();
		}
		return dao.getAll(page);
	}

	public Computer getById(long id) {
		if (id <= 0) {
			throw new IllegalArgumentException();
		}
		return dao.getById(id);
	}

	public void create(Computer computer) {
		if (computer.getName().isEmpty()) {
			throw new ServiceException("Name can't be empty");
		}
		dao.create(computer);
	}

	public void update(Computer computer) {
		if (computer == null) {
			throw new IllegalArgumentException();
		}
		if (computer.getName().isEmpty()) {
			throw new ServiceException("Name can't be empty");
		}
		dao.update(computer);
	}

	public void delete(long id) {
		if (id <= 0) {
			throw new IllegalArgumentException();
		}
		dao.delete(id);
	}

	public int count() {
		return dao.count();
	}
	
}
