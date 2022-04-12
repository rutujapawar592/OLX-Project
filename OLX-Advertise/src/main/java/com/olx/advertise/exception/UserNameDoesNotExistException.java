package com.olx.advertise.exception;

public class UserNameDoesNotExistException extends Exception {
	
String message;

	
	public UserNameDoesNotExistException() {
		this.message = "";
		// TODO Auto-generated constructor stub
	}


	public UserNameDoesNotExistException(String message) {
		this.message = message;
		// TODO Auto-generated constructor stub
	}


	@Override
	public String toString() {
		return "UserNameDoesNotExistException [message=" + message + "]";
	}
}
