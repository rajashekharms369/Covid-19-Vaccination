package com.masai.model;

import java.util.ArrayList;
import java.util.List;


import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.ToString;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Vaccine {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer vaccineid;
	
	@NonNull
	private String vaccineName;
	
	private String description;

	@OneToOne(cascade = CascadeType.ALL, mappedBy = "vaccine")
	private VaccineCount vaccinecount;

	@JsonIgnore
	@OneToOne(cascade = CascadeType.ALL, mappedBy = "vaccine")
	private Member member ;

}