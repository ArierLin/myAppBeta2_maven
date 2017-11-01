package com.jr.dao;

import com.jr.entity.Role;
import com.jr.entity.RoleAuthorityRelation;

import java.util.List;


/**
 * 角色mapper
 * @author jql
 *
 */
public interface RoleMapper {

	/**
	 * 通过角色id查询一个角色
	 * @author jql
	 * @param id
	 * @return
	 */
	public Role getOneRoleById(int id);
	
	/**
	 * 根据角色id查询角色和权限信息
	 * @author jql
	 * @param id
	 * @return
	 */
	public Role queryRoleJoinAuthority(int id);
	
	/**
	 * 保存一个角色
	 * @author jql
	 * @param role
	 * @return
	 */
	public int saveRole(Role role);
	
	/**
	 * 保存角色、权限关联关系
	 * @author jql
	 * @param roleAuthorityRelation
	 * @return
	 */
	public int saveRoleAuthorityRelation(RoleAuthorityRelation roleAuthorityRelation);
	
	/**
	 * 查询所有角色
	 * @author jql
	 * @return
	 */
	public List<Role> findAllRoles();
	
	
	/**
	 * 批量删除角色和权限关联信息
	 * @author jql
	 * @param idStr
	 * @return
	 */
	public int multiDeleteRoleAuthorityRelation(String[] idStr);
	
	/**
	 * 批量删除角色
	 * @author jql
	 * @param idS
	 * @return
	 */
	public int multiDeleteRole(String[] idS);

	/**
	 * 更新一个角色
	 * @author jql
	 * @param newRole
	 * @return
	 */
	public int updateRole(Role newRole);
	
	
	
	
	
	
}
