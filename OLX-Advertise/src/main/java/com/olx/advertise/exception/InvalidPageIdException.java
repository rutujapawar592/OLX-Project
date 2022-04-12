package com.olx.advertise.exception;

public class InvalidPageIdException extends RuntimeException {
	
	String message;

	
	public InvalidPageIdException() {
		this.message = "";
		// TODO Auto-generated constructor stub
	}


	public InvalidPageIdException(String message) {
		this.message = message;
		// TODO Auto-generated constructor stub
	}


	@Override
	public String toString() {
		return "InvalidCredentialsException [message=" + message + "]";
	}
	
	

}
