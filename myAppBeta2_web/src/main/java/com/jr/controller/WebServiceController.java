package com.jr.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.jr.weather.ArrayOfString;
import com.jr.weather.WeatherWebService;
import com.jr.weather.WeatherWebServiceSoap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@SuppressWarnings(value="all")
@Controller
@RequestMapping(value="/webService")
public class WebServiceController {
	
	private static Logger log = LoggerFactory.getLogger(WebServiceController.class);
	
	@RequestMapping(value="toWebServiceListPage")
	public String toWebServiceListPage(){
		log.debug("跳转到webServiceListPage。");
		return "webServiceList";
	}
	
	/**
	 * 查询城市天气
	 * @author jql
	 * String(0) 到 String(4)：省份，城市，城市代码，城市图片名称，最后更新时间。
		String(5) 到 String(11)：当天的 气温，概况，风向和风力，天气趋势开始图片名称(以下称：图标一)，天气趋势结束图片名称(以下称：图标二)，现在的天气实况，天气和生活指数。
		String(12) 到 String(16)：第二天的 气温，概况，风向和风力，图标一，图标二。
		String(17) 到 String(21)：第三天的 气温，概况，风向和风力，图标一，图标二。
		String(22) 被查询的城市或地区的介绍 
	 * @return
	 */
	@RequestMapping(value="queryCityWeather")
	@ResponseBody
	public List<Map<String, Object>> queryCityWeather(@RequestParam(value="cityName")String cityName){
		log.debug("查询城市天气。cityName:"+cityName);
		List<Map<String, Object>> result = new ArrayList<>();
		Map<String, Object> firstPart = new LinkedHashMap<>();
		Map<String, Object> nextPart = new LinkedHashMap<>();
		Map<String, Object> lastPart = new LinkedHashMap<>();
		
		//创建代理对象
		WeatherWebService weatherWS = new WeatherWebService();
		WeatherWebServiceSoap weatherWSSoap = weatherWS.getWeatherWebServiceSoap();
		
		//代理对象调用方法
		ArrayOfString weather = weatherWSSoap.getWeatherbyCityName(cityName);
		List<String> list = weather.getString();
		firstPart.put("城市(及城市代码)", list.get(1)+"("+list.get(2)+")");firstPart.put("所属省份", list.get(0));firstPart.put("最后更新时间", list.get(4));
		firstPart.put("今日气温", list.get(5));firstPart.put("概况", list.get(6));firstPart.put("风向和风力", list.get(7));
		firstPart.put("今日天气实况", list.get(10));
		firstPart.put("紫外线指数", list.get(11).split("。")[0].split("：")[1]);
		firstPart.put("感冒指数", list.get(11).split("。")[1].split("：")[1]);
		firstPart.put("穿衣指数", list.get(11).split("。")[2].split("：")[1]);
		firstPart.put("洗车指数", list.get(11).split("。")[3].split("：")[1]);
		firstPart.put("运动指数", list.get(11).split("。")[4].split("：")[1]);
		firstPart.put("空气污染指数", list.get(11).split("。")[5].split("：")[1]);
		
		nextPart.put("明天气温", list.get(12));nextPart.put("明天概况", list.get(13));nextPart.put("明天风向和风力", list.get(14));
		nextPart.put("后天气温", list.get(17));nextPart.put("后天概况", list.get(18));nextPart.put("后天风向和风力", list.get(19));
		
		lastPart.put("城市或地区的介绍", list.get(22));
		
		result.add(firstPart);
		result.add(nextPart);
		result.add(lastPart);
		return result;
	}
	
	
}
