package com.excilys.computerdatabase.model;

import java.time.LocalDate;

public class Computer implements IComputer {
	
	private int id;
	private String name;
	private LocalDate introducedDate;
	private LocalDate discontinuedDate;
	private int companyId;
	
	public Computer(String name, LocalDate introducedDate, Company company) {
		// TODO
	}

	@Override
	public int getId() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LocalDate getIntroducedDate() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LocalDate getDiscontinuedDate() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getCompanyId() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setId(int id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setName(String name) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setIntroducedDate(LocalDate date) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setDiscontinuedDate(LocalDate date) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setCompanyId(int id) {
		// TODO Auto-generated method stub
		
	}
	
	public String toString() {
		String result = "Computer Id : " + id;
		result += "\nComputer name : " + name;
		result += "\nIntroduced date : " + introducedDate;
		if (discontinuedDate == null) {
			result += "\nDiscontinued date : none";
		} else {
			result += "\nDiscontinued date : " + discontinuedDate;
		}
		result += "\nComputer manufacturer id : " + companyId;
		
		return result;
	}

}
