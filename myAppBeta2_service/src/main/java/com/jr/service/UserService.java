package com.jr.service;

import com.jr.entity.User;
import com.jr.entity.UserRoleRelation;

import java.util.List;


public interface UserService {

	public int saveUser(User user);

	public int saveUserRoleRelation(UserRoleRelation userRoleRelation);
	
	public User getOneUserByName(String name);
	
	public User getOneUserById(int id);
	
	public User queryUserJoinRole(int id);
	
	public List<User> findAllUsers();
	
	/**
	 * 根据用户名查询用户具有的权限
	 * @author jql
	 * @param userName
	 * @return
	 */
	public List<String> getAuthoritiesByUserName(String userName);
	
	/**
	 * 根据用户名查询用户具有的角色
	 * @author jql
	 * @param userName
	 * @return
	 */
	public List<String> getRolesByUserName(String userName);
	
	
	/**
	 * 根据角色id查询该角色下的所有用户
	 * @author jql
	 * @param roleId
	 * @return
	 */
	public List<User> findAllUserByRoleId(int roleId);
	
	/**
	 * 判断用户名是否可以使用
	 * @author jql
	 * @param userName
	 * @return
	 */
	public boolean validateUserName(String userName);
	
	/**
	 * 保存用户和角色
	 * @author jql
	 * @param user
	 * @param roleIds
	 * @return
	 */
	public int saveUserWithRole(User user, String roleIds);

	/**
	 * 更新用户、用户角色关联信息
	 * @author jql
	 * @param newUser
	 * @param roleIds
	 */
	public int updateUserRoleRelation(User newUser, String roleIds);

	/**
	 * 批量删除用户
	 * @author jql
	 * @param idStr
	 * @return
	 */
	public int multiDeleteUser(String idStr);
	
}
