package com.masai.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.masai.model.Member;

public interface MemberServiceRepository extends JpaRepository<Member,Integer > {

	
	
}
