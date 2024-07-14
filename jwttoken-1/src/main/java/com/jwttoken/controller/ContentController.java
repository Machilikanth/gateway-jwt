package com.jwttoken.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.jwttoken.model.MyUser;
import com.jwttoken.repo.MyUserRepository;
import com.jwttoken.service.JwtService;
import com.jwttoken.service.MyUserDetailService;

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

	@PostMapping("/register/user")
	public MyUser postMethodName(@RequestBody MyUser entity) {
		return repository.save(entity);
	}

	@PostMapping("/authenticate")
	public String authenticateAndGetToken(@RequestBody MyUser user) {
		Optional<MyUser> entity = repository.findByMobileNumber(user.getMobileNumber());
		if (entity.isPresent()) {
			System.out.println("111111111"+myUserDetailService.loadUserByMobileNumber(user.getMobileNumber()));  
			return jwtService.generateToken(myUserDetailService.loadUserByMobileNumber(user.getMobileNumber()));
		} else {
			throw new UsernameNotFoundException("Invalid credentials");
		}
	}

	@GetMapping("/all/login")
	public boolean getAllUsers() {
		return true;
	}

}
