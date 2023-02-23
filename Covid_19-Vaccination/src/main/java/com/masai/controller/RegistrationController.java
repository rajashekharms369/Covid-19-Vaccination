package com.masai.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.masai.DTO.LoginDTO;
import com.masai.model.Login;
import com.masai.service.LoginService;
import com.masai.service.RegistrationService;

@RestController
public class RegistrationController {

	@Autowired
	private LoginService loginService;
	
	@Autowired
	private RegistrationService registrationService;
	
//==========	Register new member =====
	
	@PostMapping(value = "/register")
	public ResponseEntity<Login> registerNewMember(@RequestBody Login login){
		Login saveLogin = registrationService.createCustomer(login);
		
		return new ResponseEntity<>(saveLogin,HttpStatus.CREATED);
	}
	
//	========== Update existing acount ===============
	
	@PutMapping(value = "/register/{userkey}")
	public ResponseEntity<Login> updateExistingMember(@RequestBody Login login,@PathVariable("userkey")String userKey){
		Login saveLogin = registrationService.updateCustomer(login, userKey);
		
		return new ResponseEntity<>(saveLogin,HttpStatus.CREATED);
	}
	
/*
	@PostMapping(value = "/loginmember")
	public ResponseEntity<String> logInMember(@RequestBody LoginDTO login){
		String memberLogin = loginService.logIntoAccount(login);
		
		return new ResponseEntity<>(memberLogin,HttpStatus.OK);
	}
	
	

	@PostMapping(value = "/logout/{UserKey}")
	public ResponseEntity<String> logOutMember(@PathVariable("UserKey") String userkey){
		String memberLogout = loginService.logOutFromAccount(userkey);
		
		return new ResponseEntity<>(memberLogout,HttpStatus.OK);
	}
	*/
	
}
