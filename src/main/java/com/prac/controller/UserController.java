package com.prac.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.prac.dto.LoginDTO;
import com.prac.dto.RegistrationDTO;
import com.prac.dto.ResetPwdDTO;
import com.prac.entity.User;
import com.prac.service.UserService;

@Controller
public class UserController {
	@Autowired
	private UserService userService;
	
	
	@GetMapping("/")
	public String index(Model model) {
		model.addAttribute("login", new LoginDTO());
		return "index";
	}
	@PostMapping("/login")
	public String login(@ModelAttribute("login") LoginDTO loginDTO, Model model) {
		User user = userService.login(loginDTO);
		if (user == null) {
			model.addAttribute("errMsg", "Invalid credentials");
			return "index";
		}
		if (user.getPwdUpdated().equals("NO")) {
			ResetPwdDTO resetPwdDTO = new ResetPwdDTO();
			resetPwdDTO.setUserId(user.getUserId());
			model.addAttribute("resetPwd", resetPwdDTO);
			return "resetPwd";
		}
		return "redirect:dashboard";
	}
	@GetMapping("/register")
	public String register(Model model) {
		model.addAttribute("register", new RegistrationDTO());
		Map<Integer, String> countries = userService.getCountries();
		model.addAttribute("countries", countries);
		return "register";
	}
    @GetMapping("/getStates")
    @ResponseBody
	public Map<Integer, String> loadStates(@RequestParam("countryId") Integer countryId){
		
		return userService.getStates(countryId);
	}
    @GetMapping("/getCities")
    @ResponseBody
	public Map<Integer, String> loadCities(@RequestParam("stateId") Integer stateId){
		
		return userService.getCities(stateId);
	}
    @PostMapping("/register")
	public String userRegister(@ModelAttribute("register") RegistrationDTO registrationDTO, Model model) {
		boolean saveUser = userService.saveUser(registrationDTO);
		if (saveUser) {
			model.addAttribute("sMsg", "Successfully registered");
			Map<Integer, String> countries = userService.getCountries();
			model.addAttribute("countries", countries);
			return "register";
		}
		model.addAttribute("errMsg", "Registration failed");
		
		Map<Integer, String> countries = userService.getCountries();
		model.addAttribute("countries", countries);
		return "register";
	}
	@PostMapping("/updatePwd")
	public String updatePwd(@ModelAttribute("resetPwd") ResetPwdDTO resetPwdDTO, Model model) {
		if (!resetPwdDTO.getNewPwd().equals(resetPwdDTO.getConfirmPwd())) {
			model.addAttribute("erMsg", "Both password should match");
			return "resetPwd";
		}
		
		boolean status = userService.resetPwd(resetPwdDTO);
		if (status) {
			return "redirect:dashboard";
		}

		model.addAttribute("errMsg", "Update failed");
		return "resetPwd";
	}
	

}
