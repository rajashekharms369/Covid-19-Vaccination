package com.masai.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class VaccinationCenter {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer centerid;
	@Min(3)
	private Integer code;
	@NotNull(message = "Center name can not be null")
	@NotBlank(message = "Center name is Mandatory")
	private String centername;

	@NotNull(message = "City can not be null")
	@NotBlank(message = "City name is Mandatory")
	private String city;

	@NotNull(message = "Address can not be null")
	@NotBlank(message = "Address is Mandatory")
	private String address;

	@NotNull(message = "State can not be null")
	@NotBlank(message = "State name is Mandatory")
	private String state;

	@NotBlank(message = "Pincode is Mandatory")
	@Size(min = 6, max = 8)
	private String pincode;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "vaccinationCenter")
	private List<Appointment> appointments = new ArrayList<>();

	@ManyToOne(cascade = CascadeType.ALL)
	private VaccineInventory vaccineInventory;
}