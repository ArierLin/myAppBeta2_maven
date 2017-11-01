package com.jr.entity;

public class UserRoleRelation {

	private User user;
	private Role role;
	private String createTime;
	
	
	
	public UserRoleRelation() {
		super();
	}
	
	public UserRoleRelation(User user, Role role) {
		this.user = user;
		this.role = role;
	}



	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	
	
}
