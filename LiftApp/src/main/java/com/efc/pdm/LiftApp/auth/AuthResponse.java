package com.efc.pdm.LiftApp.auth;

public class AuthResponse {
	private String email;
	private String accesToken;
	private Integer id;
	
	public AuthResponse() {
		
	}

	public AuthResponse(String email, String accesToken, Integer id) {
		this.email = email;
		this.accesToken = accesToken;
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAccesToken() {
		return accesToken;
	}

	public void setAccesToken(String accesToken) {
		this.accesToken = accesToken;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	
}
