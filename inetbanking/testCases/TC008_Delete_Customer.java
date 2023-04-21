package com.inetbanking.testCases;

import java.io.IOException;

import org.openqa.selenium.NoAlertPresentException;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.inetbanking.pageObjects.AddCustomerPage;
import com.inetbanking.pageObjects.DeleteCustomerPage;

public class TC008_Delete_Customer extends InvalidValue_BaseClass {

	@Test
	public void deleteCustomer() throws InterruptedException, IOException {

		logger.info("TC009 Deelete Customer Test");
		
		logger.info("First Will Add New Customer To Get Id");
		

		AddCustomerPage addcust=new AddCustomerPage(driver);
		logger.info("Add New Customer Page Object is created ");
		
		
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
		
		logger.info("validation started for creating new customer....");
		
		boolean res=driver.getPageSource().contains("Customer Registered Successfully!!!");
		
		if(res==true)
		{
			 String cusId = addcust.getNewCustomerId();	
			 System.out.println(cusId);
			 
			 Thread.sleep(3000);
			 
			 addcust.clickManager();
			 
			 Thread.sleep(3000);
			 
			 
			 logger.info("TC008 Deelete Customer scearch Test Case");

				DeleteCustomerPage deletecust = new DeleteCustomerPage(driver);

				deletecust.clickDeleteCustomer();
				logger.info("Delete Customer Is Clicked");

				logger.info("Stop Execution for 3000s");
				Thread.sleep(3000);

				logger.info("providing customer id....");
				

				deletecust.enterCustId(cusId);

				deletecust.submitCustId();
				
				
				if (isAlertPresent() == true) {
					String alertText = driver.switchTo().alert().getText();

					System.out.println(alertText);
					if (alertText.equalsIgnoreCase("Do you really want to delete this Customer?")) {

						// accept delete confirm
						driver.switchTo().alert().accept();// close alert
						Thread.sleep(3000);

						if (isAlertPresent() == true) {
							
							// accept delete confirm
							
							String alerText2 = driver.switchTo().alert().getText();
							
							//System.out.println(cusId + ": " + alerText2);
							if (alerText2.equalsIgnoreCase("Customer deleted successfully!!")) {

								driver.switchTo().alert().accept();
								logger.warn("TC009 Deelete Customer Test Passed --- Customer Deleted");
								driver.switchTo().defaultContent();
								deletecust.clickManager();
								Thread.sleep(3000);
								Assert.assertTrue(true);
								

							} else {
								//System.out.println(alerText2);
								Thread.sleep(3000);
								driver.switchTo().alert().accept();
								logger.info("TC009 Deelete Customer Failed -- customer can not be deleted or alert text problem ");
								captureScreen(driver, "TC009 Deelete Customer __ alert text mismatch");
								driver.switchTo().defaultContent();
								deletecust.clickManager();
								Thread.sleep(3000);
								Assert.assertTrue(false);
								
							}

						} else {
							
							logger.info("Failed -- something went wrong");
							captureScreen(driver, "TC009 Deelete Customer __ went wrong after alert ");
							driver.switchTo().defaultContent();
							deletecust.clickManager();
							Thread.sleep(3000);
							Assert.assertTrue(false);
							

						}
					}else {

						driver.switchTo().alert().accept();
						logger.info("TC009 Deelete Customer  Failed---- test failed with failed args");
						driver.switchTo().defaultContent();
						deletecust.clickManager();
						Thread.sleep(3000);
						Assert.assertTrue(false);
						

					}

				} else {
					logger.info("Failed -- something went wrong");
					captureScreen(driver, "TC009 Deelete Customer --went wrong in alert ");
					driver.switchTo().defaultContent();
					deletecust.clickManager();
					Thread.sleep(3000);
					Assert.assertTrue(false);
					
				}
				
				
				
			 
			 
		}
		else
		{
			logger.info("TC009 Deelete Customer Test Sub Part Failed Add New Customer");
			captureScreen(driver,"TC009 Deelete Customer Test Sub Part Failed Add New Customer ");
			Assert.assertTrue(false);
		}
		
		
}

	public boolean isAlertPresent() // user defined method created to check alert is presetn or not
	{
		try {
			driver.switchTo().alert();
			return true;
		} catch (NoAlertPresentException e) {
			return false;
		}

	}

	
	
}
