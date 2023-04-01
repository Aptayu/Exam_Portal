package com.exam.entities;



import java.util.HashSet;
import java.util.Set;


import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name ="users")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	@Column(name = "user_id")
	private long id;
	private String userName;
	private String password;
	private String email;
	private String firstname;
	private String lastname;
	private boolean enabled = true;
	private String profile;
//	cascade all so that if we delet user here userrole will also deleted and \fetchtype eager means when user gets pulled
//	userrole also gets pulled, mapped by other entity will take care of column generation
	@OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER, mappedBy = "user")
	@JsonIgnore
	private Set<UserRole> userRole = new HashSet<>();
	
	
	public User() {
		super();
		Set<UserRole> userRole = new HashSet<>();
	}

	public Set<UserRole> getUserRole() {
		return userRole;
	}

	public void setUserRole(HashSet<UserRole> userRole) {
		this.userRole = userRole;
	}

	public String getProfile() {
		return profile;
	}

	public void setProfile(String profile) {
		this.profile = profile;
	}

	
	public User(long id, String username, String password, String email, String firstname, String lastname,
			boolean enabled) {
		super();
		this.id = id;
		this.userName = username;
		this.password = password;
		this.email = email;
		this.firstname = firstname;
		this.lastname = lastname;
		this.enabled = enabled;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}	

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + userName + ", password=" + password + ", email=" + email
				+ ", firstname=" + firstname + ", lastname=" + lastname + ", enabled=" + enabled + "]";
	}
	
	
	
	
	
	
	

}
