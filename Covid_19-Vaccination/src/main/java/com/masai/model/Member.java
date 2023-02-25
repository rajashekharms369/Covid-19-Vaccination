package com.masai.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.Future;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Member {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer memberId;
	
	@JsonIgnore
	private boolean dose1Status=false;
	
	@JsonIgnore
	private boolean dose2Status=false;
	
	@JsonIgnore
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
	@Future
	private LocalDate dose1Date;
	
	@JsonIgnore
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
	@Future
	private LocalDate dose2Date;
	
	@JsonIgnore
	private String vaccineName;
	
	@OneToOne(cascade = CascadeType.ALL)
	private IdCard idCard;
	
	@JsonIgnore
	@OneToOne(cascade = CascadeType.ALL)
	private Vaccine vaccine;
	
	@JsonIgnore
	@ManyToOne(cascade = CascadeType.ALL)
	private VaccineRegistration vaccineRegistration;
     
	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL,mappedBy = "member")
	private List<Appointment> appointments = new ArrayList<Appointment>();
		
}