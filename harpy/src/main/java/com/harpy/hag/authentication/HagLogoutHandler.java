package com.harpy.hag.authentication;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;
import org.springframework.stereotype.Component;

@Component
public class HagLogoutHandler extends SimpleUrlLogoutSuccessHandler implements LogoutSuccessHandler {

	@Override
	public void onLogoutSuccess(HttpServletRequest request,
			HttpServletResponse response, Authentication authentication) throws IOException,
			ServletException {
		System.out.println("     @HagLogoutHandler.onLogoutSuccess");
		// TO:DO
		super.onLogoutSuccess(request, response, authentication);
		
	}

	 

}
