package com.excilys.mapper;

import java.util.List;
import java.util.stream.Collectors;

import com.excilys.dto.CompanyDTO;
import com.excilys.model.Company;

public class CompanyDTOMapper {
	
	public static CompanyDTO getCompanyDTO (Company c) {
		return new CompanyDTO(String.valueOf(c.getId()), c.getName());
	}
	
	public static List<CompanyDTO> getCompanyListDTO(List<Company> companies) {
		List<CompanyDTO> companiesDTO;
		
		companiesDTO = companies.stream()
			.map(CompanyDTOMapper::getCompanyDTO)
			.collect(Collectors.toList());
		
		return companiesDTO;
	}

}
