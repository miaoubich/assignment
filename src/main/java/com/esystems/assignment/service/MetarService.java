package com.esystems.assignment.service;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.esystems.assignment.entity.Metar;
import com.esystems.assignment.entity.MetarData;
import com.esystems.assignment.repository.MetarDataRepository;
import com.esystems.assignment.repository.MetarRepository;
import com.esystems.assignment.repository.temperatureAndWindStrength;

@Service
public class MetarService {

	@Autowired
	private MetarRepository metarRepository;
	@Autowired
	private MetarDataRepository metarDataRepository;

	private MetarData metarData = new MetarData();

	public String insertMetarData(Metar metar) {
		metarRepository.save(metar);
		storeMetarDataEachInField(metar);

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

	public void storeMetarDataEachInField(Metar metar) {
		String data = metar.getMdata();
//		String[] dataArray = data.split("\\s+");
		String[] dataArray = StringUtils.split(data);

		metarData.setIcaoCode(dataArray[1]);
		metarData.setTimeStamp(dataArray[2]);
		metarData.setTemperature(dataArray[3]);
		metarData.setDewPoint(dataArray[4]);
		metarData.setWindDirection(dataArray[5]);
		metarData.setWindSpeed(dataArray[6]);
		metarData.setWindStrength(dataArray[7]);
		metarData.setPrecipitation(dataArray[8]);
		metarData.setVisibility(dataArray[9]);
		metarData.setBarometricPressure(dataArray[10]);

		metarDataRepository.save(metarData);
	}

	public List<temperatureAndWindStrength> retrieveWindStrengthAndTemperature() {
		return metarDataRepository.getTemperatureAndWindStrength();
	}
}
