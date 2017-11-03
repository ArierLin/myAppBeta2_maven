package com.jr;

import java.util.List;

import com.jr.weather.ArrayOfString;
import com.jr.weather.WeatherWebService;
import com.jr.weather.WeatherWebServiceSoap;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class WebService_WeatherTest {

	public static void main(String[] args) {
		//创建代理对象
		WeatherWebService weatherWS = new WeatherWebService();
		WeatherWebServiceSoap weatherWSSoap = weatherWS.getWeatherWebServiceSoap();
		
		//代理对象调用方法
		ArrayOfString weather = weatherWSSoap.getWeatherbyCityName("哈尔滨");
		List<String> list = weather.getString();
		for(String s : list) {
			System.out.println(s+"------");
		}
		
	}
}
