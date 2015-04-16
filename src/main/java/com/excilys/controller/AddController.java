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
import com.excilys.mapper.CompanyDTOMapper;
import com.excilys.model.Company;
import com.excilys.model.Computer;
import com.excilys.service.CompanyService;
import com.excilys.service.ComputerService;


@Controller
public class AddController {
	
	private static final String DEFAULT_TIME = " 00:00:00";
	private static final String DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";
	
	@Autowired
	private CompanyService companyService;
	@Autowired
	private ComputerService computerService;
	
	@RequestMapping(value="/add", method=RequestMethod.GET)
	public String createForm(Model model) {
		List<CompanyDTO> companies = CompanyDTOMapper.getCompanyListDTO(companyService.getAll());
		model.addAttribute("companies", companies);
		return "addComputer";
	}
	
	
	
	@RequestMapping(value="/add", method=RequestMethod.POST)
	public String add(@RequestParam(required=true) String computerName,
					  @RequestParam(required=false) String introduced,
					  @RequestParam(required=false) String discontinued,
					  @RequestParam(required=false) String companyId) {
		Computer computer = new Computer();
		
		computer.setName(computerName);
		
		LocalDateTime introducedDate = null;
		if ((introduced != null) && (!introduced.isEmpty())) {
			introduced += DEFAULT_TIME;
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_TIME_PATTERN);
			introducedDate = LocalDateTime.parse(introduced, formatter);
		}
		computer.setIntroduced(introducedDate);
		
		LocalDateTime discontinuedDate = null;
		if ((discontinued != null) && (!discontinued.isEmpty())) {
			discontinued += DEFAULT_TIME;
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_TIME_PATTERN);
			discontinuedDate = LocalDateTime.parse(discontinued, formatter);
		}
		computer.setDiscontinued(discontinuedDate);
		
		Company company = companyService.getById(Long.valueOf(companyId));
		computer.setCompany(company);
		
		computerService.create(computer);
		
		return "redirect:dashboard";
		
	}
	
	
}
