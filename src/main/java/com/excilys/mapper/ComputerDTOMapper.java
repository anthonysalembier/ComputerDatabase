package com.excilys.mapper;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

import com.excilys.dto.ComputerDTO;
import com.excilys.model.Computer;

public class ComputerDTOMapper {

	public static ComputerDTO getComputerDTO(Computer c) {
		ComputerDTO computer = new ComputerDTO();
		
		computer.setId(String.valueOf(c.getId()));
		
		computer.setName(c.getName());
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yy");
		
		LocalDateTime introDate = c.getIntroduced();
		if (introDate != null) {
			String introduced = introDate.format(formatter);
			computer.setIntroduced(introduced.toString());
		} else {
			computer.setIntroduced("");
		}
		
		LocalDateTime disDate = c.getDiscontinued();
		if (disDate != null) {
			String discontinued = c.getDiscontinued().format(formatter);
			computer.setDiscontinued(discontinued);
		} else {
			computer.setDiscontinued("");
		}
		
		if (c.getCompany() != null) {
			computer.setCompanyId(String.valueOf(c.getCompany().getId()));
			computer.setCompanyName(c.getCompany().getName());
		} else {
			computer.setCompanyId("");
			computer.setCompanyName("");
		}
		
		return computer;
	}
	
	public static List<ComputerDTO> getComputerListDTO(List<Computer> computers) {
		List<ComputerDTO> dTOComputers;
		
		dTOComputers = computers.stream()
				.map(ComputerDTOMapper::getComputerDTO)
				.collect(Collectors.toList());
		
		return dTOComputers;
	}
}
