package com.excilys.validation;

import org.apache.commons.validator.routines.DateValidator;

public class ComputerDatabaseValidator {
	
	/**
	 * Validate date.
	 *
	 * @param inputString The input string
	 * @return True, if successful
	 */
	public static boolean validateDate(String inputString) {
		return DateValidator.getInstance().isValid(inputString, "yyyy-MM-dd HH:mm:ss");
	}

}

