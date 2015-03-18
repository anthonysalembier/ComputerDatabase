package com.excilys.model;

public class Company {
	private long id;
	private String name;
	
	public long getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Company [id=" + id + ", name=" + name + "]";
	}
	
	@Override
	public int hashCode() {
		int hash = (int)(this.id ^ (this.id >>> 32));
		hash += this.name.hashCode();
		return 113 * 131 + hash;
	}
	
	public boolean equals(Company comp) {
		return this.id == comp.id
				&& this.name.equals(comp.name);
	}
}
