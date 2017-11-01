package com.jr.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.jr.entity.Role;
import com.jr.entity.User;
import com.jr.service.RoleService;
import com.jr.service.UserService;
import com.jr.utils.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@SuppressWarnings(value="all")
@Controller
@RequestMapping(value="/user")
public class UserController {

	private static Logger log = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private RoleService roleService;
	
	/**
	 * 跳转到用户管理页面。
	 * @author jql
	 * @return
	 */
	@RequestMapping(value="toUserManagementPage")
	public String toUserManagementPage(ModelMap map){
		log.debug("跳转到用户管理页面。");
		List<User> users = userService.findAllUsers();
		map.put("users", users);
 		return "userList";
	}
	
	/**
	 * 跳转到添加用户页面
	 * @author jql
	 * @param map
	 * @return
	 */
	@RequestMapping(value="toAddUserPage")
	public String toAddUserPage(ModelMap map){
		log.debug("跳转到添加用户页面");
		
		List<Role> roles = roleService.findAllRoles();
		map.put("roles", roles);
		return "userAddOrUpdate";
	}

	/**
	 * 判断用户名是否可以使用
	 * @author jql
	 * @param name
	 * @return
	 */
	@RequestMapping(value="validateUserName")
	@ResponseBody
	public boolean validateUserName(String name){
		log.debug("判断用户名是否可以使用。name:"+name);
		return userService.validateUserName(name);
	}
	
	/**
	 * 保存用户
	 * @author jql
	 * @return
	 */
	@RequestMapping(value="saveUser")
	@ResponseBody
	public boolean saveUser(@RequestParam(value="name")String userName,
			@RequestParam(value="password")String password,
			@RequestParam(value="idStr")String idStr){
		log.debug("保存用户。userName:"+userName+",password:"+password+",角色id：idStr:"+idStr);
		SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
		User user = new User();
		user.setUserName(userName);
		user.setPassword(password);
		user.setCreateTime(sdf.format(new Date()));
		int result = userService.saveUserWithRole(user, idStr);
		if (result==Constants.SysConstants.SUCCESS) {
			return true;
		}
		return false;
	}
	
	/**
	 * 跳转到编辑用户信息页面
	 * @author jql
	 * @param userId
	 * @return
	 */
	@RequestMapping(value="toUpdateUserPage")
	public String toUpdateUserPage(int userId,ModelMap map){
		log.debug("跳转到编辑用户信息页面。userId:"+userId);
		User user = userService.queryUserJoinRole(userId);
		List<Role> roles = roleService.findAllRoles();
		List<Role> selectedRoles = user.getRoles();
		map.put("roles", roles);
		map.put("selectedRoles", selectedRoles);
		map.put("user", user);
		return "userAddOrUpdate";
	}
	
	/**
	 * 修改用户信息
	 * @author jql
	 * @param userId
	 * @param userName
	 * @param idStr
	 * @return
	 */
	@RequestMapping(value="updateUser")
	@ResponseBody
	public boolean updateUser(@RequestParam(value="id")int userId,
			@RequestParam(value="name")String userName,
			@RequestParam(value="idStr")String idStr){
		log.debug("修改用户信息。userId:"+userId+",userName:"+userName+"角色id：idStr:"+idStr);
		boolean result = false;
		SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
		User user = new User();
		user.setId(userId); 
		user.setUserName(userName);
		int i = userService.updateUserRoleRelation(user,idStr);
		if (i==Constants.SysConstants.SUCCESS) {
			result = true;
		}
		return result;
	}
	
	/**
	 * 删除用户信息
	 * @author jql
	 * @param idStr
	 * @return
	 */
	@RequestMapping(value="multiDeleteUser")
	@ResponseBody
	public String multiDeleteUser(@RequestParam(value="idStr")String idStr){
		log.debug("删除用户信息，待删除的用户id："+idStr);
		int result = userService.multiDeleteUser(idStr);
		if (result == Constants.SysConstants.SUCCESS) {
			return "success";
		}
		return "角色用户失败！";
	}
	
	
}
