package com.exam.entities;

import javax.persistence.*;

import lombok.*;

@Entity
@Getter @Setter @NoArgsConstructor
public class UserRole {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@ManyToOne
	private User user;
	@ManyToOne
	private Role role;

}