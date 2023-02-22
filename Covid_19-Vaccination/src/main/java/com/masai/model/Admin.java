package com.masai.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
@Entity
public class Admin {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer customerId;
	
	@NotNull(message = "Name is Empty")
	private String name;
	
	@Column(unique = true)
	@NotNull(message="Mobile is Empty")
	@Size(max=10, min=10)
	private String mobileNo;
	
	@NotNull(message="Password is Empty")
	private String password;
	
	@NotNull(message="Email is Empty")
	@Email
	private String email;
}
