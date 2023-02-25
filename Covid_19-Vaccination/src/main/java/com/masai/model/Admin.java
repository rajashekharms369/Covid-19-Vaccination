package com.masai.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
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
	@Size(min=10,max=10,message="Moblie Number length should be 10!")
	@Pattern(regexp = "^[1-9][0-9]{9}$",message="Mobile No is Invalid!")
	private String mobileNo;
	
	@NotNull(message="Password is Empty")
	private String password;
	
	@NotNull(message="Email is Empty")
	@Email
	private String email;
}
