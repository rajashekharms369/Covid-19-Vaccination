package com.masai.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.masai.exception.MemberException;
import com.masai.model.Member;
import com.masai.repository.MemberServiceRepository;

public class MemberServiceImpl implements MemberService{
	
@Autowired
private	MemberServiceRepository memberServiceRepository;
	
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


}
