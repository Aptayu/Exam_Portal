package com.exam.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.exam.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {
	User findByUserName(String username);
	
	Optional<User> findById(Long id);


}
