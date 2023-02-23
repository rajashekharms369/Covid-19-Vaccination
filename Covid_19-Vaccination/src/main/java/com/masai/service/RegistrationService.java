package com.masai.service;

import com.masai.exception.LoginException;
import com.masai.model.Login;

public interface RegistrationService {

public Login createCustomer(Login  member)throws LoginException;
	
	public Login updateCustomer(Login member,String userkey)throws LoginException;

}
