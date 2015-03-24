package com.excilys.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.excilys.exception.ServiceException;
import com.excilys.model.Computer;
import com.excilys.service.ComputerService;

/**
 * Servlet implementation class ServletController
 */
public class DashboardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public DashboardServlet() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Computer> computers = null;
		
		try {
			computers = ComputerService.INSTANCE.getAll();
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		
		request.getServletContext().setAttribute("computers", computers);
		request.getServletContext().getRequestDispatcher("/WEB-INF/views/dashboard.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
