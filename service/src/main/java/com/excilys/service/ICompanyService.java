package com.excilys.service;

import com.excilys.model.Company;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ICompanyService {

    List<Company> getAll();

    /**
     * @param id Identifier
     * @return the company referenced by the identifier.
     */
    Company getById(Long id);

    /**
     * Delete the company referenced by the identifier.
     * @param id Identifier
     */
    @Transactional
    void delete(Long id);

    /**
     * @return the total number of companies.
     */
    long count();
}
