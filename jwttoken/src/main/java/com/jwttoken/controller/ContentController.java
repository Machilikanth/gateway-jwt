package com.jwttoken.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.jwttoken.model.MyUser;
import com.jwttoken.repo.MyUserRepository;
import com.jwttoken.service.MyUserDetailService;
import com.jwttoken.webtoken.JwtService;
import com.jwttoken.webtoken.LoginForm;

@RestController
public class ContentController {

	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private JwtService jwtService;
	@Autowired
	private MyUserDetailService myUserDetailService;

	@Autowired
	private MyUserRepository repository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@GetMapping("/home")
	public String handleWelcome() {
		return "Welcome to home!";
	}

	@GetMapping("/admin/home")
	public String handleAdminHome() {
		return "Welcome to ADMIN home!";
	}

	@GetMapping("/user/home")
	public String handleUserHome() {
		return "Welcome to USER home!";
	}

	@GetMapping("/login")
	public String handleLogin() {
		return "custom_login";
	}

	@PostMapping("/register/user")
	public MyUser postMethodName(@RequestBody MyUser entity) {
		entity.setPassword(passwordEncoder.encode(entity.getPassword()));
		return repository.save(entity);
	}
	
//this is for generating a token
//	@PostMapping("/authenticate")
//	public String authenticateAndGetToken(@RequestBody MyUser user) {
//		Authentication authentication = authenticationManager
//				.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
//		if (authentication.isAuthenticated()) {
//			return jwtService.generateToken(myUserDetailService.loadUserByUsername(user.getUsername()));
//		} else {
//			throw new UsernameNotFoundException("Invalid credentials");
//		}
//	}
	@PostMapping("/authenticate")
	public String authenticateAndGetToken(@RequestBody LoginForm loginForm) {
		Authentication authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(loginForm.username(), loginForm.password()));
		if (authentication.isAuthenticated()) {
			return jwtService.generateToken(myUserDetailService.loadUserByUsername(loginForm.username()));
		} else {
			throw new UsernameNotFoundException("Invalid credentials");
		}
	}

}
