package com.masai.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.masai.model.AdharCard;
import com.masai.model.IdCard;
import com.masai.model.PanCard;
import com.masai.service.IdCardService;

@RestController
public class IdCardController {
	
	@Autowired
	IdCardService idCardService;
	
	@GetMapping(" /getPancard/{id}")
	public ResponseEntity<PanCard> getPancardByIdHandler(@PathVariable Integer id){
		
		PanCard panCard=idCardService.getPanCardByNumber(id);
		
		
		
		
		return new ResponseEntity(panCard,HttpStatus.ACCEPTED);
	}
	
	

	@GetMapping(" /getAadharcard/{id}")
	public ResponseEntity<AdharCard> getAadharcardByIdHandler(@PathVariable Integer id){
		
	AdharCard aadharCard =idCardService.getAdharCardByNuber(id);
		
		
		
		
		return new ResponseEntity(aadharCard,HttpStatus.ACCEPTED);
	}
	
	@PostMapping("/saveId")
	public ResponseEntity<IdCard> saveIdCardHandler(@RequestBody IdCard idCard){
		
		IdCard savedIdCard=idCardService.saveIdcard(idCard);
		
		
		return new ResponseEntity<IdCard>(savedIdCard,HttpStatus.ACCEPTED);
	}
	
	
	
	

}
