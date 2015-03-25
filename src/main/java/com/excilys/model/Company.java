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
		StringBuffer result = new StringBuffer();
		result.append("Company [id=").append(id).append(", name=").append(name).append("]");
		return result.toString();
	}
	
	@Override
	public int hashCode() {
		final int prime = 113;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Company other = (Company) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
}
