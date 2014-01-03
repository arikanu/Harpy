package com.harpy.hag.authentication;

public class SqlAuthentication {

	public static String qUserByEmail(String emailAddress) {
		return "from User where emailAddress = '" + emailAddress + "'";
	}
	
	public static String qUserByEmailPassword(String emailAddress, String password) {
		return "from User where emailAddress = '" + emailAddress + "' and password = '" + password + "'";
	}
	
	
}
