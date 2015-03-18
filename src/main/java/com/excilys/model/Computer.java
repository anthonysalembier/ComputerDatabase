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
	
	@Override
	public int hashCode() {
		int hash = (int)(this.id ^ (this.id >>> 32));
		hash += this.name.hashCode();
		hash += this.introduced.hashCode();
		hash += this.discontinued.hashCode();
		hash += this.company.hashCode();
		return 113 * 131 + hash;
	}
	
	public boolean equals(Computer comp) {
		return this.name.equals(comp.name)
				&& this.introduced.equals(comp.introduced)
				&& this.discontinued.equals(comp.discontinued)
				&& this.company.equals(comp.company);
	}

}
