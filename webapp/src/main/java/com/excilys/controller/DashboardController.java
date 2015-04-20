package com.excilys.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.excilys.mapper.ComputerDTOMapper;
import com.excilys.service.ComputerService;

@Controller
public class DashboardController {
	
	@Autowired
	private ComputerService computerService;
	
	@RequestMapping(value="/dashboard", method=RequestMethod.GET)
	public String dashboard(@RequestParam(required=false, defaultValue="1") int page,
							@RequestParam(required=false, defaultValue="") String search,
							@RequestParam(required=false, defaultValue="10") int size,
							Model model) {
		
		// Creating Pageable object
        Pageable p;
        
        p = new PageRequest(page - 1, size);
        
        // Getting number of entities
        final int totalEntities;
        if (search != null && !search.isEmpty()) {
        	totalEntities = computerService.countByName(search);
        } else {
        	totalEntities = (int) computerService.count();
        }
        
        // Getting number of pages
        int maxPages = (totalEntities / size);
        if (totalEntities % size != 0 || maxPages == 0) {
            maxPages++;
        }
        
        // Pages number
        model.addAttribute("totalPages", maxPages);
        maxPages = Math.min(maxPages, page + size - 1);
        
        // The Page object
        model.addAttribute("page", p);
        
        // Elements number by page
        model.addAttribute("sizePage", size);
        
        // Max page buttons number
        model.addAttribute("maxPages", maxPages);
        
        // List of computers
        if (!search.isEmpty()) {
        	model.addAttribute("computers", ComputerDTOMapper.getComputerListDTO(computerService.getByName(search, p)));
        	model.addAttribute("search", search);
        } else {
        	model.addAttribute("computers", ComputerDTOMapper.getComputerListDTO(computerService.getAll(p)));
        }
        
        // The current page
        model.addAttribute("currentPage", page);
        
        // Number of found entities
        model.addAttribute("total", totalEntities);
        
        return "dashboard";
	}
	
//	@RequestMapping(value="/dashboard", method=RequestMethod.GET)
//	public String changeLanguage() {
//		return "dashboard";
//	}
	
}
