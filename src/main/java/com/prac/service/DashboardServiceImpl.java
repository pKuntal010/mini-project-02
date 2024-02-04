package com.prac.service;

import java.util.List;
import java.util.Random;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.prac.dto.Quote;

@Service
public class DashboardServiceImpl implements DashboardService{

	private String quoteUrl = "https://type.fit/api/quotes";
	private Quote[] quote = null;
	private Random random = new Random();
	
	@Override
	public String getQuote() {
		// TODO Auto-generated method stub
		String txt = "";
		
		if (quote == null) {
			RestTemplate rt = new RestTemplate();
			ResponseEntity<String> forEntity = rt.getForEntity(quoteUrl, String.class);
			String body = forEntity.getBody();
			
			ObjectMapper mapper = new ObjectMapper();
			
			try {
				quote = mapper.readValue(body, Quote[].class);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
		}else {
			int nextInt = random.nextInt(quote.length-1);
			txt = quote[nextInt].getText();
		}
		return txt;
	}
	

}
