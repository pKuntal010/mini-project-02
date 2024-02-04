package com.prac.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.prac.dto.Quote;
import com.prac.service.DashboardService;

@Controller
public class DashboardController {
	@Autowired
	private DashboardService dashboardService;
	
	@GetMapping("/dashboard")
	public String buildDashboard(Model model) {
		String quote = dashboardService.getQuote();
		model.addAttribute("quote", quote);
		return "dashboard";
	}
	
	
}
