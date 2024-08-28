package com.demoproject.base;

import static org.testng.Assert.assertTrue;
import java.io.File;
import java.io.IOException;
import java.time.Duration;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClassDemo {

	public static WebDriver driver;
	public static Logger logger;
	//Actions act = new Actions(driver);
	
	ReadConfig readconfig = new ReadConfig();
	public String browserName = readconfig.getBrowserName();
	public String baseUrl = readconfig.getBaseUrl();

	@BeforeSuite
	public void setUp() {
		System.out.println("Browser : " + browserName);
		System.out.println("URL : " + baseUrl);
		/***
		 * if(browserName.equalsIgnoreCase("chrome")) { driver = new ChromeDriver();
		 * }else if(browserName.equalsIgnoreCase("edge")) { driver = new EdgeDriver();
		 * }else if(browserName.equalsIgnoreCase("firefox")) { driver = new
		 * FirefoxDriver(); }
		 ***/

		switch (browserName.toLowerCase()) {
		case "chrome":
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			break;

		case "edge":
			//WebDriverManager.edgedriver().setup();
			//System.setProperty("webdriver.edge.driver", "C:\\SanketProject\\DemoProject\\drivers\\msedgedriver.exe");
			driver = new EdgeDriver();
			break;
		case "firefox":
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			break;
		default:
			logger.error("Invalid browser name : " + browserName);
		}
		// maximize window
		driver.manage().window().maximize();

		// implicit wait
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
	
		logger = LogManager.getLogger("BaseClass");

		driver.get(baseUrl);
		customWait("Page loading",10);
		logger.info("URL opened !");
	}

	@AfterSuite
	public void tearDown() {
		customWait("Closing browser in", 3);
		driver.close();
		driver.quit();
	}

	/***
	 * capture screenshot
	 * 
	 ***/
	public void captureScreen(String testCaseName) {		
		try {
			TakesScreenshot ss = (TakesScreenshot)driver;
			File src = ss.getScreenshotAs(OutputType.FILE);
			File dest = new File(System.getProperty("user.dir")+"\\screenshots\\"+testCaseName+".png");
			FileUtils.copyFile(src, dest);
			logger.info("Screenshot captured : "+testCaseName);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error(testCaseName+"Screenshot not Captured !");
		}
	}
	/***
	 * assertion
	 ***/
	public void verifyString(String actualResult, String expectedResult, String testCase) {
		if(actualResult.equals(expectedResult)) {
			assertTrue(true);
			logger.info("Test case passed: "+testCase);
			captureScreen(testCase);
		}else {
			assertTrue(false);
			logger.info("Test case failed: "+testCase);
			captureScreen(testCase);
		}
	}
	
	public void verifyBoolean(Boolean result , String testCase) {
		if(result.equals(true)) {
			assertTrue(true);
			logger.info("Test case passed: "+testCase);
			captureScreen(testCase);
		}else {
			assertTrue(false);
			logger.info("Test case failed: "+testCase);
			captureScreen(testCase);
		}
	}

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
	
	/***
	 * scroll
	 ***
	
	public void moveToElement(WebElement elementToScroll) {
		act.moveToElement(elementToScroll);
		act.perform();	
	}
	
	public void scrollToElement(WebElement elementToScroll) {
		act.scrollToElement(elementToScroll);
		act.perform();
	}
	***/

}
