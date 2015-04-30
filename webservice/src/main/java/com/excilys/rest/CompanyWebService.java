package com.excilys.rest;

import com.excilys.dto.CompanyDTO;
import com.excilys.mapper.CompanyDTOMapper;
import com.excilys.service.ICompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/company")
public class CompanyWebService {

    @Autowired
    private ICompanyService companyService;
    @Autowired
    private CompanyDTOMapper companyDTOMapper;

    @RequestMapping("/get")
    public List<CompanyDTO> getAll() {
        return companyDTOMapper.getCompanyListDTO(companyService.getAll());
    }

    @RequestMapping("/{id}")
    public CompanyDTO getById(@PathVariable Long id) {
        return companyDTOMapper.getCompanyDTO(companyService.getById(id));
    }
}
