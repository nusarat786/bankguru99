package com.inetbanking.testCases;

import org.openqa.selenium.NoAlertPresentException;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.inetbanking.pageObjects.EditAccountPage;

public class TC_0023_Mini_Statement_Test extends InvalidValue_BaseClass {

	@Test
	public void TC_0023_MiniStatement() throws Exception 
	{
		
		logger.info("TC0023 Mini Statement ");

		EditAccountPage editacc = new EditAccountPage(driver);
		logger.info("Mini Statement Page object Created");
		
		
		editacc.clickMiniStatement();
		logger.info("Click On Mini Statement Link");
		
		editacc.enterAccNo("120589");
		logger.info("Account No is Provided");
		
		editacc.submitAccNo();
		logger.info("Submit Button is Clicked");
		
		logger.info("Sleep For 3000 ms");
		Thread.sleep(3000);
		
		boolean res=driver.getPageSource().contains("Mini Statement Success");
		
		
		if(res==true) {
			logger.info("test case passed");
			Assert.assertTrue(true);
		}else {
			logger.info("test case failed.... something went wrong");
			captureScreen(driver,"TC0023 Mini Statement");
			Assert.assertTrue(false);
		}
	
	}
	
	public boolean isAlertPresent() //user defined method created to check alert is presetn or not
	{
		try
		{
		driver.switchTo().alert();
		return true;
		}
		catch(NoAlertPresentException e)
		{
			return false;
		}
		
	}
	
		
}
