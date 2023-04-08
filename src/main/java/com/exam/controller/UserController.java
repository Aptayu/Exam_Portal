package com.exam.controller;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.exam.entities.Role;
import com.exam.entities.User;
import com.exam.entities.UserRole;
import com.exam.service.UserService;
import com.fasterxml.jackson.annotation.JsonFormat;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userservice;
	
	
	@PostMapping("/")
	public User createUser(@RequestBody User user) throws Exception {
		UserRole userRole = new UserRole();
		Role role = new Role();
		role.setRole_id(11);
		role.setRole_name("Admin");
		userRole.setRole(role);
		userRole.setUser(user);
		
		Set<UserRole> userRoleset = new HashSet<>();
		userRoleset.add(userRole);
		return this.userservice.createUser(user, userRoleset);
	}
	@GetMapping("/{username}")	
	public User getUser(@PathVariable("username") String username) {
		return this.userservice.getUserByUserName(username);
		
	}
	@DeleteMapping("/{userid}")
	public void deleteUser(@PathVariable("userid") long userid) {
		this.userservice.deleteUser(userid);
	}
	@PutMapping("/{userid}")
	public User updateUser(@PathVariable("userid") long userid, @RequestBody User user) throws Exception {
		return this.userservice.updateUser(userid, user);
		
	}

}
