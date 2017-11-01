package com.jr.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 商品管理、商品类型管理
 * @author jql
 *
 */
@SuppressWarnings(value="all")
@Controller
@RequestMapping("/goods")
public class GoodsController {

	private static Logger log = LoggerFactory.getLogger(GoodsController.class);
	
	@RequestMapping("toGoodTypeManagementPage")
	public String toGoodTypeManagementPage(){
		log.debug("跳转到商品类型管理页面");
		return "goodTypeList";
	}
	
	@RequestMapping("toGoodsManagementPage")
	public String toGoodsManagementPage(){
		log.debug("跳转到商品管理页面");
		return "goodsList";
	}
}
