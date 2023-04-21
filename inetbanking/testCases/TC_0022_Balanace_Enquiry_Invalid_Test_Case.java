package com.inetbanking.testCases;

import java.io.IOException;



import org.openqa.selenium.NoAlertPresentException;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.inetbanking.pageObjects.EditAccountPage;
import com.inetbanking.utilities.XLUtils;


import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;



public class TC_0022_Balanace_Enquiry_Invalid_Test_Case extends InvalidValue_BaseClass {

	
	@Test(dataProvider = "BalanaceEnquiry")
	public void TC_0022_Balanace_Enquiry (
			String subTcNo, 
			String title,
			String cusId
			
			) throws InterruptedException, IOException {

		
		
		logger.info("TC0022 Balance Enquiry Inavlid Value Test");


		EditAccountPage editacc = new EditAccountPage(driver);
		logger.info("Balance Enquiry Object Is Created");

		
		editacc.clickBalanceEnquiry();
		logger.info("Balance Enquiry Link Is Clicked");

		
		editacc.enterAccNo(cusId);
		logger.info("Acc No. Is Provided");
		
		if(cusId.equalsIgnoreCase("empty")) {
			logger.info("Blank Value Test");
			editacc.resetCustId();
		}
		
		editacc.submitAccNo();
		logger.info("Acc No. Is Submited");
		
		logger.info("Sleep for 3000 ms");
		Thread.sleep(3000);
		
		
		String testCase = subTcNo + " _ " + title  + " _ ";
		logger.info("Sub Test Case Is : " + testCase);
		
		logger.info("Testing....");

		if (isAlertPresent() == true) {
				

					
					String alerText2 = driver.switchTo().alert().getText();
					
					System.out.println(cusId + ": " + alerText2);
					if (alerText2.equalsIgnoreCase("Account does not exist")) {

						driver.switchTo().alert().accept();
						logger.info(testCase + "Test Passed --- Account does not exist confirmed");
						driver.switchTo().defaultContent();
						editacc.clickManager();
						Thread.sleep(3000);
						Assert.assertTrue(true);
						

					}else if (alerText2.equalsIgnoreCase("Please fill all fields")) {

						driver.switchTo().alert().accept();
						logger.info(testCase + "Test Passed --- Invalid/ blank value confirmed");
						driver.switchTo().defaultContent();
						editacc.clickManager();
						Thread.sleep(3000);
						Assert.assertTrue(true);
						
						
					}else {
						//System.out.println(alerText2);
						Thread.sleep(3000);
						driver.switchTo().alert().accept();
						logger.info(testCase + "Failed -- value accepted in incorrect fileds");
						captureScreen(driver,testCase + "Balance Equiry Invalid Value --alert text mismatch");
						Assert.assertTrue(false);
						driver.switchTo().defaultContent();
						editacc.clickManager();
						Thread.sleep(3000);

					}

		} else {
			logger.info("Failed -- something went wrong");
			captureScreen(driver, testCase + "Delete Customer--went wrong -- accepted invalid value  ");
			Assert.assertTrue(false);
			driver.switchTo().defaultContent();
			editacc.clickManager();
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

	@DataProvider(name = "BalanaceEnquiry")
	String[][] getData() throws IOException {
		String path = System.getProperty("user.dir") + "/src/test/java/com/inetbanking/testData/LoginData.xlsx";

		int rownum = XLUtils.getRowCount(path, "BalanaceEnquiry");
		int colcount = XLUtils.getCellCount(path, "BalanaceEnquiry", 1);

		System.out.println(rownum);

		String BalanaceEnquiryData[][] = new String[rownum][colcount];

		for (int i = 1; i <= rownum; i++) {
			for (int j = 0; j < colcount; j++) {
				BalanaceEnquiryData[i - 1][j] = XLUtils.getCellData(path, "BalanaceEnquiry", i, j);// 1 0
				System.out.print(XLUtils.getCellData(path, "BalanaceEnquiry", i, j));

			}

		}

		System.out.print(BalanaceEnquiryData);
		return BalanaceEnquiryData;
	}
	
}
