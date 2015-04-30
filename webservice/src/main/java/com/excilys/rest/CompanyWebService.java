package com.excilys.rest;

import com.excilys.dto.CompanyDTO;
import com.excilys.mapper.CompanyDTOMapper;
import com.excilys.service.ICompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyWebService implements ICompanyWebService {

    @Autowired
    private ICompanyService companyService;
    @Autowired
    private CompanyDTOMapper companyDTOMapper;

    @Override
    public List<CompanyDTO> getAll() {
        return companyDTOMapper.getCompanyListDTO(companyService.getAll());
    }

    @Override
    public CompanyDTO getById(Long id) {
        return companyDTOMapper.getCompanyDTO(companyService.getById(id));
    }
}
