package com.excilys.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.excilys.model.Company;

public interface CompanyRepository extends PagingAndSortingRepository<Company, Long> {
	
}
