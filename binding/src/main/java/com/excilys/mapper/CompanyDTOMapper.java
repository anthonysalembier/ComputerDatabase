package com.excilys.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.excilys.dto.CompanyDTO;
import com.excilys.model.Company;

@Component
public class CompanyDTOMapper {
	
	public static CompanyDTO getCompanyDTO (Company c) {
		return new CompanyDTO(String.valueOf(c.getId()), c.getName());
	}
	
	public List<CompanyDTO> getCompanyListDTO(List<Company> companies) {
		List<CompanyDTO> companiesDTO;
		
		companiesDTO = companies.stream()
						.map(CompanyDTOMapper::getCompanyDTO)
						.collect(Collectors.toList());
		
		return companiesDTO;
	}
	
	public Company getCompany(CompanyDTO companyDTO) {
		Company company = new Company();
		company.setId(Long.valueOf(companyDTO.getId()));
		company.setName(companyDTO.getName());
		
		return company;
	}
	
	public List<Company> getCompanyList(List<CompanyDTO> companyDTOs) {
		List<Company> companies;
		
		companies = companyDTOs.stream()
					.map(this::getCompany)
					.collect(Collectors.toList());
		
		return companies;
	}

}
