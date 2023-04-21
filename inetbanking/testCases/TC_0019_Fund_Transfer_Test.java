package com.inetbanking.testCases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.inetbanking.pageObjects.FundTransferPage;

public class TC_0019_Fund_Transfer_Test   extends InvalidValue_BaseClass {

	@Test
	public void TC_0019_Fund_Transfe() throws InterruptedException, IOException {

		logger.info("TC0019 Fund Transfer Test");


		FundTransferPage fundtransfer = new FundTransferPage(driver);
		
		fundtransfer.clickFundTransfer();
		logger.info("Fund Transfer  Link Is Clicked");
		
		logger.info("providing acc....");
		fundtransfer.enterPayerAccountNo("120589");
		
		fundtransfer.enterPayeeAccountNo("120588");
		
		logger.info("providing amount....");
		fundtransfer.enterAmmount("1");
		
		logger.info("providing description....");
		fundtransfer.enterDescription("test");
		
		
		fundtransfer.accsubmit();
		logger.info("form is submitted ");
		
		
		logger.info("sleep for 3000 second");
		Thread.sleep(3000);
		
		logger.info("validation started....");
		
		boolean res=driver.getPageSource().contains("Fund Transfer Details");
		


		if(res==true)
		{
			Assert.assertTrue(true);
			logger.info("TC0019 Fund Transfer Test Passed....");	
		}
		else
		{
			logger.info("TC0019 Fund Transfer Test Failed....");
			captureScreen(driver,"TC0019 Fund Transfer Test");
			Assert.assertTrue(false);
		}
			
	}
	
}
