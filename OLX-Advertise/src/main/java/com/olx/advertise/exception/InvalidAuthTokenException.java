package com.olx.advertise.exception;

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
