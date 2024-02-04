package com.prac.util;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.prac.entity.City;
import com.prac.entity.Country;
import com.prac.entity.State;
import com.prac.repository.CityRepository;
import com.prac.repository.CountryRepository;
import com.prac.repository.StateRepository;

@Component
public class DataLoader implements ApplicationRunner{

	@Autowired
	private CountryRepository countryRepository;
	@Autowired
	private CityRepository cityRepository;
	@Autowired
	private StateRepository stateRepository;
	
	@Override
	public void run(ApplicationArguments args) throws Exception {
		cityRepository.deleteAll();
		countryRepository.deleteAll();
		stateRepository.deleteAll();
		
		Country c1 = new Country(1, "India");
		Country c2 = new Country(2, "USA");
		
		countryRepository.saveAll(Arrays.asList(c1, c2));
		
		State s1 = new State(1, "Odisha", 1);
		State s2 = new State(2, "UP", 1);
		State s3 = new State(3, "New Jersey", 2);
		State s4 = new State(4, "Florida", 2);
		
		stateRepository.saveAll(Arrays.asList(s1, s2, s3, s4));
		
		City c11 = new City(1, "Bhubaneshwar", 1);
		City c22 = new City(2, "Cuttack", 1);
		City c33 = new City(3, "Noida", 2);
		City c44 = new City(4, "Ayodha", 2);
		City c55 = new City(5, "Brooklyn1", 3);
		City c66 = new City(6, "Brooklyn2", 3);
		City c77 = new City(7, "NY1", 4);
		City c88 = new City(8, "NY2", 4);
		
		cityRepository.saveAll(Arrays.asList(c11,c22,c33,c44,c55,c66,c77,c88));
		
		
	}

}
