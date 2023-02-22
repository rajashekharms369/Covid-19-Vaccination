package com.masai.model;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class IdCard {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@NotNull(message="Member name cannot be null")
	@NotBlank(message="Member name is mandatory")
	private String name;
	
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd/MM/yyyy")
	private LocalDate dob;
	
	@NotNull(message="Gender cannot be null")
	@Enumerated(EnumType.STRING)

	private gender Gender;
	
	@NotNull(message="Address cannot be null")
	private String address;
	
	@NotNull(message="City cannot be null")
	private String city;
	
	@NotNull(message="State cannot be null")
	private String state;
	
	@NotNull(message="Pincode cannot be null")
	private String pincode;
	
	@OneToOne(cascade = CascadeType.ALL, mappedBy = "idCard")
	private Member member;
	
	@Embedded
	private AdharCard adharCard;
	
	@Embedded
	private PanCard pancard;
}