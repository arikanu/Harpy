package com.harpy.hag.authentication;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import com.harpy.hag.db.entities.user.Role;

@Component
public class HagSuccessHandler implements AuthenticationSuccessHandler {

	@Override
	public void onAuthenticationSuccess
			(HttpServletRequest request, HttpServletResponse response, Authentication authentication) 
					throws IOException, ServletException {
		
		System.out.println("     @HagSuccessHandler.onAuthenticationSuccess");
		
		List<Role> roles = LoginAuthentication.getRoles(authentication.getName());
		System.out.println("          authentication.getName = " + authentication.getName());
		
		//Set<String> roles = AuthorityUtils.authorityListToSet(authentication.getAuthorities());
		List<String> strRoles = new ArrayList<String>();
		for(Role role : roles) {
			System.out.println("          ROLE = " + "ROLE_" + role.getRoleName());
			strRoles.add("ROLE_" + role.getRoleName());
		}
		
		
		
		
		
		if (strRoles.contains("ROLE_ADMN")) {
			System.out.println("          contains = " + "ROLE_ADMN");
			response.sendRedirect("/hag/admin/home");
		} else if (strRoles.contains("ROLE_STDNT")) {
			System.out.println("          contains = " + "ROLE_STDNT");
			response.sendRedirect("/hag/student/home");
		} else {
			System.out.println("          contains = " + "none");
			response.sendRedirect("/hag/home");
		}
		
	}

}
