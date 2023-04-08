package com.exam.service;

import java.util.Set;

import com.exam.entities.User;
import com.exam.entities.UserRole;


public interface UserService {
//	create user
	public User createUser(User user, Set<UserRole> userRoles) throws Exception;
//get user
	public User getUserByUserName(String username);
//delete user by id
	public void deleteUser(Long userId);
//	update user
	public User updateUser(Long userid, User user) throws Exception;
}
