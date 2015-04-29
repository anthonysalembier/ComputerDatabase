package com.excilys.service;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.excilys.exception.ServiceException;
import com.excilys.model.Computer;
import com.excilys.repository.ComputerRepository;

@Service
@Transactional(rollbackFor=ServiceException.class)
public class ComputerService implements IComputerService {
	
	@Autowired
	private ComputerRepository repo;
	
	public List<Computer> getAll() {
		List<Computer> computers = new LinkedList<>();
		for (Computer c : repo.findAll()) {
			computers.add(c);
		}
		
		return computers;
	}

	public List<Computer> getAll(Pageable page) {
		if (page == null) {
			throw new ServiceException("Pageable argument is null");
		}
		
		List<Computer> computers = new LinkedList<>();
		for (Computer c : repo.findAll(page)) {
			computers.add(c);
		}
		
		return computers;
	}

	public Computer getById(long id) {
		if (id <= 0) {
			throw new ServiceException("Id is less than 0");
		}
		return repo.findOne(id);
	}
	
	public List<Computer> getByName(String name, Pageable page) {
		if (name == null || page == null) {
			throw new ServiceException("Argument is null");
		}
		
		List<Computer> computers = new LinkedList<>();
		for (Computer c : repo.findByNameContainingOrCompanyNameContaining(name, name, page)) {
			computers.add(c);
		}
		
		return computers;
	}

	public void create(Computer computer) {
		if (computer.getName().isEmpty()) {
			throw new ServiceException("Computer argument is null");
		}
		repo.save(computer);
	}

	public void update(Computer computer) {
		if (computer == null) {
			throw new ServiceException("Computer argument is null");
		}
		if (computer.getName().isEmpty()) {
			throw new ServiceException("Computer name is empty");
		}
		repo.save(computer);
	}

	public void delete(long id) {
		if (id <= 0) {
			throw new ServiceException("Id is less than 0");
		}
		repo.delete(id);
	}

	public long count() {
		return repo.count();
	}
	
	public int countByName(String name) {
		if (name == null) {
			throw new ServiceException("Argument is null");
		}
		return repo.countByNameContainingOrCompanyNameContaining(name, name);
	}
	
}
