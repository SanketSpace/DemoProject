package com.demoproject.base;

import java.time.Duration;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Action;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import com.demoproject.utilities.CaptureScreenshot;
import com.demoproject.utilities.Verify;
import com.demoproject.utilities.Waits;

import io.github.bonigarcia.wdm.WebDriverManager;


public class BaseClass {

	public static WebDriver driver;
	public static Logger logger;
		
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
			System.setProperty("webdriver.edge.driver", "C:\\SanketProject\\DemoProject\\drivers\\msedgedriver.exe");
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
		wait.customWait("Page loading",15);
		logger.info("URL opened !");
	}

	@AfterSuite
	public void tearDown() {
		wait.customWait("Closing browser in", 3);
		driver.close();
		driver.quit();
	}

	/***
	 * capture screenshot
	 * 
	 ***/
	public static CaptureScreenshot screen = new CaptureScreenshot();
	/***
	 * assertion
	 ***/
	public static Verify verify = new Verify();

	/***
	 * waits
	 ***/
	public static Waits wait = new Waits();

}
