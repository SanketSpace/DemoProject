package com.demoproject.utilities;

import static org.testng.Assert.assertTrue;
import com.demoproject.base.BaseClass;

public class Verify extends BaseClass{

	public void verifyString(String actualResult, String expectedResult, String testCase) {
		if(actualResult.equals(expectedResult)) {
			assertTrue(true);
			logger.info("Test case passed: "+testCase);
			screen.captureScreen(testCase);
		}else {
			assertTrue(false);
			logger.info("Test case failed: "+testCase);
			screen.captureScreen(testCase);
		}
	}
	
	public void verifyBoolean(Boolean result , String testCase) {
		if(result.equals(true)) {
			assertTrue(true);
			logger.info("Test case passed: "+testCase);
			screen.captureScreen(testCase);
		}else {
			assertTrue(false);
			logger.info("Test case failed: "+testCase);
			screen.captureScreen(testCase);
		}
	}
}