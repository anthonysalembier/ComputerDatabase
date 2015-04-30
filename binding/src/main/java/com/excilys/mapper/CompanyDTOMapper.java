package com.excilys.mapper;

import com.excilys.dto.CompanyDTO;
import com.excilys.model.Company;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CompanyDTOMapper {
	
	public  CompanyDTO getCompanyDTO (Company c) {
		return new CompanyDTO(String.valueOf(c.getId()), c.getName());
	}
	
	public List<CompanyDTO> getCompanyListDTO(List<Company> companies) {
		List<CompanyDTO> companiesDTO;
		
		companiesDTO = companies.stream()
						.map(this::getCompanyDTO)
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
