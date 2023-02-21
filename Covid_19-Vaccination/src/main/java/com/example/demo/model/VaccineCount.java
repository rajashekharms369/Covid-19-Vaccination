package com.example.demo.model;

import javax.persistence.CascadeType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class VaccineCount {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer vaccineCountId;

	private Integer quantity;
	
	private Double price;

	@JsonIgnore
	@OneToOne(cascade = CascadeType.ALL)
	private Vaccine vaccine;

	@ManyToOne(cascade = CascadeType.ALL)
	private VaccineInventory vaccineInventory;
}
