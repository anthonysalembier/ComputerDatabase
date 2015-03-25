package com.excilys.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.excilys.mapper.ComputerDTOMapper;
import com.excilys.service.ComputerService;
import com.excilys.util.Page;
import com.excilys.util.SimplePage;

/**
 * Servlet implementation class ServletController
 */
public class DashboardServlet extends HttpServlet {

	private static final long serialVersionUID = 110834506145935418L;
	
	private ComputerService computerService = ComputerService.INSTANCE;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
        final int totalEntities = computerService.count();
        int maxPages = (totalEntities / entitiesByPage);
        if (totalEntities % entitiesByPage != 0) {
            ++maxPages;
        }
        
        // Pages number
        request.setAttribute("totalPages", maxPages);
        maxPages = Math.min(maxPages, pge + entitiesByPage - 1);
        
        // The Page object
        request.setAttribute("page", p);
        
        // Elements number by page
        request.setAttribute("sizePage", entitiesByPage);
        
        // Max page buttons number
        request.setAttribute("maxPages", maxPages);
        
        // List of computers
        request.setAttribute("computers", ComputerDTOMapper.getComputerListDTO(computerService.getAll(p)));
        
        // The current page
        request.setAttribute("currentPage", pge);
        
        // Number of found entities
        request.setAttribute("total", totalEntities);
        getServletContext().getRequestDispatcher("/WEB-INF/views/dashboard.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
