package com.masai.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.exception.LoginException;
import com.masai.model.Login;
import com.masai.model.LoginUserSession;
import com.masai.repository.LoginRepository;
import com.masai.repository.RegistrationRepository;
@Service
public class RegistrationServiceImpl implements RegistrationService{

	@Autowired
	private LoginRepository loginRepository;
	
	@Autowired
	private RegistrationRepository registrationRepository;

	
	@Override
	public Login createCustomer(Login member) throws LoginException {
		Optional<Login> optional = registrationRepository.findByMobileNo(member.getMobileNo());
		
		if(optional.isPresent()) {
			throw new LoginException("Member already registered with this mobile number");
		}
		
		Login login = registrationRepository.save(member);
		
		return login;
	}

	@Override
	public Login updateCustomer(Login member, String userkey) throws LoginException {
		Optional<LoginUserSession> optional = loginRepository.findByUserKey(userkey);
		
		if(optional.isEmpty()) {
			throw new LoginException("Please provide a valid userkey...");
		}
		LoginUserSession userSession = optional.get();
		
		if(userSession.getUserId() == member.getMemberID()) {
			return registrationRepository.save(member);
		}
		else {
			throw new LoginException("Invalid customer detailes...");
		}
		
	
	}

}
