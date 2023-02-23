package com.masai.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.masai.DTO.LoginDTO;
import com.masai.service.LoginService;

@RestController
public class LoginController {
	
	@Autowired
	private LoginService loginService;
	
	
	
//	===================== LogIN AND LogOUT Logic ===============
	
	@PostMapping(value = "/login")
	public ResponseEntity<String> logInMember(@RequestBody LoginDTO login){
		String memberLogin = loginService.logIntoAccount(login);
		
		return new ResponseEntity<>(memberLogin,HttpStatus.OK);
	}
	
	

	@PostMapping(value = "/logout/{UserKey}")
	public ResponseEntity<String> logOutMember(@PathVariable("UserKey") String userkey){
		String memberLogout = loginService.logOutFromAccount(userkey);
		
		return new ResponseEntity<>(memberLogout,HttpStatus.OK);
	}
	
}
