package com.management.library.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.management.library.entities.User;
import com.management.library.repositories.UserRepository;
import com.management.library.services.SecurityService;

import io.swagger.annotations.Api;


@Controller
public class UserController {
	
	@Autowired
	private BCryptPasswordEncoder encoder;

	@Autowired
	private UserRepository repository;
	
	@Autowired
	private SecurityService securityService;
	
	@GetMapping("/")
	public String showRegistrationPage() {	
		return "registerUser";
	}
	
	@PostMapping(value="/registerUser")
	public String register(@ModelAttribute User user ){
		//user.setPassword(encoder.encode(user.getPassword()));
		user.setPassword(encoder.encode(user.getPassword()));
		repository.save(user);		
		return "login";
	}
	
	@GetMapping("/login")
	public String register() {
		return "login";
	}
	
	@PostMapping(value="/login")
	public String login(@RequestParam("email") String email , @RequestParam("password") String password, Model model) {
		boolean loginResponse = securityService.login(email, password);
		
		if(loginResponse) {
			return "home";
		}
		else {
			model.addAttribute("msg", "Invalid username or password. Please try again!");
		}
		return "login";
	}
	
}
