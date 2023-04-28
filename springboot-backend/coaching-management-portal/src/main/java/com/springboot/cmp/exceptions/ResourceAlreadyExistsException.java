package com.springboot.cmp.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class ResourceAlreadyExistsException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	public ResourceAlreadyExistsException(String entity, String entityName) {
		super(String.format("the %s '%s' already exists.", entity, entityName));
	}

}
