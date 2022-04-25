package com.olx.login.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import io.jsonwebtoken.SignatureException;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
	

	@ExceptionHandler(value = InvalidCredentialsException.class)
	public ResponseEntity<Object> handleConflictForCredentials(RuntimeException exception, WebRequest request) {
		String errorMessage = "{\"error\" : \"InvalidCredentialsException\"}";
		ResponseEntity<Object> response = 
				
				handleExceptionInternal(exception, errorMessage, new HttpHeaders(), HttpStatus.CONFLICT, request);
		System.out.println(request);
		return response;
	}
	
	
	@ExceptionHandler(value = InvalidAuthTokenException.class)
	public ResponseEntity<Object> handleConflictForAuthToken(RuntimeException exception, WebRequest request) {
		//String errorMessage = "{\"error\" : \"InvalidCredentialsException\"}";
		ResponseEntity<Object> response = 
				
				handleExceptionInternal(exception, exception.toString(), new HttpHeaders(), HttpStatus.CONFLICT, request);
		System.out.println(request);
		return response;
	} 
	
	@ExceptionHandler(value = SignatureException.class)
	public ResponseEntity<Object> handleInvalidToken(RuntimeException exception, WebRequest request) {
		
		ResponseEntity<Object> response = 
				
				handleExceptionInternal(exception, exception.toString(), new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
		System.out.println(request);
		return response;
	}
}
