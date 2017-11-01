package com.jr.entity;

import java.util.Date;

/**
 * 角色、权限关联描述类
 * @author jql
 *
 */
public class RoleAuthorityRelation {

	private Role role;
	private Authority authority;
	private String createTime;
	
	
	public RoleAuthorityRelation() {
		super();
	}
	public RoleAuthorityRelation(Role role, Authority authority) {
		super();
		this.role = role;
		this.authority = authority;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	public Authority getAuthority() {
		return authority;
	}
	public void setAuthority(Authority authority) {
		this.authority = authority;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	
	
	
	
}
