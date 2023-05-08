package com.springboot.cmp.util;

import java.util.regex.Pattern;

public class EmailValidator {
	
	private static final String regex =
			"^([_a-zA-Z0-9-]+(\\.[_a-zA-Z0-9-]+)*@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*(\\.[a-zA-Z]{1,6}))?$";
	
	public static boolean isEmailValid(String email) {
		Pattern pattern = Pattern.compile(regex);
		return pattern.matcher(email).matches();
	}
}
