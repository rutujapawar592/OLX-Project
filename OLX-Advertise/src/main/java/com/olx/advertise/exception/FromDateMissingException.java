package com.olx.advertise.exception;

public class FromDateMissingException extends RuntimeException {
	
	String message;

	
	public FromDateMissingException() {
		this.message = "";
	}


	public FromDateMissingException(String message) {
		this.message = message;
	}


	@Override
	public String toString() {
		return "InvalidCredentialsException [message=" + message + "]";
	}
	
	

}
