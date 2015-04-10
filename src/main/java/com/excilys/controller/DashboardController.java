package com.excilys.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.WebRequest;

import com.excilys.mapper.ComputerDTOMapper;
import com.excilys.service.ComputerService;
import com.excilys.util.Page;
import com.excilys.util.SimplePage;

@Controller
public class DashboardController {
	
	@Autowired
	private ComputerService computerService;
	
	@RequestMapping(value="/dashboard", method=RequestMethod.GET)
	public String dashboard(WebRequest request, Model model) {
		
		String page = request.getParameter("page");		
		String size = request.getParameter("size");
        Page p;
        int currentPage = 1, entitiesByPage = 10, pge = 1;
        if (page != null) {
            page = page.trim();
            if (!page.isEmpty()) {
                currentPage = Integer.valueOf(page);
                pge = currentPage;
            }
        }
        if (size != null) {
            size = size.trim();
            if (!size.isEmpty()) {
                entitiesByPage = Integer.valueOf(size);
            }
        }
        p = new SimplePage(currentPage, entitiesByPage);
        
        String search = request.getParameter("search");
        final int totalEntities;
        if (search != null) {
        	totalEntities = computerService.countByName(search);
        } else {
        	totalEntities = computerService.count();
        }
        
        int maxPages = (totalEntities / entitiesByPage);
        if (totalEntities % entitiesByPage != 0
    		|| maxPages == 0) {
            maxPages++;
        }
        
        // Pages number
        model.addAttribute("totalPages", maxPages);
        maxPages = Math.min(maxPages, pge + entitiesByPage - 1);
        
        // The Page object
        model.addAttribute("page", p);
        
        // Elements number by page
        model.addAttribute("sizePage", entitiesByPage);
        
        // Max page buttons number
        model.addAttribute("maxPages", maxPages);
        
        // List of computers
        if (search != null) {
        	model.addAttribute("computers", ComputerDTOMapper.getComputerListDTO(computerService.getByName(search, p)));
        	model.addAttribute("search", search);
        } else {
        	model.addAttribute("computers", ComputerDTOMapper.getComputerListDTO(computerService.getAll(p)));
        }
        
        // The current page
        model.addAttribute("currentPage", pge);
        
        // Number of found entities
        model.addAttribute("total", totalEntities);
        
        return "dashboard";
	}
	
}
