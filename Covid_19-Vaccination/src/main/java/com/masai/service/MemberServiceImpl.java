package com.masai.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.DTO.AppointmentDTO;
import com.masai.DTO.VaccineDTO;
import com.masai.exception.AppointmentException;
import com.masai.exception.MemberException;
import com.masai.model.Appointment;
import com.masai.model.Member;
import com.masai.model.Slot;
import com.masai.model.Vaccine;
import com.masai.model.VaccineCount;
import com.masai.model.VaccineRegistration;
import com.masai.repository.AppointmentRepository;
import com.masai.repository.IdCardRepository;
import com.masai.repository.MemberServiceRepository;
import com.masai.repository.VaccineRegistrationRepository;
import com.masai.repository.VaccineRepository;

@Service
public class MemberServiceImpl implements MemberService{
	
@Autowired
private	MemberServiceRepository memberServiceRepository;
@Autowired
private IdCardRepository  idCardRepository;

@Autowired
private VaccineRegistrationRepository vaccineRegistrationRepository;

@Autowired
private VaccineRepository vaccineRepository;

@Autowired
private AppointmentRepository appointmentRepository;
	
	@Override
	public List<Member> getAllMember() {
		
		
		List<Member> members=memberServiceRepository.findAll();
		
		
		
		return members;
	}

	@Override
	public Member getMemberById(Integer id)throws MemberException {
		
		Optional<Member> optionalMeber=memberServiceRepository.findById(id);
		
		if(optionalMeber.isPresent()) {
			Member member=optionalMeber.get();
			return member;
		}else {
			throw new MemberException("Member does not exist with the id : "+id);
		}
		
		
		
	}

	@Override
	public Member addMember(Member member) throws MemberException {
	   
		Member savedMember=memberServiceRepository.save(member);
		
		return savedMember;
	}

	@Override
	public Member updateMember(Member member) throws MemberException {
		Optional<Member> optionalMember=memberServiceRepository.findById(member.getMemberId());
		
		if(optionalMember.isPresent()) {
			Member existigMember=optionalMember.get();
			Member updatedMember=memberServiceRepository.save(member);
			return updatedMember;
		}else {
			throw new MemberException("Please provide correct member Detail for update...");
		}
		
		
	}

	@Override
	public Boolean deleteMember(Member member) throws MemberException {
		
		Member existingMember=memberServiceRepository.findById(member.getMemberId()).get();
	     
		if(existingMember==null) {
			throw new MemberException("Member does not exits with this roll");
		}
		else {
			memberServiceRepository.delete(existingMember);
			return true;
		}
		
		
		
		
	}

	@Override
	public Member getmemberByAadharNumber(Long aadharNumber) throws MemberException {
		
	      List<Member> members=  memberServiceRepository.findAll();
	      
	      for(Member member:members) {
	    	  if(member.getIdCard().getAdharCard().getAdharNo()==aadharNumber) {
	    		  return member;
	    	  }
	      }
	    	  
	      throw new MemberException("Member odes not exits with is Aadhar Number");
	    	  
	      
	      
		
		
		
		
	}

	@Override
	public Member getmemberByPanNumber(String panNumber) throws MemberException {
		
		
		  List<Member> members=  memberServiceRepository.findAll();
		  
	      for(Member member:members) {
	    	  if(member.getIdCard().getPancard().getPanNo().equalsIgnoreCase(panNumber)) {
	    		  return member;
	    	  }
	      }
	      throw new MemberException("Member odes not exits with is PAN Number");
    	  
		
		
	}

//==============	from here code written by Gaurav Shirke - fw21_0985 ====================
	
	@Override
	public List<VaccineDTO> getAllVaccines() throws MemberException {
		List<VaccineDTO> list = new ArrayList<>();
		
		list = vaccineRepository.getAllVaccines();
		
		if(list.isEmpty()) {
			throw new MemberException("No Vaccine available...");
		}else {
			return list;
		}
	}

