package com.olx.masterdata.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.olx.masterdata.dto.*;
import com.olx.masterdata.service.MasterService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/olx/materData")
@CrossOrigin(origins = "*")
public class MasterController {

	@Autowired
	MasterService masterService;

	// 6
	@GetMapping(value = "/category", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Getting Categories", notes = "This Rest API will Return all Categories Data")
	public List<Category> getAllCategory() {
		return masterService.getAllCategory();
	}

	// 7
	@GetMapping(value = "/status", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Getting Status Details", notes = "This Rest API will Return all Status Details Data")
	public List<Status> getAllStatus() {
		return masterService.getAllStatus();
	}

	// Getting Categories
	// http://localhost:9091/materData/cateId
	@GetMapping(value = "/CategoryId/{cateId}", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	@ApiOperation(value = "Authenticating a User", notes = "This Rest API helps to Authenticated User Data")
	public String getCategoryDispcription(@PathVariable("cateId") int CateId) {
		return masterService.getCategoryDescription(CateId);

	}

	// Getting Categories
	// http://localhost:9091/materData/cateId
	@GetMapping(value = "/StatusId/{StatusId}", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	@ApiOperation(value = "Authenticating a User", notes = "This Rest API helps to Authenticated User Data")
	public String getStatusName(@PathVariable("StatusId") int StatusId) {
		return masterService.getStatusName(StatusId);

	}
}
