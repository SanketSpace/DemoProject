package com.demoproject.base;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import freemarker.log.Logger;

public class ExtentListenerClass implements ITestListener {
	ExtentSparkReporter htmlReporter;
	ExtentReports reports;
	ExtentTest test;

	public void configureReport() {
		//to get browserName from properties file
		ReadConfig readConfig = new ReadConfig();

		String fileName = System.getProperty("user.dir") + "\\configuration\\config.properties";
		String timestamp = new SimpleDateFormat("yyyy.MM.dd.hh.mm.ss").format(new Date());
		String reportName = "DemoProject" + timestamp + ".html";
		htmlReporter = new ExtentSparkReporter(System.getProperty("user.dir") + "\\reports\\" + reportName);
		reports = new ExtentReports();
		reports.attachReporter(htmlReporter);
		//add system information/environment info to reports
		reports.setSystemInfo("Machine:", "LG");
		reports.setSystemInfo("OS", "windows 10");
		reports.setSystemInfo("browser:", readConfig.getBrowserName());

		reports.setSystemInfo("user name:", "ST");
		//configuration to change look and feel of report
		htmlReporter.config().setDocumentTitle("DemoProject");
		htmlReporter.config().setReportName("DemoProject Reporter");
		htmlReporter.config().setTheme(Theme.DARK);
	}

	//OnStart method is called when any Test starts.
	public void onStart(ITestContext Result) {
		configureReport();		
	}

	//onFinish method is called after all Tests are executed
	public void onFinish(ITestContext Result) {
		reports.flush();
	}

	// When Test case get failed, this method is called. 
	public void onTestFailure(ITestResult Result) {
		System.out.println("Test Case failed : " + Result.getName());
		test = reports.createTest(Result.getName());
		test.log(Status.FAIL, MarkupHelper.createLabel("Test Case Failed : " + Result.getName(), ExtentColor.RED));
		try {
			String screenShotPath = System.getProperty("user.dir") + "\\screenshots\\" + Result.getName() + ".png";
			File screenShotFile = new File(screenShotPath);
			if (screenShotFile.exists()) {
				test.fail("ScreenShot captured" + test.addScreenCaptureFromPath(screenShotPath));
			}else {
				System.out.println("Test Failed : Sceenshot not found");
			}
		} catch (Exception e) {
			// TODO: handle exception			
		}
		
	}

	// When Test case get Skipped, this method is called. 
	public void onTestSkipped(ITestResult Result) {
		System.out.println("Skipped Test Case : " + Result.getName());
		test = reports.createTest(Result.getName());
		test.log(Status.SKIP, MarkupHelper.createLabel("Skipped Test case : " + Result.getName(), ExtentColor.YELLOW));
	}

	// When Test case get Started, this method is called. 
	public void onTestStart(ITestResult Result) {
		System.out.println("Test Case : " + Result.getName());
	}

	// When Test case get passed, this method is called. 
	public void onTestSuccess(ITestResult Result) {
		System.out.println("Test Case Passed :" + Result.getName());
		test = reports.createTest(Result.getName());
		test.log(Status.PASS, MarkupHelper.createLabel("Test case Passed : " + Result.getName(), ExtentColor.GREEN));
		try {
			String screenShotPath = System.getProperty("user.dir") + "\\screenshots\\" + Result.getName() + ".png";
			File screenShotFile = new File(screenShotPath);
			if (screenShotFile.exists()) {
				test.fail("ScreenShot captured" + test.addScreenCaptureFromPath(screenShotPath));
			}else {
				System.out.println("Test Success : Sceenshot not found");
			}
		} catch (Exception e) {
			// TODO: handle exception			
		}
	}

	//we will just leave this method without implementation
	public void onTestFailedButWithinSuccessPercentage(ITestResult Result) {
	}
}

