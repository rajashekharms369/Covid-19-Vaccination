package com.masai.service.vaCenter;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.exception.VaccinationCenterException;
import com.masai.model.VaccinationCenter;
import com.masai.repository.VaccinationCenterDao;
@Service
public class VaccinationCenterServiceImpl implements VaccinationCenterService{

	@Autowired
	private VaccinationCenterDao vaCenterDao;
	
	@Override
	public VaccinationCenter addVaccinationCenter(VaccinationCenter vaccinationCenter)
			throws VaccinationCenterException {
		VaccinationCenter vaCenter= vaCenterDao.save(vaccinationCenter);
		if(vaCenter == null) throw new VaccinationCenterException("Not Created some error occures");
		
		return vaCenter;
	}
	@Override
	public List<VaccinationCenter> getAllCenters() throws VaccinationCenterException {
		List<VaccinationCenter> lists = vaCenterDao.findAll();
		if(lists.isEmpty()) throw new VaccinationCenterException("Not Found");
		else return lists;
	}
	@Override
	public VaccinationCenter getCenterByCenterId(Integer centerid) throws VaccinationCenterException {
		VaccinationCenter opt = vaCenterDao.findById(centerid).orElseThrow(()-> new VaccinationCenterException("Not found Center with provided id"));
		return opt;
	}
	
	//check enter id and return id
	@Override
	public VaccinationCenter updateVaccinationCenter(VaccinationCenter center) throws VaccinationCenterException {
		Optional<VaccinationCenter> vacenter = vaCenterDao.findById(center.getCenterid());
		if(vacenter.isPresent()) {
			VaccinationCenter result = vaCenterDao.save(center);
			return result;
		}else throw new VaccinationCenterException("Not found Center");
	}
	@Override
	public boolean deleteVaccinationCenter(Integer centerid) throws VaccinationCenterException {
		Optional<VaccinationCenter> vacenter = vaCenterDao.findById(centerid);
		if(vacenter.isPresent()) {
			vaCenterDao.delete(vacenter.get());
			return true;
		}else throw new VaccinationCenterException("Not found Center");
	}
}
