package com.excilys.repository;

import com.excilys.model.Company;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface CompanyRepository extends PagingAndSortingRepository<Company, Long> {
	List<Company> findAll();
}
