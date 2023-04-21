package com.inetbanking.testCases;

import org.apache.http.impl.client.HttpClientBuilder;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.inetbanking.pageObjects.EditAccountPage;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;

import java.net.HttpURLConnection;
import java.net.URL;
public class TC00_12_Edit_Account extends InvalidValue_BaseClass  {
	
	
	@Test
	public void editAccount() throws Exception 
	{
		
		logger.info("TC0012 Edit Account Test");
		
		EditAccountPage editacc = new EditAccountPage(driver);
		logger.info("Edit Account Page Object Is Created");
		
		editacc.clickEditAccount();
		logger.info("Edit Account Is Clicked");

		
		editacc.enterAccNo("120589");
		logger.info("providing Acc No. ....");
		
		editacc.submitAccNo();
		logger.info("Acc No. Is Provided");
		
		logger.info("Sleep for 3000 ms");
		Thread.sleep(3000);

		boolean res=driver.getPageSource().contains("Account Number");

		
		if(res) {
			logger.info("TC0012 Edit Account Test Passed");
			Assert.assertTrue(true);
		}else {
			logger.info("test case failed.... something went wrong");
			captureScreen(driver,"TC_0012 Edit Account Test");
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
