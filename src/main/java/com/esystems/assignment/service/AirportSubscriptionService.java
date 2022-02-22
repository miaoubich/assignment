package com.esystems.assignment.service;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.ReflectionUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.esystems.assignment.entity.Subscription;
import com.esystems.assignment.repository.SubscriptionRepository;

@Service
public class AirportSubscriptionService {

	@Autowired
	private SubscriptionRepository subscriptionRepository;

	public Subscription insertSubscribtion(Subscription subscription) {
		if (retrieveSingleSubscribtion(subscription.getIcaoCode()) == null)
			return subscriptionRepository.save(subscription);
		else
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Subscription already exist.");
	}

	public String insertSubscriptionsList(List<Subscription> subscriptions) {
		if (subscriptionRepository.saveAll(subscriptions).size() != 0)
			return "Subscriptions are success";
		else
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Something went wrong.");
	}

	public Subscription retrieveSingleSubscribtion(String icaoCode) {
		return subscriptionRepository.findByIcaoCode(icaoCode);
	}

	public List<Subscription> retrieveSubscribtionsList() {
		return subscriptionRepository.findAll();
	}

	public String deleteSubscription(String icaoCode) {
		Subscription subscription = retrieveSingleSubscribtion(icaoCode);
		if (subscription != null) {
			subscriptionRepository.deleteById(subscription.getId());
			return "Subscription successfully deleted.";
		} else
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,
					"The Subscription doesn't exists, or was already deleted.");
	}

	public Subscription activateDeactivateSubscribe(String icaoCode, Map<Object, Object> fields) {
		Subscription existingSubscription = retrieveSingleSubscribtion(icaoCode);

		if (existingSubscription != null) {
			fields.forEach((key, value) -> {
				Field field = ReflectionUtils.findRequiredField(Subscription.class, (String) key);
				field.setAccessible(true);
				ReflectionUtils.setField(field, existingSubscription, value);
			});
			subscriptionRepository.save(existingSubscription);
		} else
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,
					"Subscription with the [ ICAO CODE = " + icaoCode + " ] doesn't exist.");

		return existingSubscription;
	}

	public List<Subscription> retrieveActiveSubscribtion() {
		String active = "1";
		List<Subscription> subscriptions = subscriptionRepository.findByActive(active);
		if (subscriptions.size() > 0)
			return subscriptions;
		throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No active subscription found.");
	}
}
