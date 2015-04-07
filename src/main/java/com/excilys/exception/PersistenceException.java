package com.excilys.exception;

public class PersistenceException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 783762366111417303L;

	public PersistenceException(String message) {
		super(message);
	}
	
	public PersistenceException(Throwable message) {
		super(message);
	}
}
