package com.excilys.validation;

import org.apache.commons.validator.routines.DateValidator;

public enum ComputerDatabaseValidator {
	INSTANCE;
	
	/**
	 * Validate date.
	 *
	 * @param inputString The input string
	 * @return True, if successful
	 */
	public boolean validateDate(String inputString) {
//		SimpleDateFormat format = new java.text.SimpleDateFormat(
//				"yyyy-MM-dd HH:mm:ss");
//		try {
//			format.parse(inputString);
//			Pattern p = Pattern.compile("^\\d{4}[-]?\\d{1,2}[-]?\\d{1,2} \\d{1,2}:\\d{1,2}:\\d{1,2}");
//			return p.matcher(inputString).matches();
//		} catch (ParseException e) {
//			System.out.println("ParseException");
//			return false;
//		}
//		
//		String yyyy = "";
//		String mm = "";
//		String dd = "";
//		String p = "^[1-2][0-9][0-9][0-9]-((0[0-9]|1[0-2])-((0[13578]-([0-2][0-9])|(3[0-1]))|(1[02]-([0-2][0-9])|(3[0-1]))|(02-[0-2][0-9])|(0[469]-([0-2][0-9])|(30))|(11-([0-2][0-9])|(30)))) ([0-1][0-9]|2[0-3]):[0-5][0-9]:[0-5][0-9]";
////		
//		StringBuilder pattern = new StringBuilder();
//		pattern.append(yyyy).append("-").append(mm).append("-").append(dd);
////		
//		return Pattern.matches(pattern.toString(), inputString);
		
		return DateValidator.getInstance().isValid(inputString, "yyyy-MM-dd HH:mm:ss");
	}

}

