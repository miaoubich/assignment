package com.esystems.assignment.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "subscriptions")
@Entity
@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler" })
public class Subscription {

	@Id
	@Column(name = "subscription_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "icao_code", nullable = false)
	private String icaoCode;
	@Column(name = "airport_name")
	private String name;
	
	@Column(name = "active")
	private String active;

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "fk_location_id")
	private Location location;

}
