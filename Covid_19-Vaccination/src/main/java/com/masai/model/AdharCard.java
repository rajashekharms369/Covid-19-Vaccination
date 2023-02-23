package com.masai.model;

import javax.validation.constraints.Digits;

public class AdharCard {
	
	@Digits(integer = 12,fraction = 12,message = "Length must be 12")
	private Long adharNo;

	public Long getAdharNo() {
		return adharNo;
	}

	public void setAdharNo(Long adharNo) {
		this.adharNo = adharNo;
	}
	
	
	
}
