package com.jr.service;

import com.jr.entity.Role;
import com.jr.entity.RoleAuthorityRelation;

import java.util.List;


public interface RoleService {

	public Role getOneRoleById(int id);
	
	//根据角色id查询角色和权限信息
	public Role queryRoleJoinAuthority(int id);
	
	public int saveRole(Role role);
	
	public int saveRoleAuthorityRelation(RoleAuthorityRelation roleAuthorityRelation);
	
	public List<Role> findAllRoles();
	
	/**
	 * 保存角色和权限
	 * @author jql
	 * @param role
	 * @param authorityIds
	 * @return
	 */
	public int saveRoleWithAuthority(Role role, String authorityIds);
	
	/**
	 * 批量删除角色
	 * @author jql
	 * @param idStr
	 * @return
	 */
	public int multiDeleteRole(String idStr);
	
	/**
	 * 更新角色
	 * @author jql
	 * @param newRole
	 * @return
	 */
	public int updateRole(Role newRole);
	
	/**
	 * 更新角色和权限
	 * @author jql
	 * @return
	 */
	public int updateRoleAuthorityRelation(Role role, String authorityIds);
	
}
