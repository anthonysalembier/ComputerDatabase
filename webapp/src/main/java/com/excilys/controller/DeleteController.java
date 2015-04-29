package com.excilys.controller;

import com.excilys.service.IComputerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class DeleteController {

	@Autowired
	private IComputerService computerService;

	@RequestMapping(value="/delete", method=RequestMethod.POST)
	public String delete(@RequestParam(required=false) String selection) {
		List<Long> idList = new ArrayList<>();
		for (String s : selection.split(",")) {
			idList.add(Long.valueOf(s));
		}

        idList.forEach(computerService::delete);

		return "redirect:dashboard";
	}

}
