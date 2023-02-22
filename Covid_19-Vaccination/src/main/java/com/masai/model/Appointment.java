package com.masai.model;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class Appointment {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long bookingId;
	
	@NotBlank(message = "Mobile Number is Mandatory")
	@Size(max=10,message="Moblie Number length should be 10!")
	@Pattern(regexp = "^[6-9][0-9]{9}$",message="Mobile No is Invalid!")
	private String mobileNo;
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate dateOfBooking;
	
	private boolean bookingStatus;
	@Enumerated(EnumType.STRING)
	private Slot slot;
	
	@JsonIgnore
	@ManyToOne(cascade = CascadeType.ALL)
	private Member member;
	
	@ManyToOne(cascade = CascadeType.ALL)
	private VaccinationCenter vaccinationCenter;
}