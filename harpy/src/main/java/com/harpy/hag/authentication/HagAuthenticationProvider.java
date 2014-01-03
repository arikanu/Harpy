package com.harpy.hag.authentication;

import java.util.ArrayList;
import java.util.List;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import com.harpy.hag.db.entities.user.Role;

@Component
public class HagAuthenticationProvider implements AuthenticationProvider {

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		
		System.out.println("     @HagAuthenticationProvider.authenticate");
		
		String emailAddress = authentication.getName();
		String password = authentication.getCredentials().toString();
		
		boolean isValidLogin = LoginAuthentication.isValidLogin(emailAddress, password);
		System.out.println("          isValidLogin = " + isValidLogin);
		if (isValidLogin) {
			List<GrantedAuthority> grantedAuths = new ArrayList<GrantedAuthority>();
			List<Role> roles = LoginAuthentication.getRoles(emailAddress);
			for(Role role : roles) {
				System.out.println("          Role = " + "ROLE_" + role.getRoleName().toUpperCase());
				SimpleGrantedAuthority sga = new SimpleGrantedAuthority("ROLE_" + role.getRoleName().toUpperCase());
				grantedAuths.add(sga);
			}
			Authentication auth = new UsernamePasswordAuthenticationToken(emailAddress, password, grantedAuths);
			return auth;
		} else {
			System.out.println("          Authentication = NULL");
			return null;
		}
	}

	@Override
	public boolean supports(Class<?> authentication) {
		System.out.println("     @HagAuthenticationProvider.supports");
		System.out.println("          supports = " + authentication.equals(UsernamePasswordAuthenticationToken.class));
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}

}
