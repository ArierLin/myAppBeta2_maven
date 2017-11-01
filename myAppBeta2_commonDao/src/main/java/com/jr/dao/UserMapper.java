package com.jr.dao;

import com.jr.entity.User;
import com.jr.entity.UserRoleRelation;

import java.util.List;


public interface UserMapper {
	/**
	 * 保存一个用户
	 * @author jql
	 * @param user
	 * @return
	 */
	public int saveUser(User user);

	/**
	 * 保存用户、角色关联关系
	 * @author jql
	 * @param userRoleRelation
	 * @return
	 */
	public int saveUserRoleRelation(UserRoleRelation userRoleRelation);
	
	/**
	 * 根据用户名查询一个用户
	 * @author jql
	 * @param userName
	 * @return
	 */
	public User getOneUserByName(String userName);
	
	/**
	 * 根据id查询一个用户
	 * @author jql
	 * @param userId
	 * @return
	 */
	public User getOneUserById(int userId);
	
	/**
	 * 查询用户，携带用户的角色信息
	 * @author jql
	 * @param userId：用户id
	 * @return
	 */
	public User queryUserJoinRole(int userId);
	
	/**
	 * 查询所有用户
	 * @author jql
	 * @return
	 */
	public List<User> findAllUsers();
	
	/**
	 * 根据角色id查询该角色下的所有用户
	 * @author jql
	 * @param roleId
	 * @return
	 */
	public List<User> findAllUserByRoleId(int roleId);

	/**
	 * 更新用户
	 * @author jql
	 * @param newUser
	 * @return
	 */
	public int updateUser(User newUser);

	/**
	 * 批量删除用户和角色关联信息
	 * @author jql
	 * @param userIds
	 */
	public int multiDeleteUserRoleRelation(String[] userIds);

	/**
	 * 批量删除用户
	 * @author jql
	 * @param userIds
	 */
	public int multiDeleteUser(String[] userIds);
	
	
}
