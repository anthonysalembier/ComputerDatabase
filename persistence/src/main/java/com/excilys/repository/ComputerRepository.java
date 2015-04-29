package com.excilys.repository;

import com.excilys.model.Computer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ComputerRepository extends PagingAndSortingRepository<Computer, Long> {
	
	Page<Computer> findByNameContainingOrCompanyNameContaining(String name, String companyName, Pageable page);
	
	int countByNameContainingOrCompanyNameContaining(String name, String companyName);

}
