package com.inetbanking.testCases;

import java.io.IOException;

import org.openqa.selenium.NoAlertPresentException;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.inetbanking.pageObjects.CustomizedStatementPage;
import com.inetbanking.utilities.XLUtils;

public class TC_0025_Customized_Statement_Test extends InvalidValue_BaseClass {

	@Test
	public void TC_0025_Customized_Statement () throws InterruptedException, IOException {

		logger.info("TC0025 Customized_Statement");


		CustomizedStatementPage custst = new CustomizedStatementPage(driver);
		logger.info("Customized Statement Page Object Is Created ");

		custst.clickCustomizedStatement();
		logger.info("Customized Statement Link Is Clicked");

		custst.enterAccNo("120589");
		logger.info("Customized Statement Link Is Clicked");

		custst.enterFromDate("04","01","2023");
		logger.info("Enter From Date");

		custst.enterToDate("04","11","2023");
		logger.info("Enter To Date");

		custst.enterMinimumAmount("1");
		logger.info("Enter Minimum Amount");

		custst.enterNumberOfTransaction("5");
		logger.info("Enter No Of Transaction ");

		custst.submitAccNo();
		logger.info("Submit Acc No.");

		
		Thread.sleep(3000);
		logger.info("Sleep For 3000");

		
		
		boolean res=driver.getPageSource().contains("Balance");
		
		logger.info("Validating........");
		
		
		if(res) {
			logger.info("test case passed");
			Assert.assertTrue(true);
		}else {
			logger.info("test case failed.... something went wrong");
			captureScreen(driver,"TC_0025_Customized_Statement");
			Thread.sleep(6000);
			Assert.assertTrue(false);
		}
		
	}

	
	
	
}
