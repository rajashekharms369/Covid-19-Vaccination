package com.masai.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.masai.DTO.VaccineDTO;
import com.masai.model.Member;

public interface MemberServiceRepository extends JpaRepository<Member,Integer > {

	
	
}
