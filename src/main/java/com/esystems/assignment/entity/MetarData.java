package com.esystems.assignment.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "metardata")
@Entity(name = "metardata")
@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler" })
public class MetarData {
	// data timestamp, wind strength, temperature, overall visibility 
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name = "icao_code")
	private String icaoCode;
	@Column(name = "time_stamp")
	private String timeStamp;
	@Column(name = "temperature")
	private String temperature;
	@Column(name = "dew_point")
	private String dewPoint;
	@Column(name = "wind_direction")
	private String windDirection;
	@Column(name = "wind_speed")
	private String windSpeed;
	@Column(name = "wind_strength")
	private String windStrength;
	@Column(name = "precipitation")
	private String precipitation;
	@Column(name = "visibility")
	private String visibility;
	@Column(name = "barometric_pressure")
	private String barometricPressure;
	
}
