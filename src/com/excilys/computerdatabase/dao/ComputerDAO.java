package com.excilys.computerdatabase.dao;

import java.util.List;

import com.excilys.computerdatabase.model.IComputer;

public enum ComputerDAO {
	INSTANCE;
	
	/**
	 * Get a list of all computers
	 * @return an ArrayList of IComputer
	 */
	public List<IComputer> getAllComputers() {
		// TODO
		return null;
	}
	
	/**
	 * Find a computer by its ID
	 * @param id
	 * @return an IComputer
	 */
	public IComputer getComputerById(int id) {
		// TODO
		return null;
	}
	
	/**
	 * Delete a computer
	 * @param id
	 */
	public void deleteComputer(int id) {
		// TODO
	}
	
	
	/**
	 * Add a computer
	 * @param computer
	 */
	public void addComputer(IComputer computer) {
		// TODO
	}
	
	
	/**
	 * Update informations of a computer
	 * @param computer
	 */
	public void updateComputer(IComputer computer) {
		// TODO
	}

}
