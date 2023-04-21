package com.inetbanking.testCases;

import java.io.IOException;

import org.openqa.selenium.NoAlertPresentException;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.inetbanking.pageObjects.AddCustomerPage;
import com.inetbanking.pageObjects.LoginPage;
import com.inetbanking.pageObjects.EditCustomerPage;
public class TC005_Edit_Customer_Scearch extends BaseClass {

	
	@Test
	public void editCustomerScearch() throws InterruptedException, IOException
	{
		
		logger.info("TC005 edit Customer scearch Test Case");
		
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
		
		EditCustomerPage addcust0=new EditCustomerPage(driver);
		
		
		addcust0.clickEditCustomer();
		logger.info("Add customer button is clicked");
		
		logger.info("Stop Execution for 3000s");
		Thread.sleep(3000);
		
		
		driver.navigate().refresh();
		logger.info("Page Is Refreshed to handle add");
		
		logger.info("Stop Execution for 3000s");
		Thread.sleep(3000);
		
		
		
		driver.switchTo().defaultContent();
		logger.info("focus come to refresehd page");
		
		
		
		EditCustomerPage editcust=new EditCustomerPage(driver);
		
		
		editcust.clickEditCustomer();
		logger.info("Edit Customer Is Clicked");

		logger.info("Stop Execution for 3000s");
		Thread.sleep(3000);
		
		
		
		logger.info("providing customer id....");
		
		
		// Define a string array
	    String[] cusId = {"65361","89074","-","absd","check clear button"};

	    // Loop through the array and print each element
	    for (int i = 0; i < cusId.length; i++) {
	    	
	    	
	    	
	    	
	    	
	    	editcust.enterCustId(cusId[i]);
			editcust.submitCustId();
			
			
			
			if(isAlertPresent()==true)
			{
				String alertText = driver.switchTo().alert().getText();
				
				System.out.println(alertText);
				if(alertText.equalsIgnoreCase("Customer does not exist!!")) {
					
					driver.switchTo().alert().accept();//close alert
					Assert.assertTrue(true);
					logger.warn("Test Passed ---- Customer Not Exist");
					driver.switchTo().defaultContent();
					Thread.sleep(3000);
				}else {
					driver.switchTo().alert().accept();//close alert
					logger.warn("Test Passed --- Scearch Box is Empty or String is Entered");
					driver.switchTo().defaultContent();
					editcust.resetCustId();
					logger.warn("Test Passed --- Reset Is Clicked");
					Assert.assertTrue(true);
					Thread.sleep(3000);
				}	
		
			}
			else if(driver.getTitle().equalsIgnoreCase("Guru99 Bank Edit Customer Entry Page"))
			{
				
				logger.warn("Test Passed --- Redirect to edit page");
				
				EditCustomerPage editcust2=new EditCustomerPage(driver);
				editcust2.clickEditCustomer();
				Assert.assertTrue(true);
				Thread.sleep(3000);
				
				
			}else {
				logger.info("Failed -- to Load Page");
				System.out.println("error");
				captureScreen(driver,"Edit Scearch For Customer");
				Assert.assertTrue(false);
				EditCustomerPage editcust2=new EditCustomerPage(driver);
				editcust2.clickEditCustomer();
				Thread.sleep(3000);
				
			}
	      
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
