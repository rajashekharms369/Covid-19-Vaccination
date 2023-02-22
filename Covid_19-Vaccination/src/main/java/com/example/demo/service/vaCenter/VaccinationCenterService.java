package com.example.demo.service.vaCenter;

import java.util.List;

import com.example.demo.exception.VaccinationCenterException;
import com.example.demo.model.VaccinationCenter;

public interface VaccinationCenterService {

	public VaccinationCenter addVaccinationCenter(VaccinationCenter vaccinationCenter) throws VaccinationCenterException;
	public List<VaccinationCenter> getAllCenters()throws VaccinationCenterException;
	public VaccinationCenter getCenterByCenterId(Integer centerid) throws VaccinationCenterException;
	public VaccinationCenter updateVaccinationCenter(VaccinationCenter center)throws VaccinationCenterException;
	public boolean deleteVaccinationCenter(Integer centerid)throws VaccinationCenterException;
}
