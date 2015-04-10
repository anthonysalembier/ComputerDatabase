package com.excilys.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/edit")
public class UpdateController {
	
	@RequestMapping(method = RequestMethod.POST)
	public String edit(String name, String introduced, String discontinued, String companyId) {
		
		return "dashboard";
	}

}
