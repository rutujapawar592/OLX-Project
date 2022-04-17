package com.olx.login.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.olx.login.dto.User;
import com.olx.login.service.LoginService;

import io.swagger.annotations.ApiOperation;

///olx/login/user/authenticate
@RestController
@RequestMapping("/olx/login")
@CrossOrigin(origins = "*")

public class LoginController {
	
	@Autowired
	LoginService loginService;
	
	
	@PostMapping(value = "/user/authenticate", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	@ApiOperation(value = "Authenticating a User", notes = "This Rest API helps to Authenticated User Data")
	public ResponseEntity<String> authenticate(@RequestBody User user) {
	        return new ResponseEntity<String>(loginService.authenticate(user),HttpStatus.OK);
	}
	
	
	@DeleteMapping(value = "/user/logout")
	@ApiOperation(value = "Logout a User", notes = "This Rest API helps to Logout a Authenticated User")
	public Boolean logout(@RequestHeader("Authorization") String authToken) {
	        return loginService.logout(authToken);
	}
	
	//3
	@PostMapping(value = "/user", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	@ApiOperation(value = "Register a User", notes = "This Rest API helps to Register a User")
	public ResponseEntity<User> register(@RequestBody User user) {
	        return new ResponseEntity<User> (loginService.register(user),HttpStatus.OK);
	}
	
	//4
	@GetMapping(value = "/user", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	@ApiOperation(value = "Getting a User Information", notes = "This Rest API helps to get Authenticated User Data")
	public ResponseEntity<User> getUser(@RequestHeader("auth-token") String authToken) {
	        return new ResponseEntity<User>(loginService.getUser(authToken),HttpStatus.OK);
	}
	
	//5
	@GetMapping(value = "/token/validate")
	@ApiOperation(value = "Authenticating a User", notes = "This Rest API helps to Authenticated User Data")
    public ResponseEntity<Boolean> tokenValidatation(@RequestHeader("Authorization") String authToken) {
	       return new ResponseEntity<Boolean> (loginService.tokenValidatation(authToken),HttpStatus.OK);
	}
}

