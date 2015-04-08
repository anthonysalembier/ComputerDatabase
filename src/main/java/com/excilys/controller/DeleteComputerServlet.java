package com.excilys.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.excilys.service.ComputerService;

@Controller
public class DeleteComputerServlet extends HttpServlet {
	
	private static final long serialVersionUID = -117083031348700427L;
	
	@Autowired
	private ComputerService computerService;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		List<Long> selection = new ArrayList<>();
		for (String s : request.getParameter("selection").split(",")) {
			selection.add(Long.valueOf(s));
		}
		
		for (Long l : selection) {
			computerService.delete(l);
		}
		
		response.sendRedirect("dashboard");
	}

}
