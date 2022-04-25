package com.olx.advertise.exception;

public class InvalidCredentialsException extends RuntimeException {
	
	String message;

	
	public InvalidCredentialsException() {
		this.message = "";
		// TODO Auto-generated constructor stub
	}


	public InvalidCredentialsException(String message) {
		this.message = message;
		// TODO Auto-generated constructor stub
	}


	@Override
	public String toString() {
		return "InvalidCredentialsException [message=" + message + "]";
	}
	
	

}
