package com.exam;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
	
import com.exam.entities.Role;
import com.exam.entities.User;
import com.exam.entities.UserRole;
import com.exam.service.UserService;

;

@SpringBootApplication
public class ExamPortal1Application implements CommandLineRunner {
	
	@Autowired
	private UserService userservice;

	public static void main(String[] args) {
		SpringApplication.run(ExamPortal1Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("Application start point");
		User user = new User();
		user.setEmail("abc@gmail.com");
		user.setEnabled(true);
		user.setFirstname("Arpit");
		user.setLastname("Gautam");
		user.setPassword("abc");
		user.setProfile("default.png");
		
		Set<UserRole> userRoleset = new HashSet<>();
		UserRole userRole = new UserRole();
		Role role = new Role();
		role.setRole_id(11);
		role.setRole_name("Admin");
		userRole.setRole(role);
		userRole.setUser(user);
		
		userRoleset.add(userRole);
		
		this.userservice.createUser(user, userRoleset);
		System.out.println("USER IS CREATED WITH NAME " + user.getFirstname());
	}

}
