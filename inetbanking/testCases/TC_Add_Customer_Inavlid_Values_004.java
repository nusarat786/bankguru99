package com.inetbanking.testCases;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.openqa.selenium.NoAlertPresentException;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.inetbanking.pageObjects.AddCustomerPage;
import com.inetbanking.pageObjects.LoginPage;
import com.inetbanking.utilities.XLUtils;

public class TC_Add_Customer_Inavlid_Values_004 extends BaseClass {
	
	
	@Test(dataProvider="AddCustomerData")
	public void addCustomerDDT(
			String name,
			String gender,
			String day,
			String month,
			String year,
			String country,
			String adress , 
			String city, 
			String state,
			String pin,
			String mobile,
			String email,
			String newPassword) throws InterruptedException, IOException
	{
		System.out.println("name: " + name);
		System.out.println("gender: " + gender);
		System.out.println("day: " + day);
		System.out.println("month: " + month);
		System.out.println("year: " + year);
		System.out.println("adress: " + adress);
		System.out.println("city: " + city);
		System.out.println("State: " + state);
		System.out.println("pin: " + pin);
		System.out.println("mobile: " + mobile);
		System.out.println("email: " + email);
		System.out.println("passwordd: " + newPassword);
		
//		 // Split the input date string into day, month, and year substrings
//	      String[] dateComponents = dob.split("/");
//
//	      // Convert the substrings to integers
//	      int day = Integer.parseInt(dateComponents[0]);
//	      int month = Integer.parseInt(dateComponents[1]);
//	      int year = Integer.parseInt(dateComponents[2]);
	      
	    //  System.out.println(year);
		
//		// Create a DateTimeFormatter for the input date string
//	      DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("d/M/yyyy");
//
//	      // Parse the input date string into a LocalDate object
//	      LocalDate date = LocalDate.parse(dob, inputFormatter);
//
//
//	      // Extract the day, month, and year components from the LocalDate object
//	      int day = date.getDayOfMonth();
//	      int month = date.getMonthValue();
//	      int year = date.getYear();
		
		
		logger.info("TC004 Add Customer With Inavlid Values");
		
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
		
		
		addcust.custName(name);
		addcust.custgender(gender);
		//addcust.custdob(Integer.toString(day),Integer.toString(month),Integer.toString(year));
		addcust.custdob(day,month,year);
		
		Thread.sleep(5000);
		addcust.custaddress(country);
		addcust.custcity(city);
		addcust.custstate(state);
		addcust.custpinno(pin);
		addcust.custtelephoneno(mobile);
		
//		String emaily=randomestring();
//				//"@gmail.com";
		
		addcust.custemailid(randomestring());
		addcust.custpassword(newPassword);
		addcust.custsubmit();
		
		Thread.sleep(3000);
		
		logger.info("validation started....");
		
		//boolean res=driver.getPageSource().contains("please fill all fields");
		
		if(isAlertPresent()==true)
		{
			driver.switchTo().alert().accept();//close alert
			logger.warn("Test Passed");
			Assert.assertTrue(true);
			driver.switchTo().defaultContent();
			lp.clickLogout();
			Thread.sleep(3000);
			driver.switchTo().alert().accept();//close alert
			driver.switchTo().defaultContent();
			Thread.sleep(3000);
		}
		else
		{
			
			logger.info("Failed -- Add Customer Accepting Invalid Argument ---");
			Assert.assertTrue(false);
			System.out.println("error");
			Thread.sleep(3000);
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
	
	
	@DataProvider(name="AddCustomerData")
	String [][] getData() throws IOException
	{
		String path=System.getProperty("user.dir")+"/src/test/java/com/inetbanking/testData/LoginData.xlsx";
		
		int rownum=XLUtils.getRowCount(path, "AddCustomer");
		int colcount=XLUtils.getCellCount(path,"AddCustomer",1);
		
		System.out.println(rownum);
		
		String addCustomerData[][]=new String[rownum][colcount];
		
		for(int i=1;i<=rownum;i++)
		{
			for(int j=0;j<colcount;j++)
			{
				addCustomerData[i-1][j]=XLUtils.getCellData(path,"AddCustomer", i,j);//1 0
				System.out.print(XLUtils.getCellData(path,"AddCustomer", i,j));
				
				
			}
				
		}
		
		System.out.print(addCustomerData);
	return addCustomerData;
	}	
}
