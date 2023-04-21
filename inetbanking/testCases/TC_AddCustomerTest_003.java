package com.inetbanking.testCases;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.openqa.selenium.Dimension;


import com.inetbanking.pageObjects.AddCustomerPage;
import com.inetbanking.pageObjects.LoginPage;



public class TC_AddCustomerTest_003 extends BaseClass
{

	@Test
	public void addNewCustomer() throws InterruptedException, IOException
	{
		
		logger.info("TC003 Add Customer Test Case");
		
		LoginPage lp=new LoginPage(driver);
		
		logger.info("Trying to login");
		
		lp.setUserName(username);
		logger.info("User name is provided");
		
		lp.setPassword(password);
		logger.info("Passsword is provided");
		
		lp.clickSubmit();
		logger.info("Submit Button is clicked");
		
		
		logger.info("Stop Execution for 3000s");
		Thread.sleep(3000);
		
		AddCustomerPage addcust0=new AddCustomerPage(driver);
		
		
		addcust0.clickAddNewCustomer();
		logger.info("Add customer button is clicked");
		
		logger.info("Stop Execution for 3000s");
		Thread.sleep(3000);
		
		
		driver.navigate().refresh();
		logger.info("Page Is Refreshed to handle add");
		
		logger.info("Stop Execution for 3000s");
		Thread.sleep(3000);
		
		
		
		driver.switchTo().defaultContent();
		logger.info("focus come to refresehd page");
		
		
		
		AddCustomerPage addcust=new AddCustomerPage(driver);
		
		
		addcust.clickAddNewCustomer();
		logger.info("Add Customer Is Clicked");
		
		
		logger.info("Stop Execution for 3000s");
		Thread.sleep(3000);
		
		
		
		logger.info("providing customer details....");
		
		
		addcust.custName("Nusarat");
		addcust.custgender("male");
		addcust.custdob("02","10","2002");
		Thread.sleep(5000);
		addcust.custaddress("INDIA");
		addcust.custcity("Vadodara");
		addcust.custstate("Gujarat");
		addcust.custpinno("390019");
		addcust.custtelephoneno("9586213283");
		
		String email=randomestring()+"@gmail.com";
		addcust.custemailid(email);
		addcust.custpassword("abcdef");
		addcust.custsubmit();
		
		Thread.sleep(3000);
		
		logger.info("validation started....");
		
		boolean res=driver.getPageSource().contains("Customer Registered Successfully!!!");
		
		if(res==true)
		{
			Assert.assertTrue(true);
			logger.info("test case passed....");	
		}
		else
		{
			logger.info("test case failed....");
			captureScreen(driver,"addNewCustomer");
			Assert.assertTrue(false);
		}
			
	}
	
	
}
