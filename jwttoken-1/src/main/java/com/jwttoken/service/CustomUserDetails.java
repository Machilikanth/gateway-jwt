package com.jwttoken.service;

import java.util.Collection;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.jwttoken.model.MyUser;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Configuration
@NoArgsConstructor
@AllArgsConstructor
public class CustomUserDetails implements UserDetails {

	private static final long serialVersionUID = -4630514114427391542L;
	private String username;
	private String mobileNumber;
	private String email;

	public CustomUserDetails(MyUser user) {
		this.username = user.getUsername();
		this.mobileNumber = user.getMobileNumber();
		this.email = user.getEmail();
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return null; // Implement authorities if needed
	}

	@Override
	public String getPassword() {
		return null; // No password required
	}

	@Override
	public String getUsername() {
		return username;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public String getEmail() {
		return email;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
}
