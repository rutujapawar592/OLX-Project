package com.misc.dto;

public class DBConfig {

private String url;
private String username;
public String getUrl() {
	return url;
}
public void setUrl(String url) {
	this.url = url;
}
public String getUsername() {
	return username;
}
public void setUsername(String username) {
	this.username = username;
}
public DBConfig(String url, String username) {
	super();
	this.url = url;
	this.username = username;
}
public DBConfig() {
	super();
}

}
