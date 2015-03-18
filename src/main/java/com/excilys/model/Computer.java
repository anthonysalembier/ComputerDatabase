package com.excilys.model;

import java.time.LocalDateTime;

public class Computer {
	private long id;
	private String name;
	private LocalDateTime introduced;
	private LocalDateTime discontinued;
	private Company company;
	
	public long getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public LocalDateTime getIntroduced() {
		return introduced;
	}
	
	public LocalDateTime getDiscontinued() {
		return discontinued;
	}
	
	
	public Company getCompany() {
		return company;
	}

	public void setId(long id) {
		this.id = id;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public void setIntroduced(LocalDateTime introduced) {
		this.introduced = introduced;
	}
	
	public void setDiscontinued(LocalDateTime discontinued) {
		this.discontinued = discontinued;
	}
	
	public void setCompany(Company company) {
		this.company = company;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Computer [id=");
		builder.append(id);
		builder.append(", name=");
		builder.append(name);
		builder.append(", introduced=");
		builder.append(introduced);
		builder.append(", discontinued=");
		builder.append(discontinued);
		builder.append(", company=");
		builder.append(company);
		builder.append("]");
		return builder.toString();
	}

}
