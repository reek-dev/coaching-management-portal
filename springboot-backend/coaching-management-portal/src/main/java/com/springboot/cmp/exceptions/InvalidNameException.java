package com.springboot.cmp.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class InvalidNameException extends RuntimeException {

    public InvalidNameException(String name) {
        super(String.format("the name `%s` is not valid.", name));
    }
}
