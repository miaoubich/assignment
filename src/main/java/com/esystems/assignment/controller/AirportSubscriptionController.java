package com.esystems.assignment.controller;

import java.net.URI;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.esystems.assignment.entity.Subscription;
import com.esystems.assignment.service.AirportSubscriptionService;
import com.fasterxml.jackson.core.JsonProcessingException;

@RestController
@RequestMapping("/api")
public class AirportSubscriptionController {

	@Autowired
	private AirportSubscriptionService airportSubscriptionService;

	@PostMapping("/post/subscription")
	public ResponseEntity<Subscription> addSubscription(@RequestBody Subscription subscription) {
		Subscription persistedSubscription = airportSubscriptionService.insertSubscribtion(subscription);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("")
				.build(persistedSubscription.getIcaoCode());

		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(location);

		return new ResponseEntity<Subscription>(persistedSubscription, headers, HttpStatus.CREATED);
	}

	@PostMapping("/post/subscriptions")
	public ResponseEntity<String> addSubscriptionsList(@RequestBody List<Subscription> subscriptions) {
		return new ResponseEntity<String>(airportSubscriptionService.insertSubscriptionsList(subscriptions),
				HttpStatus.CREATED);
	}

	@GetMapping("/get/subscription/{icaoCode}")
	public ResponseEntity<Subscription> retrieveSubscribedAirport(@PathVariable String icaoCode)
			throws JsonProcessingException {
		Subscription subscription = airportSubscriptionService.retrieveSingleSubscribtion(icaoCode);

		if (subscription != null)
			return new ResponseEntity<Subscription>(subscription, HttpStatus.FOUND);
		else
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "There is no subscription for this airport.");
	}

	@GetMapping("/get/subscriptions/list")
	public ResponseEntity<List<Subscription>> getSubscriptionsList() {
		return new ResponseEntity<List<Subscription>>(airportSubscriptionService.retrieveSubscribtionsList(),
				HttpStatus.FOUND);
	}

	@DeleteMapping("/delete/subscription/{icaoCode}")
	public ResponseEntity<String> deleteSubscription(@PathVariable String icaoCode) {
		String message = airportSubscriptionService.deleteSubscription(icaoCode);

		return new ResponseEntity<String>(message, HttpStatus.OK);
	}
}
