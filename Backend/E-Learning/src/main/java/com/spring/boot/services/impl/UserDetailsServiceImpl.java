package com.spring.boot.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.spring.boot.entity.User;
import com.spring.boot.repositories.AuthRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private AuthRepository authRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = authRepository.findUserByEmail(username);
		if(user != null) {
			return org.springframework.security.core.userdetails.User.builder()
					.username(user.getEmail())
					.password(user.getPassword())
					.roles(user.getRole().toArray(new String[0]))
					.build();
		}
		throw  new UsernameNotFoundException("User not found with username" + username);
	}

}
