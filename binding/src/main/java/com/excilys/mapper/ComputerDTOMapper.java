package com.excilys.mapper;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import com.excilys.dto.ComputerDTO;
import com.excilys.model.Company;
import com.excilys.model.Computer;

@Component
public class ComputerDTOMapper {
	
	private static final String DEFAULT_TIME = " 00:00:00";
	
	private static final String DATE_PATTERN_LONG = "pattern.localdatetime.long";
	private static final String DATE_PATTERN_SHORT = "pattern.localdatetime.short";
	
	@Autowired
	private MessageSource messageSource;

	public ComputerDTO getComputerDTO(Computer c) {
		ComputerDTO computer = new ComputerDTO();
		
		computer.setId(String.valueOf(c.getId()));
		
		computer.setName(c.getName());
		
		String pattern = messageSource.getMessage(DATE_PATTERN_SHORT, null, LocaleContextHolder.getLocale());
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
		
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
	
	public List<ComputerDTO> getComputerListDTO(List<Computer> computers) {
		List<ComputerDTO> dTOComputers;
		
		dTOComputers = computers.stream()
						.map(this::getComputerDTO)
						.collect(Collectors.toList());
		
		return dTOComputers;
	}
	
	public Computer getComputer(ComputerDTO computerDTO) {
		Computer computer = new Computer();
		
		if (computerDTO.getId() != null && !computerDTO.getId().isEmpty() && Long.valueOf(computerDTO.getId()) > 0) {
			computer.setId(Long.valueOf(computerDTO.getId()));
		}
		
		computer.setName(computerDTO.getName());
		
		String pattern = messageSource.getMessage(DATE_PATTERN_LONG, null, LocaleContextHolder.getLocale());
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
		
		LocalDateTime introducedDate = null;
		String introducedString = computerDTO.getIntroduced();
		if ((introducedString != null) && (!introducedString.isEmpty())) {
			introducedString += DEFAULT_TIME;
			
			introducedDate = LocalDateTime.parse(introducedString, formatter);
		}
		computer.setIntroduced(introducedDate);
		
		LocalDateTime discontinuedDate = null;
		String discontinuedString = computerDTO.getDiscontinued();
		if ((discontinuedString != null) && (!discontinuedString.isEmpty())) {
			discontinuedString += DEFAULT_TIME;
			
			discontinuedDate = LocalDateTime.parse(discontinuedString, formatter);
		}
		computer.setDiscontinued(discontinuedDate);
		
		if (!computerDTO.getCompanyId().isEmpty() && Long.valueOf(computerDTO.getCompanyId()) > 0) {
			Company company = new Company();
			company.setId(Long.valueOf(computerDTO.getCompanyId()));
			company.setName(computerDTO.getCompanyName());
			computer.setCompany(company);
		}
		
		return computer;
	}
	
	public List<Computer> getComputerList(List<ComputerDTO> computerDTOs) {
		List<Computer> computers;
		
		computers = computerDTOs.stream()
					.map(this::getComputer)
					.collect(Collectors.toList());
		
		return computers;
	}
}
