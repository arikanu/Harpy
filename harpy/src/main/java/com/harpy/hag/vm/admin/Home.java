package com.harpy.hag.vm.admin;

import com.harpy.hag.authentication.LoginAuthentication;
import com.harpy.hag.db.entities.user.User;
import com.harpy.hag.vm.VM;

public class Home extends VM {
	
	private User user;
	
	public Home(String username) {
		this.setPageTitle("Admin-Home");
		this.user = LoginAuthentication.getUser(username);
	}
			
}
