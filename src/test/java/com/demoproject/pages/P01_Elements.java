package com.demoproject.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.demoproject.base.BaseClassDemo;

public class P01_Elements extends BaseClassDemo{
	
	WebDriver localdriver;

	public P01_Elements(WebDriver remotedriver) {
		localdriver = remotedriver;
		PageFactory.initElements(remotedriver, this);
	}
	/***
	 *WebElements
	 ***/
	@FindBy(xpath = "//h5[text()='Elements']")
	WebElement elementsTag;
	
	public void  clickOnElements() {
		//wait.expWaitVisibilityOf(elementsTag, 15);
		elementsTag.click();
	}

}
