package com.masai.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.ToString;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Vaccine {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Integer vaccineid;
	
//	@NonNull
	private String vaccineName;
	private String description;

	@OneToOne(cascade = CascadeType.ALL, mappedBy = "vaccine")
	private VaccineCount vaccinecount;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "vaccine")
	private List<Member> member;
}
