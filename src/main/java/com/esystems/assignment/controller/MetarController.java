package com.esystems.assignment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.esystems.assignment.entity.Metar;
import com.esystems.assignment.service.MetarService;

@RestController
@RequestMapping("/api")
public class MetarController {

	@Autowired
	private MetarService metarService;
	
	@PostMapping("/post/airport/METAR")
	public ResponseEntity<String> insertMetarData(@RequestBody Metar metar){//, @PathVariable String icaoCode){
		return new ResponseEntity<String>(metarService.insertMetarData(metar), HttpStatus.CREATED);
	}
	
	@GetMapping("/get/airport/{icaoCode}/METAR")
	public ResponseEntity<Metar> getMetarDataByIcaoCode(@PathVariable String icaoCode){
		return new ResponseEntity<Metar>(metarService.retrieveMetarData(icaoCode), HttpStatus.FOUND);
	}
}
