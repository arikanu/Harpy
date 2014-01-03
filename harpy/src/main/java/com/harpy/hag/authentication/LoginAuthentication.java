package com.harpy.hag.authentication;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import com.harpy.hag.db.entities.user.Role;
import com.harpy.hag.db.entities.user.User;
import com.harpy.hag.db.utils.HibernateUtil;

public class LoginAuthentication {

	static Session session = HibernateUtil.getSessionFactory().getCurrentSession();
	
	public static User getUser(String emailAddress) {
		System.out.println("     @LoginAuthentication.getUser");
		
		session.beginTransaction();
		Query query = session.createQuery(SqlAuthentication.qUserByEmail(emailAddress));
		ArrayList<User> userList = new ArrayList<User>(query.list());
		if (userList.size() > 0) {
			System.out.println("          EmailAddress: " + userList.get(0).getEmailAddress());
			for(Role role : userList.get(0).getRoles()) {
				System.out.println("          Role: " + role.getRoleName());
			}
			return userList.get(0);
		} else {
			System.out.println("          User is NULL");
			return null;
		}
	}
	
	public static boolean doesUserExist(String emailAddress) {
		System.out.println("     @LoginAuthentication.doesUserExist");
		
		session.beginTransaction();
		Query query = session.createQuery(SqlAuthentication.qUserByEmail(emailAddress));
		ArrayList<User> userList = new ArrayList<User>(query.list());
		if (userList.size() > 0) {
			System.out.println("          EmailAddress: " + userList.get(0).getEmailAddress());
			for(Role role : userList.get(0).getRoles()) {
				System.out.println("          Role: " + role.getRoleName());
			}
			return true;
		} else {
			System.out.println("          User is NULL");
			return false;
		}
	}
	
	public static boolean isValidLogin(String emailAddress, String password) {
		System.out.println("     @LoginAuthentication.isValidLogin");
		
		session.beginTransaction();
		Query query = session.createQuery(SqlAuthentication.qUserByEmailPassword(emailAddress, password));
		ArrayList<User> userList = new ArrayList<User>(query.list());
		if (userList.size() > 0) {
			System.out.println("          EmailAddress: " + userList.get(0).getEmailAddress());
			for(Role role : userList.get(0).getRoles()) {
				System.out.println("          Role: " + role.getRoleName());
			}
			return true;
		} else {
			System.out.println("          User is NULL");
			return false;
		}
	}
	
	public static List<Role> getRoles(String emailAddress) {
		System.out.println("     @LoginAuthentication.getRoles");
		
		session.beginTransaction();
		Query query = session.createQuery(SqlAuthentication.qUserByEmail(emailAddress));
		ArrayList<User> userList = new ArrayList<User>(query.list());
		if (userList.size() > 0) {
			System.out.println("          EmailAddress: " + userList.get(0).getEmailAddress());
			for(Role role : userList.get(0).getRoles()) {
				System.out.println("          Role: " + role.getRoleName());
			}
			return userList.get(0).getRoles();
		} else {
			System.out.println("          User is NULL");
			return null;
		}
	}
	
}