	@Override
	public String selectVaccineByName(Integer memberId,String vaccineName) throws MemberException {
		
		
		Optional<Member> memberOpt = memberServiceRepository.findById(memberId);
		
		if(memberOpt.isEmpty()) {
			throw new MemberException("Wrong member Id....");
		}
		
		if(memberOpt.get().getVaccineRegistration()==null) {
			throw new MemberException("Please first make Registration of vaccine!");
		}
     
		Optional<Vaccine> optional = vaccineRepository.getVaccineByName(vaccineName);
		
	
		
		if(optional.isEmpty()) {
			throw new MemberException("Wrong vaccine name....");
		}
		
		VaccineCount countOpt = optional.get().getVaccinecount();
		System.out.println(countOpt.getQuantity());
	
		if( countOpt==null|| countOpt.getQuantity()<1) {
			throw new MemberException("Vaccine not available... try after some time.");
		}
		countOpt.setQuantity(countOpt.getQuantity()-1);
		
		optional.get().setVaccinecount(countOpt);
		
		vaccineRepository.save(optional.get());
		
		memberOpt.get().setVaccine(optional.get());
		optional.get().setMember(memberOpt.get());
		memberOpt.get().setVaccineName(vaccineName);
		
		memberServiceRepository.save(memberOpt.get());
		
		return vaccineName+" vaccine selected sucessfully...";
	}

	@Override
	public String registerMemberForVaccination(Integer memberId,String mobileNo) throws MemberException {
		
       Optional<Member> memberOpt = memberServiceRepository.findById(memberId);
		
		if(memberOpt.isEmpty()) {
			throw new MemberException("Wrong member Id....");
		}
		
		if(memberOpt.get().getVaccineRegistration()!=null) {
			throw new MemberException("member already Registered for vaccine!");
		}
		VaccineRegistration vaccineRegistration = new VaccineRegistration();
		vaccineRegistration.setMobileno(mobileNo);
		vaccineRegistration.setDateofregistration(LocalDate.now());
		List<Member> list = new ArrayList<>();
		list.add(memberOpt.get());
		
		vaccineRegistration.setMembers(list);
		
		memberOpt.get().setVaccineRegistration(vaccineRegistration);
		
		memberServiceRepository.save(memberOpt.get());
	
		
		
		return "Vaccine registration is sucessfull, Now you can select vaccine";
	}

	@Override
	public List<AppointmentDTO> getAppointmentsByDate(LocalDate appoiDate) throws MemberException {
		List<AppointmentDTO> appointments = appointmentRepository.getAllAppointmentForMember(appoiDate);
		
		if(appointments.isEmpty()) {
			throw new MemberException("No appointments available for date: "+appoiDate);
		}
		
		
		return appointments;
	}

	@Override
	public Appointment selectAppointment(Integer memberId,Long appointmentId, Slot slot) throws MemberException {
		  Optional<Member> memberOpt = memberServiceRepository.findById(memberId);
			
			if(memberOpt.isEmpty()) {
				throw new MemberException("Wrong member Id....");
			}
		
			if(memberOpt.get().getDose1Date()!=null && memberOpt.get().getDose2Date()!=null ) {
				throw new MemberException("You completed your both dose...");
			}
			
			
			
		Optional<Appointment> optional = appointmentRepository.getAppointmentByBookingId(appointmentId);
		
		List<Appointment> list = appointmentRepository.getAllAppointmentsInDate(optional.get().getDateOfBooking());
		int count=0;
		for(Appointment app:list) {
			if(app.getSlot()!=null) {
				count++;
			}
		}
		if(count==9) {
			throw new MemberException("All slotes are bookrd for this date....");
		}
		for(Appointment app:list) {
			if(app.getSlot()==slot) {
				throw new MemberException("Slot is booked choose another slot...");
			}
		}
		
		if(optional.isEmpty()) {
			throw new MemberException("Wrong appointmentId...");
		}
	
		Appointment appointment = optional.get();
		
	
		
		
		if(appointment.getMobileNo()!=null) {
			throw new MemberException("Appointment not available...");
		}
		
		String mobile = memberOpt.get().getVaccineRegistration().getMobileno();
		appointment.setMobileNo(mobile);
		appointment.setSlot(slot);
		appointment.setBookingStatus(true);
		appointment.setMember(memberOpt.get());
		memberOpt.get().getAppointments().add(appointment);
		
		if(memberOpt.get().getDose1Date()!=null && memberOpt.get().getDose2Date()==null ) {
			memberOpt.get().setDose2Date(appointment.getDateOfBooking());
			memberOpt.get().setDose2Status(true);
		}
		
		if(memberOpt.get().getDose1Date()==null && memberOpt.get().getDose2Date()==null ) {
			memberOpt.get().setDose1Date(appointment.getDateOfBooking());
			memberOpt.get().setDose1Status(true);
		}
		
		
		
		memberServiceRepository.save(memberOpt.get());
		
		return appointment;
	}


}
