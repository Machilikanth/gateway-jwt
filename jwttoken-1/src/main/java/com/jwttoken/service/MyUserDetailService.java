package com.jwttoken.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.jwttoken.model.MyUser;
import com.jwttoken.repo.MyUserRepository;

@Service
public class MyUserDetailService implements UserDetailsService {

	@Autowired
	private MyUserRepository repository;

	public UserDetails loadUserByMobileNumber(String mobileNumber) throws UsernameNotFoundException {
		Optional<MyUser> user = repository.findByMobileNumber(mobileNumber);
		if (user.isPresent()) {
			return new CustomUserDetails(user.get());
		} else {
			throw new UsernameNotFoundException("User not found with mobile number: " + mobileNumber);
		}
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// Implement based on username if needed
		return null;
	}
}
