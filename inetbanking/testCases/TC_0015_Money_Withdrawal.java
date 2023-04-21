package com.inetbanking.testCases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.inetbanking.pageObjects.AccountWithdrawalPage;

public class TC_0015_Money_Withdrawal extends InvalidValue_BaseClass {

	
	@Test
	public void TC_0015_Widrawa_Money() throws InterruptedException, IOException {

		logger.info("TC0015 Withdraw Money Test");


		AccountWithdrawalPage editacc = new AccountWithdrawalPage(driver);
		logger.info("Withdraw Page Is Created");
		
		editacc.clickWidrawal();
		logger.info("Widrawal  Link Is Clicked");
		
		logger.info("providing customer id....");
		editacc.enterAccNo("120589");
		
		logger.info("providing amount....");
		editacc.enterAmmount("500");
		
		logger.info("providing description....");
		editacc.enterDescription("test");
		
		
		logger.info("sleep for 3000 second");
		Thread.sleep(3000);
		
		editacc.accsubmit();
		logger.info("form is submitted ");

		logger.info("sleep for 3000 second");
		Thread.sleep(3000);
		
		logger.info("validation started....");
		
		boolean res=driver.getPageSource().contains("Transaction details of Withdrawal for Account");
		
		if(res==true)
		{
			Assert.assertTrue(true);
			logger.info("test case passed....");	
		}
		else
		{
			logger.info("test case failed....");
			captureScreen(driver,"TC_0015 Withdraw Money");
			Assert.assertTrue(false);
		}
			
	}
	
}
