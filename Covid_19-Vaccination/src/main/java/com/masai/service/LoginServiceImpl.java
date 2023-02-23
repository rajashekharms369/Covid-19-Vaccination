package com.masai.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.DTO.LoginDTO;
import com.masai.exception.LoginException;
import com.masai.model.Login;
import com.masai.model.LoginUserSession;
import com.masai.repository.LoginRepository;
import com.masai.repository.RegistrationRepository;

import net.bytebuddy.utility.RandomString;

@Service
public class LoginServiceImpl implements LoginService {
	
	@Autowired
	private LoginRepository loginRepository;
	
	@Autowired
	private RegistrationRepository registrationRepository;

	@Override
	public String logIntoAccount(LoginDTO dto) throws LoginException {
		
		Optional<Login> optional = registrationRepository.findByMobileNo(dto.getMobileNo());
		if(optional.isEmpty()) {
			throw new LoginException("Please enter valid mobile number...");
		}
		
		Optional<LoginUserSession> usersession = loginRepository.findById(optional.get().getMemberID());
		if(usersession.isPresent()) {
			throw new LoginException("User already logged in this number...");
		}
		Login login = optional.get();
		if(optional.get().getPassword().equals(dto.getPassword())) {
			String userKey = RandomString.make(6);
			LoginUserSession loginsession = new LoginUserSession(login.getMemberID(), userKey, LocalDateTime.now());
			
			loginRepository.save(loginsession);
			
			return "Login sucessfull "+login.getMemberName();
			
		}else {
			throw new LoginException("Wrong password! try again with right password...");
		}
	}

	@Override
	public String logOutFromAccount(String userKey) throws LoginException {
		Optional<LoginUserSession> optional = loginRepository.findByUserKey(userKey);
		LoginUserSession userSession = optional.get();
		
		if(optional.isEmpty()) {
			throw new LoginException("Wrong userKey! please give right userKry...");
		}
		
		loginRepository.delete(userSession);
		
		return "Logged out...";
		
	}

}
