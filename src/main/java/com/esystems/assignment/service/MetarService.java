package com.esystems.assignment.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.esystems.assignment.entity.Metar;
import com.esystems.assignment.repository.MetarRepository;

@Service
public class MetarService {

	@Autowired
	private MetarRepository metarRepository;

	public String insertMetarData(Metar metar) {
		metarRepository.save(metar);

		return "Metar Data inserted successful.";
	}

	public Metar retrieveMetarData(String icaoCode) {
		List<Metar> metars = metarRepository.findAll();

		for (Metar metar : metars) {
			if (metar.getMdata().contains(icaoCode))
				return metar;
		}
		throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Metar Not Found, or doesn't exist.");
	}
}
