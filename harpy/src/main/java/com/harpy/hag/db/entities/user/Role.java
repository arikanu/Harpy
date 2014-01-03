package com.harpy.hag.db.entities.user;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.TableGenerator;

@Entity
public class Role {

	@Id
	@GeneratedValue(strategy=GenerationType.TABLE, generator="roleId")
	@TableGenerator(name="roleId", table="pkRole", pkColumnName="roleKey", pkColumnValue="roleValue", allocationSize=1)
	private int roleId;
	
	@Column(nullable=false)
	private String roleName;
	
	@ManyToMany
	@JoinTable(name="User_Role", joinColumns={@JoinColumn(name="roleId")}, inverseJoinColumns={@JoinColumn(name="emailAddress")})	
	private List<User> users = new ArrayList<User>();

	
	
	public int getRoleId() {
		return roleId;
	}
	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public List<User> getUsers() {
		return users;
	}
	public void setUsers(List<User> users) {
		this.users = users;
	}
	
}
