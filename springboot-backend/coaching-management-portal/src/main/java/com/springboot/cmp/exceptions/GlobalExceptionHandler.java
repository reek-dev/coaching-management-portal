package com.springboot.cmp.exceptions;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(EmailAlreadyExistsException.class)
	public ResponseEntity<ExceptionDetail> handleEmailAlreadyExistsException(
			EmailAlreadyExistsException exception, WebRequest webRequest) {

		ExceptionDetail detail = new ExceptionDetail(LocalDateTime.now(), exception.getMessage(),
				webRequest.getDescription(false), "EMAIL_ALREADY_EXISTS");
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(detail);
	}
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ExceptionDetail> handleResourceNotFoundException(
			ResourceNotFoundException exception, WebRequest webRequest) {
		ExceptionDetail detail = new ExceptionDetail(
				LocalDateTime.now(),
				exception.getMessage(),
				webRequest.getDescription(false),
				"ENTITY_NOT_FOUND"
				);
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(detail);
	}
	
	@ExceptionHandler(ResourceAlreadyExistsException.class)
	public ResponseEntity<ExceptionDetail> handleResourceAlreadyExistsException(
			ResourceAlreadyExistsException exception, WebRequest webRequest) {
		ExceptionDetail detail = new ExceptionDetail(
				LocalDateTime.now(),
				exception.getMessage(),
				webRequest.getDescription(false),
				"ENTITY_ALREADY_EXISTS"
				);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(detail);
	}
	
	@ExceptionHandler(InvalidEmailException.class)
	public ResponseEntity<ExceptionDetail> handleInvalidEmailException(
			InvalidEmailException exception, WebRequest webRequest) {
		ExceptionDetail detail = new ExceptionDetail(
				LocalDateTime.now(),
				exception.getMessage(),
				webRequest.getDescription(false),
				"INVALID_EMAIL"
				);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(detail);
	}
}
