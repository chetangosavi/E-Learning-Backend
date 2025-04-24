package com.spring.boot.services;


import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.spring.boot.entity.User;
import com.spring.boot.repositories.AuthRepository;

@Service
public class AuthService {

	@Autowired
	private AuthRepository authRepository;
	
	private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	
	public void saveUser(User user) {
		authRepository.save(user);
	}
	
	public ResponseEntity<?> registerUser(User user) {
		
		try {
			String email = user.getEmail().toLowerCase();
			user.setEmail(email);
			user.setPassword(passwordEncoder.encode(user.getPassword()));
			user.setRole(Arrays.asList("USER"));  
			authRepository.save(user);
			return new ResponseEntity<User>(HttpStatus.CREATED);
			
		} catch (Exception e) {
			return new ResponseEntity<>("Something Went Wrong",HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
			
	}
}
