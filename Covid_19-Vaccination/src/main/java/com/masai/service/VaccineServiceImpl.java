package com.masai.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.exception.VaccineException;
import com.masai.model.Vaccine;
import com.masai.repository.VaccineRepository;

@Service
public class VaccineServiceImpl implements VaccineService {
	
	@Autowired
	private VaccineRepository vaccineRepository;

	@Override
	public Vaccine getVaccineByName(String vaccineName) throws VaccineException {
		Optional<Vaccine> optional = vaccineRepository.findByVaccineName(vaccineName);
		
		return optional.orElseThrow(()-> new VaccineException("No vaccine found!!"));
	}

	@Override
	public Vaccine getVaccineById(Integer vaccineId) throws VaccineException {
        Optional<Vaccine> optional = vaccineRepository.findById(vaccineId);
		
		return optional.orElseThrow(()-> new VaccineException("No vaccine found..."));
	}

	@Override
	public Vaccine addNewVaccine(Vaccine vaccine) throws VaccineException {
		Optional<Vaccine> optional = vaccineRepository.findById(vaccine.getVaccineid());
		if(optional.isEmpty()) {
			return vaccineRepository.save(vaccine);
			
		}else {
			throw new VaccineException("Vaccine already exist...");
		}
	}

	@Override
	public Vaccine updateVaccine(Vaccine vaccine) throws VaccineException {
		Optional<Vaccine> optional = vaccineRepository.findById(vaccine.getVaccineid());
		if(optional.isEmpty()) {
			throw new VaccineException("vaccine does not exist...");
		}else {
			return vaccineRepository.save(vaccine);
		}
		
	}

	@Override
	public Boolean deleteVaccine(Vaccine vaccine) throws VaccineException {
		Optional<Vaccine> optional = vaccineRepository.findById(vaccine.getVaccineid());
		if(optional.isEmpty()) {
			throw new VaccineException("vaccine does not exist...");
		}else {
			 vaccineRepository.delete(vaccine);
			 return true;
		}
	}

}
