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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonFormat;

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
	
	private boolean dose1Status;
	private boolean dose2Status;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
	private LocalDate dose1Date;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
	private LocalDate dose2Date;
	
	@OneToOne(cascade = CascadeType.ALL)
	private IdCard idCard;

	@ManyToOne(cascade = CascadeType.ALL)
	private Vaccine vaccine;

	@ManyToOne(cascade = CascadeType.ALL)
	private VaccineRegistration vaccineRegistration;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "member", fetch = FetchType.EAGER)
	private List<Appointment> appointments = new ArrayList<Appointment>();

	public Integer getMemberId() {
		return memberId;
	}

	public void setMemberId(Integer memberId) {
		this.memberId = memberId;
	}

	public boolean isDose1Status() {
		return dose1Status;
	}

	public void setDose1Status(boolean dose1Status) {
		this.dose1Status = dose1Status;
	}

	public boolean isDose2Status() {
		return dose2Status;
	}

	public void setDose2Status(boolean dose2Status) {
		this.dose2Status = dose2Status;
	}

	public LocalDate getDose1Date() {
		return dose1Date;
	}

	public void setDose1Date(LocalDate dose1Date) {
		this.dose1Date = dose1Date;
	}

	public LocalDate getDose2Date() {
		return dose2Date;
	}

	public void setDose2Date(LocalDate dose2Date) {
		this.dose2Date = dose2Date;
	}

	public IdCard getIdCard() {
		return idCard;
	}

	public void setIdCard(IdCard idCard) {
		this.idCard = idCard;
	}

	public Vaccine getVaccine() {
		return vaccine;
	}

	public void setVaccine(Vaccine vaccine) {
		this.vaccine = vaccine;
	}

	public VaccineRegistration getVaccineRegistration() {
		return vaccineRegistration;
	}

	public void setVaccineRegistration(VaccineRegistration vaccineRegistration) {
		this.vaccineRegistration = vaccineRegistration;
	}

	public List<Appointment> getAppointments() {
		return appointments;
	}

	public void setAppointments(List<Appointment> appointments) {
		this.appointments = appointments;
	}

	
	
	
}