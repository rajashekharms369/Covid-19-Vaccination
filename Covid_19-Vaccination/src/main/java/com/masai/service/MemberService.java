package com.masai.service;

import com.masai.exception.MemberException;
import com.masai.model.Member;
import java.util.List;



public interface MemberService {

	public List<Member> getAllMember();
	
	public Member getMemberById(Integer id) throws MemberException;

	public Member addMember(Member member)throws MemberException;
    
	public Member updateMember(Member member) throws MemberException;
	
	public Boolean deleteMember(Member member)throws MemberException;
	
	public Member getmemberByAadharNumber(Long aadharNumber)throws MemberException;
	
	public Member getmemberByPanNumber(String panNumber)throws MemberException;
	
	
}
