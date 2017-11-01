package com.jr.serviceImpl;

import com.jr.dao.AuthorityMapper;
import com.jr.dao.RoleMapper;
import com.jr.dao.UserMapper;
import com.jr.entity.Authority;
import com.jr.entity.Role;
import com.jr.entity.RoleAuthorityRelation;
import com.jr.entity.User;
import com.jr.service.RoleService;
import com.jr.utils.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;


@SuppressWarnings(value="all")
@Service
public class RoleServiceImpl implements RoleService {
	
	private static Logger log = LoggerFactory.getLogger(RoleServiceImpl.class);
	
	@Resource
	private RoleMapper roleMapper;
	
	@Resource
	private AuthorityMapper authorityMapper;
	
	@Resource
	private UserMapper userMapper;
	
	/**
	 * 根据id查询一个角色信息，不包含权限信息
	 */
	@Override
	public Role getOneRoleById(int id) {
		return roleMapper.getOneRoleById(id);
	}

	/**
	 * 根据id查询一个角色信息，包含权限信息
	 */
	@Override
	public Role queryRoleJoinAuthority(int id) {
		return roleMapper.queryRoleJoinAuthority(id);
	}

	/**
	 * 保存一个角色信息
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public int saveRole(Role role) {
		return roleMapper.saveRole(role);
	}

	/**
	 * 保存一个角色、权限关联信息
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public int saveRoleAuthorityRelation(RoleAuthorityRelation roleAuthorityRelation) {
		return roleMapper.saveRoleAuthorityRelation(roleAuthorityRelation);
	}

	/**
	 * 查询所有角色信息
	 */
	@Override
	public List<Role> findAllRoles() {
		return roleMapper.findAllRoles();
	}

	/**
	 * 保存角色和权限
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public int saveRoleWithAuthority(Role role, String authorityIds) {
		log.debug("保存角色和权限。role:"+role+",authorityIds:"+authorityIds);
		int result = Constants.SysConstants.FAIL;
		if (role==null) {
			return result;
		}
		if (authorityIds!=null&&!"".equals(authorityIds)) {
			try {
				int count = roleMapper.saveRole(role);
				if (count>0) {
					String[] ids = authorityIds.split(",");
					int[] id = new int[ids.length];
					for(int i=0; i<id.length; i++){
						id[i] = Integer.parseInt(ids[i]);
					}
					for (int i : id) {
						Authority authority = authorityMapper.getAuthorityById(i);
						roleMapper.saveRoleAuthorityRelation(new RoleAuthorityRelation(role, authority));
					}
					result = Constants.SysConstants.SUCCESS;
				}
				return result;
			} catch (Exception e) {
				log.error("保存角色报错。e:"+e.getMessage());
				return result;
			}
		}else{
			try {
				int i = roleMapper.saveRole(role);
				if (i>0) {
					result = Constants.SysConstants.SUCCESS;
					return result;
				}
				return result;
			} catch (Exception e) {
				log.error("保存角色报错。e:"+e.getMessage());
				return result;
			}
		}
	}

	
	/**
	 * 批量删除角色
	 * idStr：待删除的角色id
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public int multiDeleteRole(String idStr) {
		log.debug("批量删除角色。idStr:"+idStr);
		int result = Constants.SysConstants.FAIL;
		if (idStr!=null&&!"".equals(idStr)) {
			//转换类型
			String[] ids = idStr.split(",");
			int[] id = new int[ids.length];
			for(int i=0; i<id.length; i++){
				id[i] = Integer.parseInt(ids[i]);
			}
			//可以删除的角色集合
			List<String> canDelIds = new ArrayList<String>();
			//判断角色下是否有用户，以决定是否删除角色
			for (int i : id) {
				//判断角色下是否具有用户
				try {
					List<User> users = userMapper.findAllUserByRoleId(i);
					if (users!=null&&users.size()>0) {
						continue;
					}
					canDelIds.add(i+"");
					continue;
				} catch (Exception e) {
					log.error("删除角色报错！删除失败！e:"+e.getMessage());
				}
			}
			//删除角色
			if (canDelIds.size()>0) {
				String[] delArray = new String[canDelIds.size()];
				try {
					int count = roleMapper.multiDeleteRoleAuthorityRelation(canDelIds.toArray(delArray));
					int delRoleResult = roleMapper.multiDeleteRole(canDelIds.toArray(delArray));
					if (delRoleResult>0) {
						if (canDelIds.size()==ids.length) {
							result = Constants.SysConstants.SUCCESS;
						}else{
							result = Constants.SysConstants.ROLE_EXIST_USER_SUCCESS;
						}
					}
					return result;
				} catch (Exception e) {
					log.error("删除角色报错！删除失败！e:"+e.getMessage());
				}
			}else{
				result = Constants.SysConstants.ROLE_EXIST_USER_FAIL;
				return result;
			}
		}
		return result;
	}

	
	/**
	 * 更新一个角色信息
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public int updateRole(Role newRole) {
		log.debug("更新一个角色信息。新的角色信息。newRole:"+newRole);
		return roleMapper.updateRole(newRole);
	}
	
	/**
	 * 更新角色和权限
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public int updateRoleAuthorityRelation(Role newRole, String authorityIds) {
		log.debug("更新角色和权限");
		int result = Constants.SysConstants.FAIL;
		if (newRole==null) {
			return result;
		}
		//更新关联的权限信息
		if (authorityIds!=null&&!"".equals(authorityIds)) {
			try {
				int count = roleMapper.updateRole(newRole);
				if (count>0) {
					String[] ids = authorityIds.split(",");
					int[] id = new int[ids.length];
					for(int i=0; i<id.length; i++){
						id[i] = Integer.parseInt(ids[i]);
					}
					String[] roleIds = new String[1];
					roleIds[0] = newRole.getId()+"";
					//删除旧权限
					roleMapper.multiDeleteRoleAuthorityRelation(roleIds);
					//添加新权限
					for (int i : id) {
						Authority authority = authorityMapper.getAuthorityById(i);
						roleMapper.saveRoleAuthorityRelation(new RoleAuthorityRelation(newRole, authority));
					}
					result = Constants.SysConstants.SUCCESS;
				}
				return result;
			} catch (Exception e) {
				log.error("更新角色报错。e:"+e.getMessage());
				return result;
			}
		}
		//
		else{
			try {
				int count = roleMapper.updateRole(newRole);
				if (count>0) {
					String[] roleIds = new String[1];
					roleIds[0] = newRole.getId()+"";
					//删除旧权限
					roleMapper.multiDeleteRoleAuthorityRelation(roleIds);
					result = Constants.SysConstants.SUCCESS;
				}
				return result;
			} catch (Exception e) {
				log.error("更新角色报错。e:"+e.getMessage());
				return result;
			}
		}
		
	}

	

}
