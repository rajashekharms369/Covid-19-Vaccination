package com.masai.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.exception.VaccineException;
import com.masai.exception.VaccineInventoryException;
import com.masai.model.VaccinationCenter;
import com.masai.model.Vaccine;
import com.masai.model.VaccineCount;
import com.masai.model.VaccineInventory;
import com.masai.repository.VaccinationCenterRepository;
import com.masai.repository.VaccineInventoryRepository;
import com.masai.repository.VaccineRepository;

@Service
public class VaccineInventoryServiceImpl implements VaccineInventoryService {
	
	@Autowired
	private VaccineInventoryRepository vacInvRep;
	
	@Autowired
	private VaccinationCenterRepository vacCenRep;
	
	@Autowired
	private VaccineRepository vacRep;

	@Override
	public List<VaccineInventory> allVaccineInventory() throws VaccineInventoryException {
		List<VaccineInventory> list = vacInvRep.findAll();
		if(list.isEmpty()) {
			throw new VaccineInventoryException("No Inventory Found.");
		}
		return list;
	}
	

	@Override
	public VaccineInventory addVaccineCount(Integer vid, Integer vacInv, VaccineCount vaccineCount)
			throws VaccineInventoryException, VaccineException {
		Optional<VaccineInventory> vaccineInventory = vacInvRep.findById(vacInv);
		if(vaccineInventory.get()==null) {
			throw new VaccineInventoryException("No VaccineInventory found with details :"+vacInv);
		}
		
		Optional<Vaccine> vaccine = vacRep.findById(vid);
		if(vaccine.get()==null) {
			throw new VaccineException("No Vaccine Found with that id");
		}
		
		vaccineCount.setVaccineInventory(vaccineInventory.get());
		
		vaccineCount.setVaccine(vaccine.get());
		
		vaccine.get().setVaccinecount(vaccineCount);
		
		vaccineInventory.get().getVaccineCounts().add(vaccineCount);
		
		return vacInvRep.save(vaccineInventory.get());
	}
	

	@Override
	public VaccineInventory getVaccineInventoryByCenter(Integer centerId) throws VaccineInventoryException {
		
		Optional<VaccinationCenter> vaccinationCenter = vacCenRep.findById(centerId);
		
		if(vaccinationCenter.get()==null) {
			throw new VaccineInventoryException("No Vaccination Center Found with centerId: "+centerId);
		}
		
		return vaccinationCenter.get().getVaccineInventory();
	}

	@Override
	public VaccineInventory updateVaccineInventory(Integer vaccineInventory, VaccineInventory vInventory)
			throws VaccineInventoryException {
		
		Optional<VaccineInventory> vacInventory = vacInvRep.findById(vaccineInventory);
		
		if(vacInventory.get()==null) {
			throw new VaccineInventoryException("No VaccineInventory fond with id: "+vaccineInventory);
		}
		
		List<VaccinationCenter> centerList = vInventory.getVaccinationCenters();
		
		for(VaccinationCenter vaccenter : centerList) {
			vaccenter.setVaccineInventory(vInventory);
		}
		
		List<VaccineCount> VacCountList = vInventory.getVaccineCounts();
		
		for(VaccineCount vacCount: VacCountList) {
			vacCount.setVaccineInventory(vInventory);
		}
		
		return vacInvRep.save(vInventory);
	}

	@Override
	public Boolean deleteVaccineInventory(Integer vaccineInventory) throws VaccineInventoryException {
		Optional<VaccineInventory> vacInventory =  vacInvRep.findById(vaccineInventory);
		
		if(vacInventory.get()==null) {
			throw new VaccineInventoryException("No VaccineInventory found with id: "+vaccineInventory);
		}
		
		vacInvRep.deleteById(vaccineInventory);
		
		return true;
	}

	
	@Override
	public List<VaccineInventory> getVaccineInventoryByDate(LocalDate date) throws VaccineInventoryException {
		List<VaccineInventory> list = vacInvRep.findByDate(date);
		
		if(list.isEmpty()) {
			throw new VaccineInventoryException("No VaccineInventory Found with date :"+date);
		}
		return list;
	}

	
	@Override
	public List<VaccineInventory> getVaccineInventoryByVaccine(Vaccine vaccine) throws VaccineInventoryException {
	
		List<VaccineInventory> result = new ArrayList<>();
		
		List<VaccineInventory> list = vacInvRep.findAll();
		
		if(list.isEmpty()) {
			throw new VaccineInventoryException("No VaccineInventory found, first add some inventory");
		}
		
		for(VaccineInventory vacInv: list) {
			List<VaccineCount> vacCountList = vacInv.getVaccineCounts();
			
			if(vacCountList.isEmpty()) {
				throw new VaccineInventoryException("No VaccineCount found, first add some Vaccine Count.");
			}
			
			for(VaccineCount vacCount: vacCountList) {
				if(vacCount.getVaccine()==vaccine) {
					result.add(vacInv);
				}
			}
		}
		if(result.isEmpty()) {
			throw new VaccineInventoryException("No VaccineInventory Found with Vaccine: "+vaccine);
		}
		return result;
	}

}