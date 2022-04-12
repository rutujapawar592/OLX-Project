package com.olx.advertise.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.olx.advertise.dto.Advertise;
import com.olx.advertise.service.AdvertiseService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

//http://localhost:9093/advertises/search/filtercriteria
@RestController
@RequestMapping("/advertises")
@CrossOrigin(origins = "*")

public class AdvertiseController {

	@Autowired
	AdvertiseService advertiseService;

	// 8
	@PostMapping(value = "/", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	@ApiOperation(value = "Adding a Avertise", notes= "This Rest API will return Added Advertise")
	public Advertise postAdvertise(Advertise adv) {
		return advertiseService.postAdvertise(adv);
	}

	// 9
	@PutMapping(value = "/{id}", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}, 
			produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	@ApiOperation(value = "Updating a Avertise", notes= "This Rest API will return Updated Advertise")
    public Advertise updateAdvertise(Advertise adv) {
		return advertiseService.updateAdvertise(adv);

	}

	@GetMapping(value = "/user/advertise", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Getting a List of Avertise", notes= "This Rest API will return List of Advertise")
    public List<Advertise> getAllAdvByUser() {
		return null;
	}

	@GetMapping(value = "/user/advertise/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Getting a List of a Avertise by Id", notes= "This Rest API will return List of Advertise by Id")
    public List<Advertise> getAdvByUser(@ApiParam(value="Advertise", name= "id") @PathVariable("id") int id) {
		return null;
	}


    //13
	@GetMapping(value = "/search/filtercriteria", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Getting a List of Avertise by FilterCriteria", notes= "This Rest API will return List of Advertise by Filter Criteria")
	public List<Advertise> searchAdvertisesByFilterCriteria(@RequestParam(name = "searchText", required = false) String searchText,
			@RequestParam(name = "category", required = false) Integer categoryId,
			@RequestParam(name = "postedBy", required = false) String postedBy, @RequestParam(name = "dateCondition" , required = false) String dateCondition,
			@RequestParam(name = "onDate" , required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate onDate,
			@RequestParam(name = "fromDate" , required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fromDate,
			@RequestParam(name = "toDate" , required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate toDate,
			@RequestParam(name = "sortedBy" , required = false) String sortedBy,
			@RequestParam(name = "startIndex", defaultValue = "0") int startIndex,
			@RequestParam(name = "records", defaultValue = "10") int records) {
		return advertiseService.filterAdvertise(searchText, categoryId, postedBy, dateCondition, onDate, fromDate,
				toDate, sortedBy, startIndex, records);
	}

	// 14
	@GetMapping(value = "/search", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Getting a Avertise by Search Text", notes= "This Rest API will return a Advertise by Search Text")
    public Advertise SearchAdvByText(String searchText) {
		return advertiseService.SearchAdvertiseByText(searchText);
	}

	// 15

	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Get a Avertise by Id", notes= "This Rest API will return Advertise by Id")
    public Advertise returnAdv(@ApiParam(value="Advertise", name= "id") @PathVariable("id") int  id) {
		return advertiseService.returnAdvertise(id);
	}

}
