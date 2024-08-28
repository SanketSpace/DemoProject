package com.demoproject.base;

import java.io.FileInputStream;
import java.util.Properties;

public class ReadConfig {
	
	Properties properties;
	
	String path = System.getProperty("user.dir")+"\\configuration\\config.properties";
	
	public ReadConfig(){
		
		try {
			properties = new Properties();
			FileInputStream fis = new FileInputStream(path);
			properties.load(fis);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Exception is " + e.getMessage());
		}
		
	}
	
/*********************************************************************/
	//to read values
	public String getBrowserName() {
		String bro = properties.getProperty("browserName");
		//to check whether it contains any value or not
		if(bro!=null) {
		return bro;
		}else 
		throw new RuntimeException("Browser not specified !");
		}
/*********************************************************************/	
	public String getBaseUrl() {
		String url = properties.getProperty("baseUrl");
		return url;
	}
/*********************************************************************/	
}