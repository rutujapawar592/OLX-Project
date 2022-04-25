package com.olx.advertise.exception;

public class InvalidStatusIdException extends RuntimeException {
	
	String message;

	
	public InvalidStatusIdException() {
		this.message = "";
		// TODO Auto-generated constructor stub
	}


	public InvalidStatusIdException(String message) {
		this.message = message;
		// TODO Auto-generated constructor stub
	}


	@Override
	public String toString() {
		return "InvalidCredentialsException [message=" + message + "]";
	}
	
	

}
