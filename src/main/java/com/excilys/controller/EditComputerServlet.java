package com.excilys.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.excilys.service.ComputerService;

public class EditComputerServlet extends HttpServlet {
	
	private static final long serialVersionUID = -4527396020461013483L;
	
	private ComputerService computerService = ComputerService.INSTANCE;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Long id = Long.valueOf(request.getParameter("id"));
		request.setAttribute("computer", computerService.getById(id));
		request.getServletContext().getRequestDispatcher("/WEB-INF/views/editComputer.jsp").forward(request, response);
	}
}
