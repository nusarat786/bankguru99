package com.inetbanking.testCases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.inetbanking.pageObjects.NewAccountPage;
import com.inetbanking.pageObjects.LoginPage;

public class TC00_10_New_Account_Test extends InvalidValue_BaseClass  {

	@Test
	public void addNewAccount() throws InterruptedException, IOException
	{
		
		logger.info("TC00_10 Add New Account Test Case");
		
		
		NewAccountPage addacc=new NewAccountPage(driver);
		logger.info("New Account Object Page Is Created");
		
		addacc.clickNewAccount();
		logger.info("Add Account Is Clicked");
		
		
		logger.info("Stop Execution for 3000s");
		Thread.sleep(3000);
		
		
		
		logger.info("providing new account details");
		
		
		addacc.enterCustId("65361");
		logger.info("Customer Id Is Entered");
		
		addacc.selectAccountType("Current");
		logger.info("Account Type Is Entered");
		
		addacc.enterIntitialDeposite("10000");
		logger.info("Initial Deposite Is Entered");
		
		addacc.custsubmit();
		logger.info("Submit Button Is Clicked");
		
		logger.info("Sleep For 3000 ms");
		Thread.sleep(3000);
		
		logger.info("validation started....");
		
		boolean res=driver.getPageSource().contains("Account Generated Successfully!!!");
		
		if(res==true)
		{
			Assert.assertTrue(true);
			logger.info("TC00_10 Add New Account Test Case Passed....");	
		}
		else
		{
			logger.info("TC00_10 Add New Account Test Case Failed....");
			captureScreen(driver,"TC00_10 Add New Account Test Case");
			Assert.assertTrue(false);
		}
			
	}
	
	
	
}
