package com.olx.login.exception;

public class InvalidCredentialsException extends RuntimeException {
	
	String message;

	
	public InvalidCredentialsException() {
		this.message = "";
	}


	public InvalidCredentialsException(String message) {
		this.message = message;
	}


	@Override
	public String toString() {
		return "InvalidCredentialsException [message=" + message + "]";
	}
	
	

}
