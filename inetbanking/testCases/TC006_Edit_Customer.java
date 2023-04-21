package com.inetbanking.testCases;

import java.io.IOException;
import java.text.SimpleDateFormat;

import org.openqa.selenium.NoAlertPresentException;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.inetbanking.pageObjects.AddCustomerPage;
import com.inetbanking.pageObjects.EditCustomerPage;
import com.inetbanking.pageObjects.LoginPage;

public class TC006_Edit_Customer extends BaseClass {

	@Test
	public void editCustomer() throws InterruptedException, IOException
	{
		
		logger.info("TC006 edit Customer Test Case");
		
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
		
		EditCustomerPage editcust=new EditCustomerPage(driver);
		
		
		editcust.clickEditCustomer();
		logger.info("Add customer button is clicked");
		
		logger.info("Stop Execution for 3000s");
		Thread.sleep(3000);
		
		
		driver.navigate().refresh();
		logger.info("Page Is Refreshed to handle add");
		
		logger.info("Stop Execution for 3000s");
		Thread.sleep(3000);
		
		
		
		driver.switchTo().defaultContent();
		logger.info("focus come to refresehd page");
		
		
		
		EditCustomerPage editcust0=new EditCustomerPage(driver);
		
		
		editcust0.clickEditCustomer();
		logger.info("Edit Customer Is Clicked");

		logger.info("Stop Execution for 3000s");
		Thread.sleep(3000);
		
		
		
		
		
	    
		
	    editcust.enterCustId("65361");
	    logger.info("enter customer id "); 	
		
	    editcust.submitCustId();
	    logger.info("submit button is clicked "); 	
	    
	    
	    Thread.sleep(3000);
	    
	    EditCustomerPage custedit=new EditCustomerPage(driver);
	    
	    logger.info("providing data to fields "); 
	    custedit.custaddress("c 30 shalimar");
	    
	    custedit.custcity("Anand");
	    
	    custedit.custstate("Maharastra");
	    
	    custedit.custpinno("980019");
	    
	    custedit.custtelephoneno("980098989");
	    
	    String email=randomestring()+"@gmail.com";
	    
	    custedit.custemailid(email);
	    
	    custedit.editsubmit();
	    logger.info("submit button is clicked "); 
	    
	    
	    if(isAlertPresent()) {
	    	
	    	String alertText = driver.switchTo().alert().getText();
	    	
	    	if(alertText.equalsIgnoreCase("No Changes made to Customer records")) {
	    		
	    		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());
	    		//captureScreen(driver,"edit Customer01" + timeStamp);
	    		
	    		
	    		driver.switchTo().alert().accept();
				logger.info("test case failed.... data not updated message");
				captureScreen(driver,"edit Customer01" + timeStamp);
				Assert.assertTrue(false);
	    	
	    	}else if(alertText.equalsIgnoreCase("Please fill all fields")) {
	    		
	    		driver.switchTo().alert().accept();
	    		logger.info("test case passed empty/invalid entry....");
	    		Assert.assertTrue(true);
	    	}else {
	    		driver.switchTo().alert().accept();
	    		logger.info("test case passed entry updated....");
	    		Assert.assertTrue(true);
	    	}
	    	
	    }else {
			logger.info("test case failed.... something went wrong");
			captureScreen(driver,"edit Customer02");
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
