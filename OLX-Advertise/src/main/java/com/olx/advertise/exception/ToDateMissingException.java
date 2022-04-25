package com.olx.advertise.exception;

public class ToDateMissingException extends RuntimeException {
	
	String message;

	
	public ToDateMissingException() {
		this.message = "";
		// TODO Auto-generated constructor stub
	}


	public ToDateMissingException(String message) {
		this.message = message;
		// TODO Auto-generated constructor stub
	}


	@Override
	public String toString() {
		return "InvalidCredentialsException [message=" + message + "]";
	}
	
	

}
