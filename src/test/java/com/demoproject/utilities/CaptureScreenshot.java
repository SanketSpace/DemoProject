package com.demoproject.utilities;

import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import com.demoproject.base.BaseClass;

public class CaptureScreenshot extends BaseClass{
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


}
