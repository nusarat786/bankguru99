package com.inetbanking.testCases;

import org.openqa.selenium.NoAlertPresentException;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.inetbanking.pageObjects.EditAccountPage;

public class TC_0013_Delete_Account_Test extends InvalidValue_BaseClass{

	@Test
	public void editAccount() throws Exception 
	{
		logger.info("TC0013 Delete Account Test");

		
		EditAccountPage editacc = new EditAccountPage(driver);
		logger.info("Delete Account Page Object Is Created");
		
		editacc.clickDeleteAccount();
		logger.info("Delete Account Is Clicked");
		
		logger.info("providing Acc No. ....");
		editacc.enterAccNo("120589");
		
		editacc.submitAccNo();
		logger.info("Acc No. Is Provided");
		
		logger.info("Sleep for 3000 ms");
		Thread.sleep(3000);
		
		logger.info("Testing....");
		
		if(isAlertPresent()) {
			driver.switchTo().alert().accept();
		
			if(isAlertPresent()) {
				logger.info("TC0013 Delete Account Passed");
				Assert.assertTrue(true);
			}else {
				logger.info("TC0013 Delete Account Failed");
				captureScreen(driver,"TC0013 Delete Account Test");
				Assert.assertTrue(false);
			}
			
		}else {
			logger.info("TC0013 Delete Account Failed");
			captureScreen(driver,"TC_0013 Delete Account Test");
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
