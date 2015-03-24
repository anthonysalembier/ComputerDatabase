package com.excilys.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.assertj.core.api.Assertions.*;

import java.time.LocalDateTime;

import org.junit.Test;

public class ComputerTest {
	
	@Test
	public void setIdShouldReturnCorrectId() {
		//GIVEN
		final Computer computer = new Computer();
		final long computerId = 1L;
		
		//WHEN
		computer.setId(computerId);
		
		//THEN
		assertThat(computer.getId()).isEqualTo(computerId);
	}
	
	@Test
	public void testEquals() {
		String computerName = "computer";
		LocalDateTime introDate = LocalDateTime.now();
		LocalDateTime disDate = LocalDateTime.now();
		String companyName = "company";
		
		Company company = new Company();
		company.setName(companyName);
		
		Computer computer1 = new Computer();
		computer1.setName(computerName);
		computer1.setIntroduced(introDate);
		computer1.setDiscontinued(disDate);
		computer1.setCompany(company);
		
		Computer computer2 = new Computer();
		computer2.setName(computerName);
		computer2.setIntroduced(introDate);
		computer2.setDiscontinued(disDate);
		computer2.setCompany(company);
		
		Computer computer3 = new Computer();
		computer3.setName(computerName + "bis");
		computer3.setIntroduced(introDate);
		computer3.setDiscontinued(disDate);
		computer3.setCompany(company);
		
		assertTrue(computer1.equals(computer1));
		assertTrue(computer1.equals(computer2));
		assertTrue(computer2.equals(computer1));
		assertFalse(computer1.equals(computer3));
	}
	
	@Test
	public void testHashCode() {
		String computerName = "computer";
		LocalDateTime introDate = LocalDateTime.now();
		LocalDateTime disDate = LocalDateTime.now();
		String companyName = "company";
		
		Company company = new Company();
		company.setName(companyName);
		
		Computer computer1 = new Computer();
		computer1.setName(computerName);
		computer1.setIntroduced(introDate);
		computer1.setDiscontinued(disDate);
		computer1.setCompany(company);
		
		Computer computer2 = new Computer();
		computer2.setName(computerName);
		computer2.setIntroduced(introDate);
		computer2.setDiscontinued(disDate);
		computer2.setCompany(company);
		
		assertEquals(computer1.equals(computer1), (computer1.hashCode() == computer1.hashCode()));
		assertEquals(computer1.equals(computer2), (computer1.hashCode() == computer2.hashCode()));
	}

}
