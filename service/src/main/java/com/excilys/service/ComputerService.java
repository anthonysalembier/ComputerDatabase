package com.excilys.service;

import com.excilys.exception.ServiceException;
import com.excilys.model.Computer;
import com.excilys.repository.ComputerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;

@Service
@Transactional(rollbackFor=ServiceException.class)
public class ComputerService implements IComputerService {

	@Autowired
	private ComputerRepository repo;

    private Logger LOGGER = LoggerFactory.getLogger(ComputerService.class);
	
	public List<Computer> getAll() {
        LOGGER.info("Getting all computers");
		return repo.findAll();
	}

	public List<Computer> getAll(Pageable page) {
        LOGGER.info("Getting all computers with pageable");
		if (page == null) {
            LOGGER.error("Page is null");
			throw new ServiceException("Pageable argument is null");
		}
		
		List<Computer> computers = new LinkedList<>();
		for (Computer c : repo.findAll(page)) {
			computers.add(c);
		}
		
		return computers;
	}

	public Computer getById(long id) {
        LOGGER.info("Getting computer by ID");
		if (id <= 0) {
            LOGGER.error("ID less than 0");
			throw new ServiceException("Id is less than 0");
		}
		return repo.findOne(id);
	}
	
	public List<Computer> getByName(String name, Pageable page) {
        LOGGER.info("Getting computer by name");
		if (name == null || page == null) {
            if (name == null) {
                LOGGER.error("Name is null");
            } else  {
                LOGGER.error("Page is null");
            }
			throw new ServiceException("Argument is null");
		}
		
		List<Computer> computers = new LinkedList<>();
		for (Computer c : repo.findByNameContainingOrCompanyNameContaining(name, name, page)) {
			computers.add(c);
		}
		
		return computers;
	}

	public void create(Computer computer) {
        LOGGER.info("Creating computer");
		if (computer.getName().isEmpty()) {
            LOGGER.error("Computer's name is empty");
			throw new ServiceException("Computer argument is null");
		}
		repo.save(computer);
	}

	public void update(Computer computer) {
        LOGGER.info("Updating computer");
		if (computer == null) {
            LOGGER.error("Computer is null");
			throw new ServiceException("Computer argument is null");
		}
		if (computer.getName().isEmpty()) {
            LOGGER.error("Computer's name is empty");
			throw new ServiceException("Computer name is empty");
		}
		repo.save(computer);
	}

	public void delete(long id) {
        LOGGER.info("Deleting computer by ID : {}", id);
		if (id <= 0) {
            LOGGER.error("ID less than 0");
			throw new ServiceException("Id is less than 0");
		}
		repo.delete(id);
	}

	public long count() {
        LOGGER.info("Counting computers");
        return repo.count();
	}
	
	public int countByName(String name) {
        LOGGER.info("Couting computer by name");
		if (name == null) {
            LOGGER.error("Computer's name si null");
			throw new ServiceException("Argument is null");
		}
		return repo.countByNameContainingOrCompanyNameContaining(name, name);
	}
	
}
