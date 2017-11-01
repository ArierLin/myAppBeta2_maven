package com.jr.serviceImpl;

import java.util.List;

import javax.annotation.Resource;

import com.jr.dao.DemoMapper;
import com.jr.entity.Demo;
import com.jr.service.DemoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.support.lob.LobCreator;
import org.springframework.stereotype.Service;


@Service
public class DemoServiceImpl implements DemoService {
	
	private static Logger log = LoggerFactory.getLogger(DemoServiceImpl.class);
	
	@Resource
	private DemoMapper demoMapper;

	@Override
	public int saveOneDemo(Demo demo) {
		log.debug("保存一个demo");
		if (demo==null) {
			return 0;
		}
		try {
			return demoMapper.save(demo);
		} catch (Exception e) {
			log.error("保存出错！e:"+e.getMessage());;
		}
		return 0;
	}

	@Override
	public int updateOneDemo(Demo demo) {
		log.debug("修改一个demo。demo:"+demo);
		if (demo==null||"".equals(demo.getName())) {
			return 0;
		}
		try {
			int id = demo.getId();
			Demo demo2 = demoMapper.getDemoById(id);
			if (demo2==null) {
				return 0;
			}
			demo2.setName(demo.getName());
			return demoMapper.update(demo2);
		} catch (Exception e) {
			log.error("修改demo报错！e:"+e.getMessage());
		}
		return 0;
	}

	@Override
	public boolean deleteDemoByIds(String ids) {
		log.debug("根据id删除demo。ids:"+ids);
		if (ids==null||"".equals(ids)) {
			return false;
		}
		try {
			return demoMapper.delete(ids.split(","));
		} catch (Exception e) {
			log.error("根据id删除demo报错！e:"+e.getMessage());
		}
		return false;
	}

	@Override
	public Demo getOneDemoById(int id) {
		log.debug("根据id查询一个demo。id:"+id);
		if (id<=0) {
			return null;
		}
		try {
			return demoMapper.getDemoById(id);
		} catch (Exception e) {
			log.error("根据id查询一个demo报错！e:"+e.getMessage());;
		}
		return null;
	}

	@Override
	public List<Demo> findAllDemos() {
		log.debug("查询所有的demo。");
		return demoMapper.findAllDemos();
	}

}
