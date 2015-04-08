package com.excilys.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.excilys.dao.ComputerDAO;
import com.excilys.model.Computer;
import com.excilys.util.Page;

@Service
public class ComputerService {
	
	@Autowired
	private ComputerDAO dao;

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
	
	public List<Computer> getByName(String name, Page page) {
		if (name == null) {
			throw new IllegalArgumentException();
		}
		return dao.getByName(name, page);
	}

	public void create(Computer computer) {
		if (computer.getName().isEmpty()) {
			throw new IllegalArgumentException();
		}
		dao.create(computer);
	}

	public void update(Computer computer) {
		if (computer == null) {
			throw new IllegalArgumentException();
		}
		if (computer.getName().isEmpty()) {
			throw new IllegalArgumentException();
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
	
	public int countByName(String name) {
		return dao.countByName(name);
	}
	
}
