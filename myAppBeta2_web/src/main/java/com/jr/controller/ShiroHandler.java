package com.jr.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import com.jr.entity.Authority;
import com.jr.service.AuthorityService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
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
public class ShiroHandler {
	
	private static Logger log = LoggerFactory.getLogger(ShiroHandler.class);
	
	@Autowired
	private HttpSession session;
	
	@Autowired
	private AuthorityService authorityService;
	
	@RequiresRoles("admin")
	@RequestMapping("shiro-test")
	public String test(){
		System.out.println("有权限，可以执行方法！");
		return "redirect:/list.jsp";
	}

	@RequestMapping("/shiro-login")
	public String login(@RequestParam("username")String username,
			@RequestParam("password")String password){
		//获取当前用户
		Subject currentUser = SecurityUtils.getSubject();
		//检验当前用户是否已经登录
		if (!currentUser.isAuthenticated()) {
			//若没有登录，则把用户名和密码封装为一个UsernamePasswordToken对象
            UsernamePasswordToken token = new UsernamePasswordToken(username, password);
            token.setRememberMe(true);
            try {
            	//执行登录操作
            	//会比对密码，会调用realm的doGetAuthenticationInfo方法
                currentUser.login(token);
                session.setAttribute("currentUserName", currentUser.getPrincipal());
            } 
            // ... catch more exceptions here (maybe custom ones specific to your application?
            catch (AuthenticationException ae) {
                System.out.println("登录失败！"+ae.getMessage());
                return "redirect:/login.jsp";
            }
        }
		//重定向到首页面
		return "redirect:/index";
	}
	
	
	
	/**
	 * 跳转到首页
	 * @author jql
	 * @return
	 */
	@RequestMapping(value="/index")
	public String toIndexPage(ModelMap modelMap){
		log.debug("跳转到首页。");
		
//		List<Authority> authorities = authorityService.findAllAuthorities();
//		modelMap.put("authorities", authorities);
		return "index";
	}
	
	/**
	 * 加载左侧权限列表
	 * @author jql
	 * @return
	 */
	@RequestMapping(value="/loadAuthoritiesTreeData")
	@ResponseBody
	public List<Authority> loadAuthoritiesTreeData(){
		log.debug("加载左侧权限列表。");
		//根据用户名查询具有的权限
//		String userName = (String) session.getAttribute("currentUserName");
//		List<Authority> authorities = authorityService.findAllAuthoritiesByUserName(userName);
		List<Authority> authorities = authorityService.findAllAuthorities();
		return authorities;
	}
	
	/**
	 * 加载跑马灯内容
	 * @author jql
	 * @return
	 */
	@RequestMapping(value="/getMarqueeContent")
	@ResponseBody
	public String getMarqueeContent(){
		log.debug("加载跑马灯内容");
		Date date = new Date();
		return date.toString();
	}
	
	
	
}
