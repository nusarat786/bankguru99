package com.inetbanking.testCases;

import org.openqa.selenium.NoAlertPresentException;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.inetbanking.pageObjects.EditAccountPage;

public class TC_0021_Balance_Enquiry_Test extends InvalidValue_BaseClass {

	@Test
	public void TC_0021_BalanceEnquiry() throws Exception 
	{
		logger.info("TC0021 Balance Enquiry Test");
		
		EditAccountPage editacc = new EditAccountPage(driver);
		logger.info("Balance Enquiry Object Is Created");

		
		editacc.clickBalanceEnquiry();
		logger.info("Balance Enquiry Link Is Clicked");

		editacc.enterAccNo("120589");
		logger.info("Acc No. Is Provided");
		
		editacc.submitAccNo();
		logger.info("Acc No. Is Submited");
		
		Thread.sleep(3000);
		
		boolean res=driver.getPageSource().contains("Balance");
		
		
		if(res) {
			logger.info("TC0021 Balance Enquiry Test--- passed");
			Assert.assertTrue(true);
		}else {
			logger.info("TC0021 Balance Enquiry Test ---- Failed ---- something went wrong");
			captureScreen(driver,"edit Account");
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
