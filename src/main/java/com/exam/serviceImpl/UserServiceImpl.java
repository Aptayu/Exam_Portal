package com.exam.serviceImpl;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exam.entities.Role;
import com.exam.entities.User;
import com.exam.entities.UserRole;
import com.exam.repository.RoleRepository;
import com.exam.repository.UserRepository;
import com.exam.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepo;
	@Autowired
	private RoleRepository roleRepo;
	
	@Override
	public User createUser(User user, Set<UserRole> userRoles) throws Exception {
		// TODO Auto-generated method stub
//		take help from user Repo to do any database related things
//		check if user is already there
		User local = this.userRepo.findByUserName(user.getUserName());
		if(local != null) {
			System.out.println("user is already there");
			throw new Exception("user already present");
		}else {
//			will create user
			for(UserRole ur : userRoles) {
				Role role = roleRepo.save(ur.getRole());
			}
			user.getUserRole().addAll(userRoles);
			userRepo.save(user);
		}
		
		return null;
	}
}
