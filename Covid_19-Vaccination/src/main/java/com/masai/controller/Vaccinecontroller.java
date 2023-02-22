package com.masai.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.masai.model.Vaccine;
import com.masai.service.VaccineService;

@RestController
public class Vaccinecontroller {

	@Autowired
	private VaccineService vaccineService;
	
	@GetMapping(value = "/vaccine/{name}")
	public ResponseEntity<Vaccine> getVaccinebyNameHandller(@PathVariable("name") String vaccineName){
		Vaccine vaccine = vaccineService.getVaccineByName(vaccineName);
		
		return new ResponseEntity<>(vaccine,HttpStatus.OK);
	}
	
	@GetMapping(value = "/vaccine/{id}")
	public ResponseEntity<Vaccine> getVaccinebyIdHandler(@PathVariable("id") Integer vaccineId){
		Vaccine vaccine = vaccineService.getVaccineById(vaccineId);
		
		return new ResponseEntity<>(vaccine,HttpStatus.OK);
	}
	
	@PostMapping(value = "/vaccine")
	public ResponseEntity<Vaccine> addNewVaccineHandler(@RequestBody Vaccine vaccine){
		Vaccine savevaccine = vaccineService.addNewVaccine(vaccine);
		
		return new ResponseEntity<>(savevaccine,HttpStatus.CREATED);
	}
	
	
	@PutMapping(value = "/vaccine")
	public ResponseEntity<Vaccine> updateVaccineHandler(@RequestBody Vaccine vaccine){
		Vaccine updatevaccine = vaccineService.updateVaccine(vaccine);
		
		return new ResponseEntity<>(updatevaccine,HttpStatus.CREATED);
	}
	
	

	@DeleteMapping(value = "/vaccine")
	public ResponseEntity<Boolean> deleteVaccineHandler(@RequestBody Vaccine vaccine){
		boolean deletetevaccine = vaccineService.deleteVaccine(vaccine);
		
		return new ResponseEntity<>(deletetevaccine,HttpStatus.OK);
	}
}
