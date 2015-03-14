package com.excilys.computerdatabase.model;

public class Company implements ICompany {
	
	private long id;
	private String name;
	
	public Company() {
		
	}
	
	public Company(long id, String name) {
		this.id = id;
		this.name= name;
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
	public void setId(long id) {
		this.id = id;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

}
