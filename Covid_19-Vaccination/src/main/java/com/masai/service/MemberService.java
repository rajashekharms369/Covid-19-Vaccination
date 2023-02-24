package com.masai.service;

import com.masai.DTO.AppointmentDTO;
import com.masai.DTO.VaccineDTO;
import com.masai.exception.MemberException;
import com.masai.model.Appointment;
import com.masai.model.Member;
import com.masai.model.Slot;
import com.masai.model.Vaccine;

import java.time.LocalDate;
import java.util.List;



public interface MemberService {

	public List<Member> getAllMember();
	
	public Member getMemberById(Integer id) throws MemberException;

	public Member addMember(Member member)throws MemberException;
    
	public Member updateMember(Member member) throws MemberException;
	
	public Boolean deleteMember(Member member)throws MemberException;
	
	public Member getmemberByAadharNumber(Long aadharNumber)throws MemberException;
	
	public Member getmemberByPanNumber(String panNumber)throws MemberException;
	
	public List<VaccineDTO> getAllVaccines()throws MemberException;
	
	public String selectVaccineByName( Integer memberId,String vaccineName) throws MemberException;
	
	public String registerMemberForVaccination(Integer memberId,String mobileNo) throws MemberException;
	
	public List<AppointmentDTO> getAppointmentsByDate(LocalDate appoiDate)throws MemberException;
	
	public Appointment selectAppointment(Integer memberId,Long appointmentId, Slot slot)throws MemberException;
	
}
