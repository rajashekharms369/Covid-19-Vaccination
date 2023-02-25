package com.masai.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.masai.model.Member;
import com.masai.model.VaccineRegistration;

public interface VaccineRegistrationRepository extends JpaRepository<VaccineRegistration, Long>{
	
	@Query("select r.members from VaccineRegistration r where r.mobileno=?1")
	public Member getMemberByMobileNo(Long mobileNo);
	
	public Optional<VaccineRegistration> findByMobileno(String mobileNo);
	
}