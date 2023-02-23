package com.masai.service;

import com.masai.DTO.LoginDTO;
import com.masai.exception.LoginException;

public interface LoginService {

	public String logIntoAccount(LoginDTO dto)throws LoginException;

	public String logOutFromAccount(String userKey)throws LoginException;

}
