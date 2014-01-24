package com.harpy.hag.vm;

public class Login extends VM{

	private String j_username;
	private String j_password;		
	
	public Login() {
		this.setPageTitle("Login");
	}
	
	public String getJ_username() {
		return j_username;
	}
	public void setJ_username(String j_username) {
		this.j_username = j_username;
	}
	public String getJ_password() {
		return j_password;
	}
	public void setJ_password(String j_password) {
		this.j_password = j_password;
	}
		
}
