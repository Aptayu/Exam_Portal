package com.exam.entities;

import org.springframework.security.core.GrantedAuthority;

public class Authority implements GrantedAuthority{
	
	private String authority;

	public Authority(String authority) {
		super();
		this.authority = authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}

	@Override
	public String getAuthority() {
		// TODO Auto-generated method stub
		return this.authority;
	}
	
	
	
}
