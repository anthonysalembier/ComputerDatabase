package com.excilys.controller;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.excilys.dto.CompanyDTO;
import com.excilys.dto.ComputerDTO;
import com.excilys.mapper.CompanyDTOMapper;
import com.excilys.mapper.ComputerDTOMapper;
import com.excilys.model.Company;
import com.excilys.model.Computer;
import com.excilys.service.CompanyService;
import com.excilys.service.ComputerService;

@Controller
public class EditComputerServlet extends HttpServlet {
	
	private static final long serialVersionUID = -4527396020461013483L;
	
	private static final String DEFAULT_TIME = " 00:00:00";
	private static final String DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";
	
	@Autowired
	private ComputerService computerService;
	@Autowired
	private CompanyService companyService;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		Long id = Long.valueOf(request.getParameter("id"));
		
		ComputerDTO computer = ComputerDTOMapper.getComputerDTO(computerService.getById(id));
		request.setAttribute("computer", computer);
		
		List<CompanyDTO> companies = CompanyDTOMapper.getCompanyListDTO(companyService.getAll());
		request.setAttribute("companies", companies);
		
		request.getServletContext().getRequestDispatcher("/WEB-INF/views/editComputer.jsp").forward(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Computer computer = new Computer();
		
		computer.setId(Long.valueOf(request.getParameter("computerId")));
		
		computer.setName(request.getParameter("computerName"));
		
		LocalDateTime introducedDate = null;
		String introducedString = request.getParameter("introduced");
		if ((introducedString != null) && (!introducedString.isEmpty())) {
			introducedString += DEFAULT_TIME;
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_TIME_PATTERN);
			introducedDate = LocalDateTime.parse(introducedString, formatter);
		}
		computer.setIntroduced(introducedDate);
		
		LocalDateTime discontinuedDate = null;
		String disconstinuedString = request.getParameter("discontinued");
		if ((disconstinuedString != null) && (!disconstinuedString.isEmpty())) {
			disconstinuedString += DEFAULT_TIME;
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_TIME_PATTERN);
			discontinuedDate = LocalDateTime.parse(disconstinuedString, formatter);
		}
		computer.setDiscontinued(discontinuedDate);
		Company company = companyService.getById(Long.valueOf(request.getParameter("companyId")));
		computer.setCompany(company);
		
		computerService.update(computer);
		
		response.sendRedirect("dashboard");
	}
}
