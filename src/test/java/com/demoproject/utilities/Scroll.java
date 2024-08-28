package com.demoproject.utilities;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import com.demoproject.base.BaseClass;


public class Scroll extends BaseClass{
	
	Actions act = new Actions(driver);
	
	public void moveToElement(WebElement elementToScroll) {
		act.moveToElement(elementToScroll);
		act.perform();	
	}
	
	public void scrollToElement(WebElement elementToScroll) {
		act.scrollToElement(elementToScroll);
		act.perform();
	}

}

