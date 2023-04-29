package com.exam.controller;

import javax.security.auth.login.CredentialNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.exam.Utils.JwtUtils;
import com.exam.entities.JwtRequest;
import com.exam.entities.JwtResponse;
import com.exam.serviceImpl.UserDetailsServiceImpl;


@RestController
public class AutenticateController {
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private UserDetailsServiceImpl userDetailsService;
	
	@Autowired
	private JwtUtils jwtUtils;
	
//	generate Token
	@PostMapping("/generate-token")
	public ResponseEntity<?> generateToken(@RequestBody JwtRequest jwtRequest) throws Exception{
		
		try {
			authenticate(jwtRequest.getUsername(), jwtRequest.getPassword());
		}
		catch(CredentialNotFoundException e){
			e.printStackTrace();
			throw new Exception("User not found");
		}
		
//		user authenticated 
		UserDetails userDetails = this.userDetailsService.loadUserByUsername(jwtRequest.getUsername());
		String token = this.jwtUtils.generateToken(userDetails);
		
		return ResponseEntity.ok(new JwtResponse(token));
		
		
		
	}
	
	private void authenticate(String username, String password) throws Exception {
		
		try {
			this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		}
		catch(DisabledException e){
			throw new Exception("USER DISABLED"+ e.getMessage());
		}
		catch(BadCredentialsException e) {
			throw new Exception("Invalid Crdentials "+ e.getMessage());
		}
		
	}

}
