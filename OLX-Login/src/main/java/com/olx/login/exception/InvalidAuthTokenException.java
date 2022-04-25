package com.olx.login.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidAuthTokenException extends RuntimeException {
	
	String message;

	
	public InvalidAuthTokenException() {
		this.message = "";
		// TODO Auto-generated constructor stub
	}


	public InvalidAuthTokenException(String message) {
		this.message = message;
		// TODO Auto-generated constructor stub
	}


	@Override
	public String toString() {
		return "InvalidCredentialsException [message=" + message + "]";
	}
	
	

}
