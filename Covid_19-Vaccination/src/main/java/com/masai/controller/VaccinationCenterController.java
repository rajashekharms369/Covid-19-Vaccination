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

import com.masai.model.VaccinationCenter;
import com.masai.service.VaccinationCenterService;


@RestController
public class VaccinationCenterController {

	@Autowired
	private VaccinationCenterService vaCenterService;
	
	@PostMapping("/vacenter")
	public ResponseEntity<VaccinationCenter> createVaccinationCenter(@RequestBody VaccinationCenter vaCenter){
		VaccinationCenter vaccinationCenter = vaCenterService.addVaccinationCenter(vaCenter);
		return new ResponseEntity<>(vaccinationCenter,HttpStatus.CREATED);
	}
	@GetMapping("/allcenter")
	public ResponseEntity<List<VaccinationCenter>> getAllVaccinationCenterHandler(){
		List<VaccinationCenter> lists = vaCenterService.getAllCenters();
		return new ResponseEntity<List<VaccinationCenter>>(lists,HttpStatus.FOUND);
	}
	@GetMapping("/vacenter/{centerid}")
	public ResponseEntity<VaccinationCenter> getVaccinationCenterByIDHandler(@PathVariable("centerid")Integer id){
		VaccinationCenter center = vaCenterService.getCenterByCenterId(id);
		return new ResponseEntity<>(center,HttpStatus.FOUND);
	}
	@PutMapping("/vacenter")
	public ResponseEntity<VaccinationCenter> updateVaccinationCenterDetailsHandler(@RequestBody VaccinationCenter vacenter) {
		return new ResponseEntity<VaccinationCenter>(vaCenterService.updateVaccinationCenter(vacenter),HttpStatus.OK);
	}
	@DeleteMapping("/vacenter/{id}")
	public ResponseEntity<String> deleteVaccinationCenterByIdHandler(@PathVariable("id")Integer id){
		String result = "";
		if(vaCenterService.deleteVaccinationCenter(id)) {
			result = "Deleted successful";
		}else result = "Not Deleted";
		return new ResponseEntity<>(result,HttpStatus.ACCEPTED);
	}
}
