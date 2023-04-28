package com.efc.pdm.LiftApp.auth;

import org.hibernate.validator.constraints.Length;

public class AuthRequest {
	@Length(max=50)
	private String email;
	@Length(max=15)
	private String password;
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
	
}
