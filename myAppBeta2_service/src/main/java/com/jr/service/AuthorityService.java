package com.jr.service;

import com.jr.entity.Authority;

import java.util.List;


public interface AuthorityService {
	
	/**
	 * 通过权限id查询一个权限
	 * @author jql
	 * @param id
	 * @return
	 */
	public Authority getAuthorityById(int id);
	
	/**
	 * 通过权限名称查询一个权限
	 * @author jql
	 * @param name
	 * @return
	 */
	public Authority getAuthorityByName(String name);
	
	/**
	 * 保存一个权限
	 * @author jql
	 * @param authority
	 * @return
	 */
	public int saveAuthority(Authority authority);
	
	/**
	 * 删除一个权限
	 * @author jql
	 * @param authority
	 * @return
	 */
	public int deleteAuthority(Authority authority);

	/**
	 * 查询所有权限
	 * @author jql
	 * @return
	 */
	public List<Authority> findAllAuthorities();
	
	/**
	 * 通过角色id查找角色的权限
	 * @author jql
	 * @param roleId
	 * @return
	 */
	public List<Authority> findAllAuthoritiesByRoleId(int roleId);
	
	/**
	 * 通过用户名查询用户权限
	 * @author jql
	 * @param userName
	 * @return
	 */
	public List<Authority> findAllAuthoritiesByUserName(String userName);
	
	
}
