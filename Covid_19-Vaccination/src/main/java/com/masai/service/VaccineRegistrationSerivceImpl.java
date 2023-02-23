package com.masai.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.exception.VaccineRegistrationException;
import com.masai.model.Member;
import com.masai.model.VaccineRegistration;
import com.masai.repository.VaccineRegistrationRepository;

@Service
public class VaccineRegistrationSerivceImpl implements VaccineRegistrationSerivce {

	@Autowired
	private VaccineRegistrationRepository vacRegRep;
	
	@Override
	public List<VaccineRegistration> getAllVaccineRegistration() throws VaccineRegistrationException {
		List<VaccineRegistration> list = vacRegRep.findAll();
		if(list.isEmpty()) {
			throw new VaccineRegistrationException("No Data Found.");
		}
		return list;
	}

	@Override
	public VaccineRegistration getVaccineRegistration(Long mobileNo) throws VaccineRegistrationException {
		Optional<VaccineRegistration> vac = vacRegRep.findById(mobileNo);
		if(vac.get()==null) {
			throw new VaccineRegistrationException("No Vaccine Registrations found.");
		}
		return vac.get();
	}

	@Override
	public Member getMemberByMobile(Long mobileNo) throws VaccineRegistrationException {
		Member list = vacRegRep.getMemberByMobileNo(mobileNo);
		
		if(list==null) {
			throw new VaccineRegistrationException("No Members Found with mobile no. : "+mobileNo);
		}
		return list;
	}

	@Override
	public VaccineRegistration addVaccineRegistration(VaccineRegistration reg) {
		List<Member> memberList = reg.getMembers();
		for(Member member : memberList) {
			member.setVaccineRegistration(reg);
		}
		return vacRegRep.save(reg);
	}

	@Override
	public VaccineRegistration updateVaccineRegistration(VaccineRegistration reg) throws VaccineRegistrationException {
		
		Optional<VaccineRegistration> vac = vacRegRep.findById(reg.getMobileno());
		
		if(vac.isPresent()) {
			return vacRegRep.save(reg);
		}
		else {
			throw new VaccineRegistrationException("No Registered member with details: "+reg);
		}
	}

	@Override
	public Boolean deleteVaccineRegistration(VaccineRegistration reg) throws VaccineRegistrationException {
		Optional<VaccineRegistration> vac = vacRegRep.findById(reg.getMobileno());
		
		if(vac.isPresent()) {
			vacRegRep.delete(reg);
			return true;
		}else {
			throw new VaccineRegistrationException("No Data Found with the registration: "+reg);
		}
		
	}
}