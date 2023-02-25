package com.masai.service;

import java.util.List;

import com.masai.exception.VaccineRegistrationException;
import com.masai.model.Member;
import com.masai.model.VaccineRegistration;

public interface VaccineRegistrationSerivce {
	
	public List<VaccineRegistration> getAllVaccineRegistration() throws VaccineRegistrationException;
	
	public VaccineRegistration getVaccineRegistration(String mobileNo) throws VaccineRegistrationException;
	
	public Member getMemberByMobile(String mobileNo) throws VaccineRegistrationException;
	
	public VaccineRegistration addVaccineRegistration(VaccineRegistration reg);
	
	public VaccineRegistration updateVaccineRegistration(VaccineRegistration reg) throws VaccineRegistrationException;
	
	public Boolean deleteVaccineRegistration(VaccineRegistration reg) throws VaccineRegistrationException;
	
}