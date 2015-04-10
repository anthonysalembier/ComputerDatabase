package com.excilys.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.excilys.dto.CompanyDTO;
import com.excilys.dto.ComputerDTO;
import com.excilys.mapper.CompanyDTOMapper;
import com.excilys.mapper.ComputerDTOMapper;
import com.excilys.model.Company;
import com.excilys.model.Computer;
import com.excilys.service.CompanyService;
import com.excilys.service.ComputerService;

@Controller
public class UpdateController {
	
	private static final String DEFAULT_TIME = " 00:00:00";
	private static final String DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";
	
	@Autowired
	private ComputerService computerService;
	@Autowired
	private CompanyService companyService;
	
	@RequestMapping(value="/edit", method=RequestMethod.GET)
	public String editForm(@RequestParam(required=false) String id, Model model) {
		
		ComputerDTO computer = ComputerDTOMapper.getComputerDTO(computerService.getById(Long.valueOf(id)));
		model.addAttribute("computer", computer);
		
		List<CompanyDTO> companies = CompanyDTOMapper.getCompanyListDTO(companyService.getAll());
		model.addAttribute("companies", companies);
		
		return("editComputer");
	}
	
	@RequestMapping(value="/edit", method=RequestMethod.POST)
	public String edit(@RequestParam(required=false) String computerId,
					   @RequestParam(required=true) String computerName,
					   @RequestParam(required=false) String introduced,
					   @RequestParam(required=false) String discontinued,
					   @RequestParam(required=false) String companyId) {
		
		Computer computer = new Computer();
		
		computer.setId(Long.valueOf(computerId));
		
		computer.setName(computerName);
		
		LocalDateTime introducedDate = null;
		String introducedString = introduced;
		if ((introducedString != null) && (!introducedString.isEmpty())) {
			introducedString += DEFAULT_TIME;
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_TIME_PATTERN);
			introducedDate = LocalDateTime.parse(introducedString, formatter);
		}
		computer.setIntroduced(introducedDate);
		
		LocalDateTime discontinuedDate = null;
		String disconstinuedString = discontinued;
		if ((disconstinuedString != null) && (!disconstinuedString.isEmpty())) {
			disconstinuedString += DEFAULT_TIME;
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_TIME_PATTERN);
			discontinuedDate = LocalDateTime.parse(disconstinuedString, formatter);
		}
		computer.setDiscontinued(discontinuedDate);
		Company company = companyService.getById(Long.valueOf(companyId));
		computer.setCompany(company);
		
		computerService.update(computer);
		
		return "redirect:dashboard";
	}

}
