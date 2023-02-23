package com.masai.controller;

import java.util.List;

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

import com.masai.exception.VaccineRegistrationException;
import com.masai.model.Member;
import com.masai.model.VaccineRegistration;
import com.masai.service.VaccineRegistrationSerivce;

@RestController
public class VaccineRegistrationController {
	
	@Autowired
	public VaccineRegistrationSerivce vaccineRegistrationService;
	
	@GetMapping("/allVaccineRegistrations")
	public ResponseEntity<List<VaccineRegistration>> getAllVaccineRegistrations() throws VaccineRegistrationException{
		List<VaccineRegistration> vr = vaccineRegistrationService.getAllVaccineRegistration();
		return new ResponseEntity<List<VaccineRegistration>>(vr, HttpStatus.OK);
	}

	
	@GetMapping("/getVaccine/{mobileno}")
	public ResponseEntity<VaccineRegistration> getVaccineRegistration(@PathVariable long monileno) throws VaccineRegistrationException{
		VaccineRegistration vr = vaccineRegistrationService.getVaccineRegistration(monileno);
		return new ResponseEntity<VaccineRegistration>(vr, HttpStatus.FOUND);
	}
	
	
	@GetMapping("/getMember/{mob}")
	public ResponseEntity<Member> getMemberByMobile(@PathVariable long mob) throws VaccineRegistrationException{
		
		Member m = vaccineRegistrationService.getMemberByMobile(mob);
		return new ResponseEntity<Member>(m, HttpStatus.FOUND);
	}
	
	
	@PutMapping("/vaccineRegistration")
	public ResponseEntity<VaccineRegistration> updateVaccineRegistration(@RequestBody VaccineRegistration vacReg) throws VaccineRegistrationException{
		
		VaccineRegistration m = vaccineRegistrationService.updateVaccineRegistration(vacReg);
		
		return new ResponseEntity<VaccineRegistration>(m, HttpStatus.CREATED);
		
	}
	
	
	@DeleteMapping("/deleteVaccineRegistration")
	public ResponseEntity<Boolean> deleteVaccineRegistration(@RequestBody VaccineRegistration vacReg) throws VaccineRegistrationException{
		
		Boolean m = vaccineRegistrationService.deleteVaccineRegistration(vacReg);
		
		return new ResponseEntity<Boolean>(m, HttpStatus.OK);
	}
	
}