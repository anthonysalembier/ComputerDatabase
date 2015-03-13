package com.excilys.computerdatabase.model;

import java.time.LocalDate;

public interface IComputer {
	
public int getId();
	
	public String getName();
	
	public LocalDate getIntroducedDate();
	
	public LocalDate getDiscontinuedDate();
	
	public int getCompanyId();

	public void setId(int id);
	
	public void setName(String name);
	
	public void setIntroducedDate(LocalDate date);
	
	public void setDiscontinuedDate(LocalDate date);
	
	public void setCompanyId(int id);

}
