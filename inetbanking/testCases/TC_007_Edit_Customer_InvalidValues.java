package com.inetbanking.testCases;

import java.io.IOException;
import java.text.SimpleDateFormat;

import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.inetbanking.pageObjects.AddCustomerPage;
import com.inetbanking.pageObjects.EditCustomerPage;
import com.inetbanking.pageObjects.LoginPage;
import com.inetbanking.utilities.XLUtils;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class TC_007_Edit_Customer_InvalidValues extends Invalid_Value_Base_Class {
	
	

	@Test(dataProvider = "EditCustomerData")
	public void addCustomerDDT(String adress, String city, String state, String pin, String mobile, String email,
			String cusId) throws InterruptedException, IOException {
		System.out.println("adress: " + adress);
		System.out.println("city: " + city);
		System.out.println("State: " + state);
		System.out.println("pin: " + pin);
		System.out.println("mobile: " + mobile);
		System.out.println("email: " + email);
		System.out.println("passwordd: " + cusId);

		logger.info("TC007 Edit Customer With Inavlid Values");
		EditCustomerPage editcust0 = new EditCustomerPage(driver);
		
		editcust0.enterCustId(cusId);
		logger.info("enter customer id ");

		editcust0.submitCustId();
		logger.info("submit button is clicked ");

		Thread.sleep(3000);

		EditCustomerPage custedit = new EditCustomerPage(driver);

		logger.info("providing data to fields ");

		custedit.custaddress(adress);

		custedit.custcity(city);

		custedit.custstate(state);

		custedit.custpinno(pin);

		custedit.custtelephoneno(mobile);

		custedit.custemailid(email);

		custedit.editsubmit();
		logger.info("submit button is clicked ");

		if (isAlertPresent()) {

			String alertText = driver.switchTo().alert().getText();

			if (alertText.equalsIgnoreCase("No Changes made to Customer records")) {

				String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());
				// captureScreen(driver,"edit Customer01" + timeStamp);
				driver.switchTo().alert().accept();
				logger.info("test case failed.... data not updated message");
				captureScreen(driver, "edit Customer01" + timeStamp);

				Thread.sleep(3000);
				
				driver.switchTo().defaultContent();
				
				Assert.assertTrue(false);
				Thread.sleep(3000);

			} else if (alertText.equalsIgnoreCase("Please fill all fields")) {
				LoginPage lp0 = new LoginPage(driver);
				driver.switchTo().alert().accept();
				logger.info("test case passed empty/invalid entry....");
				driver.switchTo().defaultContent();
				Assert.assertTrue(true);
				Thread.sleep(3000);
			} else {
				LoginPage lp0 = new LoginPage(driver);
				driver.switchTo().alert().accept();
				logger.info("test case passed entry updated....");
				driver.switchTo().defaultContent();
				Assert.assertTrue(true);
				Thread.sleep(3000);
			}

		} else {
			LoginPage lp0 = new LoginPage(driver);
			logger.info("test case failed.... something went wrong");
			captureScreen(driver, "edit Customer02");
			driver.switchTo().defaultContent();
			Assert.assertTrue(true);
			Thread.sleep(3000);
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

	@DataProvider(name = "EditCustomerData")
	String[][] getData() throws IOException {
		String path = System.getProperty("user.dir") + "/src/test/java/com/inetbanking/testData/LoginData.xlsx";

		int rownum = XLUtils.getRowCount(path, "EditCustomer");
		int colcount = XLUtils.getCellCount(path, "EditCustomer", 1);

		System.out.println(rownum);

		String editCustomerData[][] = new String[rownum][colcount];

		for (int i = 1; i <= rownum; i++) {
			for (int j = 0; j < colcount; j++) {
				editCustomerData[i - 1][j] = XLUtils.getCellData(path, "EditCustomer", i, j);// 1 0
				System.out.print(XLUtils.getCellData(path, "EditCustomer", i, j));

			}

		}

		System.out.print(editCustomerData);
		return editCustomerData;
	}
}
