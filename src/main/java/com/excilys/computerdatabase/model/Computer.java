package com.excilys.computerdatabase.model;

import java.sql.Timestamp;
import java.time.LocalDate;

public class Computer implements IComputer {
	
	private long id;
	private String name;
	private Timestamp introducedDate;
	private Timestamp discontinuedDate;
	private long companyId;

    public Computer() {

    }

    public Computer(long id, String name, Timestamp introducedDate, long companyId) {
        this.id = id;
        this.name = name;
        this.introducedDate = introducedDate;
        this.companyId = companyId;
    }

	public Computer(long id, String name, Timestamp introducedDate, Timestamp discontinuedDate, long companyId) {
		this.id = id;
        this.name = name;
        this.introducedDate = introducedDate;
        this.discontinuedDate = discontinuedDate;
        this.companyId = companyId;
	}

	@Override
	public long getId() {
		return id;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public Timestamp getIntroducedDate() {
		return introducedDate;
	}

	@Override
	public Timestamp getDiscontinuedDate() {
		return discontinuedDate;
	}

	@Override
	public long getCompanyId() {
		return companyId;
	}

	@Override
	public void setId(long id) {
		this.id = id;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public void setIntroducedDate(Timestamp date) {
		this.introducedDate = date;
	}

	@Override
	public void setDiscontinuedDate(Timestamp date) {
		this.discontinuedDate = date;
	}

	@Override
	public void setCompanyId(long id) {
		this.companyId = id;
	}
	
	public String toString() {
		StringBuilder result = new StringBuilder(150);
		
		result.append("Computer [id:");
		result.append(id);
		result.append(" ; name:");
		result.append(name);
		result.append(" ; introducedDate:");
		result.append(introducedDate);
		result.append(" ; discontinuedDate:");
		result.append(discontinuedDate);
		result.append(" ; companyId:");
		result.append(companyId);
		result.append("]");
		
		return result.toString();
	}

}
