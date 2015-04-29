package com.excilys.rest;

import com.excilys.model.Computer;
import com.excilys.service.IComputerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ComputerWebService implements IComputerWebService {
	
	@Autowired
	private IComputerService computerService;

    @Override
	public List<Computer> getAll() {
		return computerService.getAll();
	}

    @Override
    public Computer getById(Long id) {
        return computerService.getById(id);
    }

}
