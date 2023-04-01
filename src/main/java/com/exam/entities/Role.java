package com.exam.entities;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;


@Entity
@Table(name = "Roles")
public class Role {
	@Id
	private long role_id;
	private String role_name;
	@OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY, mappedBy = "role")
	private Set<UserRole> userRole = new HashSet<>();
	
	public Role() {	
		super();
		this.userRole = new HashSet<>();
		// TODO Auto-generated constructor stub
	}
	
	public Role(long role_id, String role_name, Set<UserRole> userRole) {
		super();
		this.role_id = role_id;
		this.role_name = role_name;
		this.userRole = userRole;
	}

	public Set<UserRole> getUserRole() {
		return userRole;
	}
	public void setUserRole(Set<UserRole> userRole) {
		this.userRole = userRole;
	}
	public long getRole_id() {
		return role_id;
	}
	public void setRole_id(long role_id) {
		this.role_id = role_id;
	}
	public String getRole_name() {
		return role_name;
	}
	public void setRole_name(String role_name) {
		this.role_name = role_name;
	}

	

}
