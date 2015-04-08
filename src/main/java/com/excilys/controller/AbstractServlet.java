package com.excilys.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.springframework.web.context.support.SpringBeanAutowiringSupport;

public class AbstractServlet extends HttpServlet {
	
	private static final long serialVersionUID = 3889561368056061273L;

	@Override
	public void init() throws ServletException {
		super.init();
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
	}

}
