package com.productiv.dto;

public class LoginDTO {

	private String username;
	private String password;
	
	

	public LoginDTO(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "LoginDTO [username=" + this.username + ", password=" + this.password + ", getUsername()="
				+ this.getUsername() + ", getPassword()=" + this.getPassword() + ", getClass()=" + this.getClass()
				+ ", hashCode()=" + this.hashCode() + ", toString()=" + super.toString() + "]";
	}

	
}
