package com.inetbanking.testCases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.inetbanking.pageObjects.AccountWithdrawalPage;

public class TC_0017_Deposite_Money_Test extends InvalidValue_BaseClass {

	@Test
	public void TC_0017_Deposite_Money() throws InterruptedException, IOException {

		logger.info("TC0017 Deposite Money Test");


		AccountWithdrawalPage editacc = new AccountWithdrawalPage(driver);
		logger.info("Deposit Page Is Created");
		
		editacc.clickDeposite();
		logger.info("Deposite  Link Is Clicked");
		
		logger.info("providing customer id....");
		editacc.enterAccNo("120589");
		
		logger.info("providing amount....");
		editacc.enterAmmount("500");
		
		logger.info("providing description....");
		editacc.enterDescription("test");
		
		
		editacc.accsubmit();
		logger.info("Deposite  Link Is Clicked");
		
		logger.info("sleep for 3000 second");
		Thread.sleep(3000);
		
		logger.info("validation started....");
		
		boolean res=driver.getPageSource().contains("Deposite Success");
		
		if(res==true)
		{
			Assert.assertTrue(true);
			logger.info("TC0017 Deposite Money Test Passed....");	
		}
		else
		{
			logger.info("TC_00_17 Deposite Money Test Failed....");
			captureScreen(driver,"TC0017 Deposite Money Test");
			Assert.assertTrue(false);
		}
			
	}
}
