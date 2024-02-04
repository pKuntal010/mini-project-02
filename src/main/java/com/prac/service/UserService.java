package com.prac.service;

import java.util.Map;

import com.prac.dto.LoginDTO;
import com.prac.dto.RegistrationDTO;
import com.prac.dto.ResetPwdDTO;
import com.prac.entity.User;

public interface UserService {

	public Map<Integer, String> getCountries();
	public Map<Integer, String> getStates(Integer countryId);
	public Map<Integer, String> getCities(Integer stateId);
	public User getUser(String userEmail);
	public boolean saveUser(RegistrationDTO registrationDTO);
	public User login(LoginDTO loginDTO);
	public boolean resetPwd(ResetPwdDTO resetPwdDTO);
	
	
}
