package com.olx.advertise.exception;

public class InvalidCategoryIdException extends RuntimeException {
	
	String message;

	
	public InvalidCategoryIdException() {
		this.message = "";
		// TODO Auto-generated constructor stub
	}


	public InvalidCategoryIdException(String message) {
		this.message = message;
		// TODO Auto-generated constructor stub
	}


	@Override
	public String toString() {
		return "InvalidCredentialsException [message=" + message + "]";
	}
	
	

}
