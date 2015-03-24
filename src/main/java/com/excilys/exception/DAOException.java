package com.excilys.exception;

public class DAOException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3975094259490319034L;

	public DAOException(String message) {
		super(message);
	}
	
	public DAOException(Throwable cause) {
        super(cause);
    }
}
