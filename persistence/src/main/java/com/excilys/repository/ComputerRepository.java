package com.excilys.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.excilys.model.Computer;

public interface ComputerRepository extends PagingAndSortingRepository<Computer, Long> {
	
	public Page<Computer> findByNameContainingOrCompanyNameContaining(String name, String companyName, Pageable page);
	
	public int countByNameContainingOrCompanyNameContaining(String name, String companyName);

}
