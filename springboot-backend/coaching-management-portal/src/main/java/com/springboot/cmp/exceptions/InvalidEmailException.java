package com.springboot.cmp.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class InvalidEmailException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	public InvalidEmailException(String email) {
		super(String.format("the email id `%s` is not valid.", email));
	}
}
