package com.masai.controller;

import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.masai.exception.MemberException;
import com.masai.model.Member;
import com.masai.service.MemberService;

@RestController
public class MemberServiceController {
	@Autowired
	private MemberService memberService;
	
	@GetMapping("/members")
	public ResponseEntity<List<Member>> getAllMemberHandler(){
		
		List<Member> members=memberService.getAllMember();
		
		
		return new ResponseEntity<>(members,HttpStatus.ACCEPTED);
	}
	@GetMapping("/members/{id}")
	public ResponseEntity<Member> getMemberByidHandler(@PathVariable("id")Integer id ){
		
		 Member member= memberService.getMemberById(id);
		
		
		return new ResponseEntity<Member>(member,HttpStatus.ACCEPTED);
	}
	
	
	
	@PostMapping("/members")
	public ResponseEntity<Member> addMemberHandler(@RequestBody Member member){
		
		
		Member savedMember=memberService.addMember(member);
		if(savedMember==null) {
			throw new MemberException("Please enter member deatil in desired format");
		}
		
		return new ResponseEntity<>(savedMember,HttpStatus.ACCEPTED) ;
	}
	
	
	@PutMapping("/members")
	public ResponseEntity<Member> updateMemberHandler(@RequestBody Member member){
		
		Member updatedMember=memberService.updateMember(member);
		return new ResponseEntity<Member>(member,HttpStatus.ACCEPTED);
	}
	@PutMapping("/deleteMember")
    public ResponseEntity<String> deleteMemberHandler(@RequestBody Member member) {
		
		String responseMsg=null;
    	
    	if(memberService.deleteMember(member)) {
    	responseMsg="Member deleted sucessfully with id : "+member.getMemberId();
    	}
    	else {
			responseMsg="Member does not exits with this Id";
		}
    	
    	
    	return  new ResponseEntity<String>(responseMsg,HttpStatus.ACCEPTED);
    	
    }
	
	@GetMapping("/getMemberByAadharNumber/{aNo}")
	public ResponseEntity<Member> getMemberByAadharNumberUsecase(@PathVariable("aNo") Long aNo ){
		
		
		Member member=memberService.getmemberByAadharNumber(aNo);
		
		return  new ResponseEntity<Member>(member,HttpStatus.ACCEPTED);
		
		
		
		
		
	}
	
	
	@GetMapping("/getMemberPanNumber/{pNo}")
	public ResponseEntity<Member> getMemberByPanNumber(@PathVariable("pNo") String pNo){
		
		 Member existingMember =  memberService.getmemberByPanNumber(pNo);
		
		return  new ResponseEntity<Member>(existingMember,HttpStatus.ACCEPTED);
	}
	
	
}
