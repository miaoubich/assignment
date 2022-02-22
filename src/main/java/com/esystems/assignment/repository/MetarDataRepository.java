package com.esystems.assignment.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.esystems.assignment.entity.MetarData;

@Repository
public interface MetarDataRepository extends JpaRepository<MetarData, Integer> {

	MetarData findByIcaoCode(String icaoCode);

	String query = "SELECT temperature, wind_strength FROM metardata";

	@Query(value = query, nativeQuery = true)
	List<temperatureAndWindStrength> getTemperatureAndWindStrength();
}
