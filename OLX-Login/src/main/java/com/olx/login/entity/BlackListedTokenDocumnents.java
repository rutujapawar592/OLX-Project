package com.olx.login.entity;

import java.time.LocalDate;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "BlacklistedTokens")
public class BlackListedTokenDocumnents {
	
	
	private String token;
	private LocalDate createdDate;
	
	
	public BlackListedTokenDocumnents() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public BlackListedTokenDocumnents( String token, LocalDate createdDate) {
		super();
		this.token = token;
		this.createdDate = createdDate;
	}


	
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public LocalDate getDate() {
		return this.createdDate;
	}
	public void setDate(LocalDate createdDate) {
		this.createdDate = createdDate;
	}


	@Override
	public String toString() {
		return "BlackListedTokenDocumnents [token=" + token + ", createdDate=" + createdDate + "]";
	}
	

}
