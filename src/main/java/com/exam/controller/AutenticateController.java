package com.exam.controller;

import java.lang.System.Logger;
import java.security.Principal;

import javax.security.auth.login.CredentialNotFoundException;

import org.hibernate.annotations.common.util.impl.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.exam.Utils.JwtUtils;
import com.exam.Utils.LoggerUtil;
import com.exam.entities.JwtRequest;
import com.exam.entities.JwtResponse;
import com.exam.entities.User;
import com.exam.serviceImpl.UserDetailsServiceImpl;


@RestController
@CrossOrigin
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
		System.out.println(jwtRequest.getUsername());
		try {
			authenticate(jwtRequest.getUsername(), jwtRequest.getPassword());
		}
		catch(CredentialNotFoundException e){
			e.printStackTrace();
			throw new Exception("User not found");
		}
		
//		user authenticated 
		UserDetails userDetails = this.userDetailsService.loadUserByUsername(jwtRequest.getUsername());
		System.out.println("userdetailsusername = " + userDetails.getUsername());
		String token = this.jwtUtils.generateToken(userDetails.getUsername());
		
		System.out.println("username from token = " + jwtUtils.getUsernameFromToken(token));
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
	
//	returns details of current user
	@GetMapping("/current-user")
	public User getCurrentUser(Principal principal) {
		System.out.println("Inside");
		System.out.println("principal = " + principal);
		JwtResponse jwtresponse = new JwtResponse();
		System.out.println("Token=" + jwtresponse.getToken());
		return (User)this.userDetailsService.loadUserByUsername(principal.getName());
	}

}
