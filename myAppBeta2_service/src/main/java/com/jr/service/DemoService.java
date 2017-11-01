package com.jr.service;

import java.util.List;

import com.jr.entity.Demo;
import org.apache.ibatis.annotations.Param;


public interface DemoService {

	public int saveOneDemo(Demo demo);
	
	public int updateOneDemo(Demo demo);
	
//	public boolean deleteDemoByIds(String ids);
	public boolean deleteDemoByIds(@Param(value = "ids") String ids);
	
	public Demo getOneDemoById(int id);
	
	public List<Demo> findAllDemos();
	
}
