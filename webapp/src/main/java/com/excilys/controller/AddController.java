package com.excilys.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.excilys.dto.CompanyDTO;
import com.excilys.dto.ComputerDTO;
import com.excilys.mapper.CompanyDTOMapper;
import com.excilys.mapper.ComputerDTOMapper;
import com.excilys.service.CompanyService;
import com.excilys.service.ComputerService;


@Controller
public class AddController {
	
	@Autowired
	private CompanyService companyService;
	@Autowired
	private ComputerService computerService;
	@Autowired
	private ComputerDTOMapper computerMapper;
	@Autowired
	private CompanyDTOMapper companyMapper;
	
	@RequestMapping(value="/add", method=RequestMethod.GET)
	public String createForm(Model model) {
		List<CompanyDTO> companies = companyMapper.getCompanyListDTO(companyService.getAll());
		model.addAttribute("companies", companies);
		return "addComputer";
	}
	
	@RequestMapping(value="/add", method=RequestMethod.POST)
	public String add(ModelMap model, ComputerDTO computerDTO, BindingResult result) {
		
		if(result.hasErrors()) {
			model.addAttribute("companies", companyMapper.getCompanyListDTO(companyService.getAll()));
			model.addAttribute("computer", computerDTO);
			return "add";
		}
		
		if (!computerDTO.getCompanyId().isEmpty() && Long.valueOf(computerDTO.getCompanyId()) > 0) {
			computerDTO.setCompanyName(companyService.getById(Long.valueOf(computerDTO.getCompanyId())).getName());
		}
		
		computerService.create(computerMapper.getComputer(computerDTO));
		
		return "redirect:/dashboard";
	}
	
	
}
