package com.olx.advertise.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope
public class CheckStatusOfURL {
	
	@Value("${spring.datasource.url}")
	private String url;
	
	@GetMapping(value = "/status")
	public String CheckStatus() {
		return this.url;
	}

}
