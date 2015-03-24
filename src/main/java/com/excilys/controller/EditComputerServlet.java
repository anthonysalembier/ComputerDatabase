package com.excilys.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.excilys.exception.ServiceException;
import com.excilys.service.ComputerService;

public class EditComputerServlet extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Long id = Long.valueOf(request.getParameter("id"));
		try {
			request.setAttribute("computer", ComputerService.INSTANCE.getById(id));
		} catch (ServiceException e) {
			new ServiceException("");
		}
		request.getServletContext().getRequestDispatcher("/WEB-INF/views/editComputer.jsp").forward(request, response);
	}
}
