package com.masai.service;

import java.time.LocalDate;
import java.util.List;

import com.masai.exception.VaccineException;
import com.masai.exception.VaccineInventoryException;
import com.masai.model.Vaccine;
import com.masai.model.VaccineCount;
import com.masai.model.VaccineInventory;

public interface VaccineInventoryService {
	
	public List<VaccineInventory> allVaccineInventory()throws VaccineInventoryException;
	
	public VaccineInventory addVaccineCount(Integer vid,Integer vacInv, VaccineCount vaccineCount)throws VaccineInventoryException, VaccineException;
	
	public VaccineInventory getVaccineInventoryByCenter(Integer center)throws VaccineInventoryException;
	
	public VaccineInventory updateVaccineInventory(Integer vaccineInventory,VaccineInventory vInventory)throws VaccineInventoryException;
	
	public Boolean deleteVaccineInventory(Integer vaccineInventory)throws VaccineInventoryException;
	
	public List<VaccineInventory> getVaccineInventoryByDate(LocalDate date)throws VaccineInventoryException;
	
	public List<VaccineInventory> getVaccineInventoryByVaccine(Vaccine vaccine)throws VaccineInventoryException;
}
