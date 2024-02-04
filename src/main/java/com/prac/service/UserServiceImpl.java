package com.prac.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prac.dto.LoginDTO;
import com.prac.dto.RegistrationDTO;
import com.prac.dto.ResetPwdDTO;
import com.prac.entity.City;
import com.prac.entity.Country;
import com.prac.entity.State;
import com.prac.entity.User;
import com.prac.repository.CityRepository;
import com.prac.repository.CountryRepository;
import com.prac.repository.StateRepository;
import com.prac.repository.UserRepository;
import com.prac.util.EmailUtils;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private CityRepository cityRepository;
	@Autowired
	private StateRepository stateRepository;
	@Autowired
	private CountryRepository countryRepository;
	@Autowired
	private EmailUtils emailUtils;
	
	@Override
	public Map<Integer, String> getCountries() {
		// TODO Auto-generated method stub
		List<Country> country = countryRepository.findAll();
		Map<Integer, String> countries = new HashMap<>();
		country.forEach(c -> {
			countries.put(c.getCountryId(), c.getCountryName());
		});
		return countries;
	}

	@Override
	public Map<Integer, String> getStates(Integer countryId) {
		List<State> state = stateRepository.findStatesByCountryId(countryId);
		Map<Integer, String> states = new HashMap<>();
		state.forEach(s -> {
			states.put(s.getStateId(), s.getStateName());
		});
		return states;
	}

	@Override
	public Map<Integer, String> getCities(Integer stateId) {
		List<City> city = cityRepository.findCitiesByStateId(stateId);
		Map<Integer, String> cities = new HashMap<>();
		city.forEach(c -> {
			cities.put(c.getCityId(), c.getCityName());
		});
		return cities;
	}

	@Override
	public User getUser(String userEmail) {
		// This method is used to check whether data is available with the given email id or not.
		return userRepository.findByUserEmail(userEmail);
	}

	@Override
	public boolean saveUser(RegistrationDTO registrationDTO) {
		registrationDTO.setPwd(generateRandomPwd());
		registrationDTO.setPwdUpdated("NO");
		User user = new User();
		BeanUtils.copyProperties(registrationDTO, user);
		
		userRepository.save(user);
		String subject = "Your account has been created.";
		String body = "Your password : " + registrationDTO.getPwd();
		return emailUtils.sendEmail(subject, body, registrationDTO.getUserEmail());
	}

	private String generateRandomPwd() {
		String alphanumericCharacters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789abcdefghijklmnopqrstuv";
		 
	    StringBuffer randomString = new StringBuffer(6);
	    Random random = new Random();
	 
	    for (int i = 0; i < 6; i++) {
	        int randomIndex = random.nextInt(alphanumericCharacters.length() - 1);
	        char randomChar = alphanumericCharacters.charAt(randomIndex);
	        randomString.append(randomChar);
	    }
	 
	    return randomString.toString();
	}

	@Override
	public User login(LoginDTO loginDTO) {
		return userRepository.findByUserEmailAndPwd(loginDTO.getUserEmail(), loginDTO.getPwd());
		
	}

	@Override
	public boolean resetPwd(ResetPwdDTO resetPwdDTO) {
		Optional<User> findById = userRepository.findById(resetPwdDTO.getUserId());
		if (findById.isPresent()) {
			User user = findById.get();
			user.setPwd(resetPwdDTO.getNewPwd());
			user.setPwdUpdated("YES");
			userRepository.save(user);
			return true;
		}
		return false;
	}

}
