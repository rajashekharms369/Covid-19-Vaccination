package com.masai.controller;

import java.time.LocalDate;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.masai.exception.VaccineException;
import com.masai.exception.VaccineInventoryException;
import com.masai.model.Vaccine;
import com.masai.model.VaccineCount;
import com.masai.model.VaccineInventory;
import com.masai.service.VaccineInventoryService;

@RestController
public class VaccineInventoryController {

	@Autowired
	public VaccineInventoryService vaccineInventoryService;
		
	@GetMapping("/getAllVaccineInventory")
	public ResponseEntity<List<VaccineInventory>> getAllVaccineInventory() throws VaccineInventoryException{
		
		List<VaccineInventory> vacInv = vaccineInventoryService.allVaccineInventory();
		
		return new ResponseEntity<List<VaccineInventory>>(vacInv, HttpStatus.FOUND);
	}
	
	@GetMapping("/vaccineInventory/center")
	public ResponseEntity<List<VaccineInventory>> getVaccineInventoryByCenter(@RequestParam String key , @RequestParam("center") Integer centerid) throws VaccineInventoryException{
//		adminLoginService.authenticate(key);

		// error casting	
		List<VaccineInventory> vi = (List<VaccineInventory>) vaccineInventoryService.getVaccineInventoryByCenter(centerid);
		
		if(vi.isEmpty()) {
			throw new VaccineInventoryException("No Vaccine found in the center: "+centerid);
		}
		
		return new ResponseEntity<List<VaccineInventory>>(vi, HttpStatus.FOUND);
	}
	
	
	
	@PostMapping("/vaccineInventory")
	public ResponseEntity<VaccineInventory> addVaccineCount(@RequestParam String key, @RequestParam("v") Integer vacid,
															@RequestParam("vi") Integer vi,
															@RequestBody VaccineCount vc) throws VaccineInventoryException, VaccineException{
		VaccineInventory vInventory = vaccineInventoryService.addVaccineCount(vacid, vi, vc);
		
		if(vInventory==null) {
			
			throw new VaccineInventoryException("No Vaccine found");
			
		}
		
		return new ResponseEntity<VaccineInventory>(vInventory, HttpStatus.OK);
	}
	
	
	@PutMapping("/vaccineInventory")
	public ResponseEntity<VaccineInventory> updateVaccineInventory(@RequestParam String key, 
																@Valid @RequestParam Integer id,
																@RequestBody VaccineInventory vi) throws VaccineInventoryException{
//		adminLoginService.authenticate(key);
		
		VaccineInventory vacInv = vaccineInventoryService.updateVaccineInventory(id, vi);
		
		return new ResponseEntity<VaccineInventory>(vacInv, HttpStatus.ACCEPTED);
	}
	
	
	
	@DeleteMapping("/vaccineInventory/vi")
	public ResponseEntity<Boolean> deleteVaccineInventory(@RequestParam String key, @PathVariable("vi") Integer arg) throws VaccineInventoryException{

		//		adminLoginService.authenticate(key);
		
		Boolean fb = vaccineInventoryService.deleteVaccineInventory(arg);
		
		return new ResponseEntity<Boolean>(fb, HttpStatus.ACCEPTED);
	}
	
	
	
	@GetMapping("/vaccineInventory/date")
	public ResponseEntity<List<VaccineInventory>> getVaccineInventoryByDate(@PathVariable("date") LocalDate d) throws VaccineInventoryException{
		
		List<VaccineInventory> vacInvList = vaccineInventoryService.getVaccineInventoryByDate(d);
		
		if(vacInvList.isEmpty()) {
			throw new VaccineInventoryException("No Inventories Found on date: "+d);
		}
		
		return new ResponseEntity<List<VaccineInventory>>(vacInvList, HttpStatus.FOUND);
	}
	
	
	
	@GetMapping("/vaccineInventories/vaccine")
	public ResponseEntity<List<VaccineInventory>> getVaccineInventoryByVaccine(@RequestParam String key, @Valid @RequestBody Vaccine vaccine) throws VaccineInventoryException{
		
		List<VaccineInventory> vi = vaccineInventoryService.getVaccineInventoryByVaccine(vaccine);
		
		if(vi.isEmpty()) {
			
			throw new VaccineInventoryException("No Inventory of the vaccine"+vaccine);
		
		}
		return new ResponseEntity<List<VaccineInventory>>(vi, HttpStatus.FOUND);
	}
}