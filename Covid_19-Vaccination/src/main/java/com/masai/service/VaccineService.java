package com.masai.service;

import com.masai.exception.VaccineException;
import com.masai.model.Vaccine;

public interface VaccineService {

	public Vaccine getVaccineByName(String vaccineName)throws VaccineException;
	
	public Vaccine getVaccineById(Integer vaccineId)throws VaccineException;
	
	public Vaccine addNewVaccine(Vaccine vaccine)throws VaccineException;
	
	public Vaccine updateVaccine(Vaccine vaccine)throws VaccineException;
	
	public Boolean deleteVaccine(Vaccine vaccine)throws VaccineException;
	
	
	
	
}
