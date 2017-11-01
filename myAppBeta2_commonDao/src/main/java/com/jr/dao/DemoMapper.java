package com.jr.dao;

import com.jr.entity.Demo;

import java.util.List;


public interface DemoMapper {

	public int save(Demo demo);
	
	public int update(Demo demo);
	
	public boolean delete(String[] ids);
	
	public Demo getDemoById(int id);
	
	public List<Demo> findAllDemos();
}
