package com.harpy.hag.vm.admin;

import com.harpy.hag.authentication.LoginAuthentication;
import com.harpy.hag.db.entities.user.User;
import com.harpy.hag.vm.ViewModel;

public class Home extends ViewModel {
	
	private User user;
	
	public Home(String username) {
		super("Admin/Home");
		this.user = LoginAuthentication.getUser(username);
	}
	
	
}
