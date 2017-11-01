package com.jr.serviceImpl;

import com.jr.dao.AuthorityMapper;
import com.jr.dao.RoleMapper;
import com.jr.dao.UserMapper;
import com.jr.entity.Authority;
import com.jr.entity.Role;
import com.jr.entity.User;
import com.jr.service.AuthorityService;
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
public class AuthorityServiceImpl implements AuthorityService {
	
	private static Logger log = LoggerFactory.getLogger(AuthorityServiceImpl.class);

	@Resource
	private AuthorityMapper authorityMapper;
	
	@Resource
	private UserMapper userMapper;
	
	@Resource
	private RoleMapper roleMapper;
	

	/**
	 * 根据id查询一个权限信息
	 */
	@Override
	public Authority getAuthorityById(int id) {
		return authorityMapper.getAuthorityById(id);
	}

	/**
	 * 根据名称查询一个权限信息
	 */
	@Override
	public Authority getAuthorityByName(String name) {
		return authorityMapper.getAuthorityByName(name);
	}

	/**
	 * 保存一个权限信息
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public int saveAuthority(Authority authority) {
		return authorityMapper.saveAuthority(authority);
	}

	/**
	 * 删除一个权限信息
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public int deleteAuthority(Authority authority) {
		return authorityMapper.deleteAuthority(authority);
	}
	
	/**
	 * 查询所有权限
	 */
	@Override
	public List<Authority> findAllAuthorities() {
		log.debug("查询所有权限。");
		return authorityMapper.findAllAuthorities();
	}

	/**
	 * 通过角色id查找角色的权限
	 */
	@Override
	public List<Authority> findAllAuthoritiesByRoleId(int roleId) {
		log.debug("通过角色id查找角色的权限。roleId:"+roleId);
		return authorityMapper.findAllAuthoritiesByRoleId(roleId);
	}

	/**
	 * 通过用户名查询用户权限
	 */
	@Override
	public List<Authority> findAllAuthoritiesByUserName(String userName) {
		log.debug("通过用户名查询用户权限。userName:"+userName);
		User user = userMapper.getOneUserByName(userName);
		List<Authority> userAuthorities = new ArrayList<>();
		if (user!=null) {
			user = userMapper.queryUserJoinRole(user.getId());
			List<Role> roles = user.getRoles();
			for (Role role : roles) {
				role = roleMapper.queryRoleJoinAuthority(role.getId());
				List<Authority> authorities = role.getAuthority();
				userAuthorities.addAll(authorities);
			}
			return userAuthorities;
		}
		return userAuthorities;
	}

}
