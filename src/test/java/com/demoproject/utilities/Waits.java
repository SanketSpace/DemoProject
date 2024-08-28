package com.demoproject.utilities;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.demoproject.base.BaseClass;

public class Waits extends BaseClass{
	
	/***
	 * implicit wait
	 ***/
	public void impWait(int sec) {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(sec));
	}

	/***
	 * explicit wait : xPath
	 ***/
	public void expWaitToBeClickable(String xPath, int sec) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(sec));
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xPath)));
	}

	/***
	 * explicit wait : WebElement
	 ***/
	public WebElement expWaitVisibilityOf(WebElement path, int sec) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(sec));
		wait.until(ExpectedConditions.visibilityOf(path));
		return path;
	}

	/***
	 * customized wait
	 ***/
	public void customWait(String message, int sec) {
		for (int i = sec; i > 0; i--) {
			try {
				Thread.sleep(1000);
				System.out.println(message + " : " + i + " seconds");
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
