package com.jr.controller;

import java.util.List;

import com.jr.entity.Authority;
import com.jr.service.AuthorityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;


@SuppressWarnings(value="all")
@Controller
@RequestMapping(value="/authority")
public class AuthorityController {
	
	private static Logger log = LoggerFactory.getLogger(AuthorityController.class);
	
	@Autowired
	private AuthorityService authorityService;
	
	@RequestMapping(value="toAuthorityManagementPage")
	public String toAuthorityManagementPage(ModelMap map){
		List<Authority> authorities = authorityService.findAllAuthorities();
		map.put("authorities", authorities);
		return "authorityList";
	}

	
}
