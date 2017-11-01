package com.jr.serviceImpl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.annotation.Resource;

import com.jr.dao.AuthorityMapper;
import com.jr.dao.RoleMapper;
import com.jr.dao.UserMapper;
import com.jr.entity.Authority;
import com.jr.entity.Role;
import com.jr.entity.User;
import com.jr.entity.UserRoleRelation;
import com.jr.service.UserService;
import com.jr.utils.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


@SuppressWarnings(value="all")
@Service
public class UserServiceImpl implements UserService {
	
	private static Logger log = LoggerFactory.getLogger(UserServiceImpl.class);
	
	@Resource
	private UserMapper userMapper;
	
	@Resource
	private RoleMapper roleMapper;
	
	@Resource
	private AuthorityMapper authorityMapper;
	

	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public int saveUser(User user) {
		return userMapper.saveUser(user);
	}

	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public int saveUserRoleRelation(UserRoleRelation userRoleRelation) {
		return userMapper.saveUserRoleRelation(userRoleRelation);
	}

	@Override
	public User getOneUserByName(String name) {
		return userMapper.getOneUserByName(name);
	}

	@Override
	public User getOneUserById(int id) {
		return userMapper.getOneUserById(id);
	}

	@Override
	public User queryUserJoinRole(int id) {
		return userMapper.queryUserJoinRole(id);
	}

	@Override
	public List<User> findAllUsers() {
		return userMapper.findAllUsers();
	}

	/**
	 * 根据用户名查询用户具有的权限
	 * 返回：AuthorityList
	 */
	@Override
	public List<String> getAuthoritiesByUserName(String userName) {
		log.debug("根据用户名查询用户具有的权限。userName:"+userName);
		if (userName==null||"".equals(userName)) {
			return Collections.EMPTY_LIST;
		}
		User user = userMapper.getOneUserByName(userName);
		if (user==null) {
			return Collections.EMPTY_LIST;
		}
		//查询包含角色信息的用户信息
		user = userMapper.queryUserJoinRole(user.getId());
		
		List<String> authorityList = new ArrayList<String>();
		//查询用户的所属角色
		List<Role> roles = user.getRoles();
		for (Role role : roles) {
			List<Authority> authorities = authorityMapper.findAllAuthoritiesByRoleId(role.getId());
			for (Authority authority : authorities) {
				authorityList.add(authority.getUrl());
			}
		}
		return authorityList;
	}

	/**
	 * 根据用户名查询用户具有的角色
	 */
	@Override
	public List<String> getRolesByUserName(String userName) {
		log.debug("根据用户名查询用户具有的角色。userName:"+userName);
		if (userName==null||"".equals(userName)) {
			return Collections.EMPTY_LIST;
		}
		User user = userMapper.getOneUserByName(userName);
		if (user==null) {
			return Collections.EMPTY_LIST;
		}
		//查询包含角色信息的用户信息
		user = userMapper.queryUserJoinRole(user.getId());
		//查询用户的所属角色
		List<Role> roles = user.getRoles();
		List<String> roleList = new ArrayList<String>();
		for (Role role : roles) {
			roleList.add(role.getName());
		}
		return roleList;
	}

	/**
	 * 根据角色id查询该角色下的所有用户
	 */
	@Override
	public List<User> findAllUserByRoleId(int roleId) {
		log.debug("根据角色id查询该角色下的所有用户。roleId:"+roleId);
		if (roleId==0) {
			return Collections.EMPTY_LIST;
		}
		return userMapper.findAllUserByRoleId(roleId);
	}

	/**
	 * 判断用户名是否可以使用
	 */
	@Override
	public boolean validateUserName(String userName) {
		log.debug("判断用户名是否可以使用.userName:"+userName);
		User user = userMapper.getOneUserByName(userName);
		if (user==null) {
			return true;
		}
		return false;
	}

	/**
	 * 保存用户和角色
	 */
	@Override
	public int saveUserWithRole(User user, String roleIds) {
		log.debug("保存用户和角色.user:"+user+",roleIds:"+roleIds);
		int result = Constants.SysConstants.FAIL;
		if (user==null||roleIds==null||"".equals(roleIds.trim())) {
			return result;
		}
		try {
			int count = userMapper.saveUser(user);
			if (count>0) {
				String[] ids = roleIds.split(",");
				int[] id = new int[ids.length];
				for(int i=0; i<id.length; i++){
					id[i] = Integer.parseInt(ids[i]);
				}
				for (int i : id) {
					Role role = roleMapper.getOneRoleById(i);
					userMapper.saveUserRoleRelation(new UserRoleRelation(user,role));
				}
				result = Constants.SysConstants.SUCCESS;
			}
			return result;
		} catch (Exception e) {
			log.error("保存用户报错。e:"+e.getMessage());
			return result;
		}
	}

	/**
	 * 更新用户、用户角色关联信息
	 */
	@Override
	public int updateUserRoleRelation(User newUser, String roleIds) {
		log.debug("更新用户、用户角色关联信息。newUser:"+newUser+",roleIds:"+roleIds);
		int result = Constants.SysConstants.FAIL;
		if (newUser==null||roleIds==null||"".equals(roleIds.trim())) {
			return result;
		}
		try {
			int count = userMapper.updateUser(newUser);
			if (count>0) {
				String[] ids = roleIds.split(",");
				int[] id = new int[ids.length];
				for(int i=0; i<id.length; i++){
					id[i] = Integer.parseInt(ids[i]);
				}
				String[] userIds = new String[1];
				userIds[0] = newUser.getId()+"";
				//删除旧角色
				userMapper.multiDeleteUserRoleRelation(userIds);
				//添加新角色
				for (int i : id) {
					Role role = roleMapper.getOneRoleById(i);
					userMapper.saveUserRoleRelation(new UserRoleRelation(newUser,role));
				}
				result = Constants.SysConstants.SUCCESS;
			}
			return result;
		} catch (Exception e) {
			log.error("更新用户报错。e:"+e.getMessage());
			return result;
		}
	}

	/**
	 * 批量删除用户
	 */
	@Override
	public int multiDeleteUser(String idStr) {
		log.debug("批量删除用户.待删除的用户id:"+idStr);
		int result = Constants.SysConstants.FAIL;
		if (idStr!=null&&!"".equals(idStr)) {
			try {
				//转换类型
				String[] ids = idStr.split(",");
				userMapper.multiDeleteUserRoleRelation(ids);
				int count = userMapper.multiDeleteUser(ids);
				if (count>0) {
					result = Constants.SysConstants.SUCCESS;
				}
				return result;
			} catch (Exception e) {
				log.error("删除用户失败！e:"+e.getMessage());
				return result;
			}
		}
		return result;
	}
	
	

}
