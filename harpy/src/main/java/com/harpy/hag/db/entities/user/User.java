package com.harpy.hag.db.entities.user;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@Entity
public class User {

	//static Session session = HibernateUtil.getSessionFactory().getCurrentSession();
	
	@Id
	private String emailAddress;
	
	@Column(nullable=false)
	private String password;
	
	@Column(nullable=false)
	private String firstName;
	
	@Column(nullable=false)
	private String lastName;
	
	@ManyToMany
	@JoinTable(name="User_Role", joinColumns={@JoinColumn(name="emailAddress")}, inverseJoinColumns={@JoinColumn(name="roleId")})
	private List<Role> roles = new ArrayList<Role>();
	
	
	/*public void fillRoles() {
		session.beginTransaction();
		this.roles = null;
		Query query = session.createQuery("");
	}*/

	public List<Role> getRoles() {
		return roles;
	}
	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
	public String getEmailAddress() {
		return emailAddress;
	}
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String passWord) {
		this.password = passWord;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
		
	
}
