package com.olx.advertise.exception;

public class FromDateMissingException extends RuntimeException {
	
	String message;

	
	public FromDateMissingException() {
		this.message = "";
		// TODO Auto-generated constructor stub
	}


	public FromDateMissingException(String message) {
		this.message = message;
		// TODO Auto-generated constructor stub
	}


	@Override
	public String toString() {
		return "InvalidCredentialsException [message=" + message + "]";
	}
	
	

}
