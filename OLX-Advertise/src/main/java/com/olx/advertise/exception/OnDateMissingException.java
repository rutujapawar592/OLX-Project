package com.olx.advertise.exception;

public class OnDateMissingException extends RuntimeException {
	
	String message;

	
	public OnDateMissingException() {
		this.message = "";
		// TODO Auto-generated constructor stub
	}


	public OnDateMissingException(String message) {
		this.message = message;
		// TODO Auto-generated constructor stub
	}


	@Override
	public String toString() {
		return "InvalidCredentialsException [message=" + message + "]";
	}
	
	

}
