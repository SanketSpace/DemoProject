package com.demoproject.testcases;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.Test;
import com.demoproject.base.BaseClassDemo;
import com.demoproject.pages.P01_Elements;

public class DemoQA extends BaseClassDemo{
	WebDriver driver = new EdgeDriver();
	P01_Elements element = new P01_Elements(driver);
	
	@Test
	public void TC01_verifyElementsTag() {
//		element.clickOnElements();
//		customWait("Page loading", 5);
		String result = driver.getTitle();
		System.out.println("Page title: "+result);		
		verifyString(result, "DEMOQA", "TC01_verifyElementsTag");
		logger.info("TC01_verifyElementsTag execution completed !");
	}

}
