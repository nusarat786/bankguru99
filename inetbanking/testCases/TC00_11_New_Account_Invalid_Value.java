package com.inetbanking.testCases;

import java.io.IOException;

import org.openqa.selenium.NoAlertPresentException;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.inetbanking.pageObjects.DeleteCustomerPage;
import com.inetbanking.pageObjects.NewAccountPage;
import com.inetbanking.utilities.MyScreenRecorder;
import com.inetbanking.utilities.XLUtils;

public class TC00_11_New_Account_Invalid_Value extends InvalidValue_BaseClass {

	@Test(dataProvider = "addAccountData")
	public void addAccount(
			String subTcNo, 
			String title,
			String cusId,
			String initAmount, 
			String type
			
			
			) throws Exception {
		
		MyScreenRecorder.startRecording("TC00_11 Add new Account Test Case");
		logger.info("TC00_11 Add new Account Test Case");


		NewAccountPage addacc=new NewAccountPage(driver);
		logger.info("New Account Object Page Is Created");

		
		addacc.clickNewAccount();
		logger.info("Add Account Is Clicked");
		
		
		logger.info("Stop Execution for 3000s");
		Thread.sleep(3000);
		
		
		
		logger.info("providing new account details");
		
		addacc.enterCustId(cusId);
		logger.info("Customer Id Is Entered");
		
		
		addacc.selectAccountType(type);
		logger.info("Account Type Is Entered");
		
		
		addacc.enterIntitialDeposite(initAmount);
		logger.info("Initial Deposite Is Entered");
		
		
		addacc.custsubmit();
		logger.info("Submit Button Is Clicked");
		
		
		logger.info("Sleep For 3000 ms");
		Thread.sleep(3000);
		
		String testCase = subTcNo + " _ " + title  + " _ ";
		logger.info("Sub Test Case Is : " + testCase);
		
		logger.info("validation started....");
		
		
		if (isAlertPresent() == true) {
			String alertText = driver.switchTo().alert().getText();

			System.out.println(alertText);
			if (alertText.equalsIgnoreCase("Please fill all fields")) {

				driver.switchTo().alert().accept();
				Thread.sleep(3000);
				logger.info(testCase +  "Test Passed --- Field blank passed");
				driver.switchTo().defaultContent();
				addacc.clickManager();
				Thread.sleep(3000);
				MyScreenRecorder.stopRecording();
				Assert.assertTrue(true);
				
				
			}else if(alertText.equalsIgnoreCase("Customer does not Exist!!")) {

				driver.switchTo().alert().accept();
				logger.info(testCase + "Passed---- test passed with invalid customer id");
				driver.switchTo().defaultContent();
				addacc.clickManager();
				Thread.sleep(3000);
				MyScreenRecorder.stopRecording();
				Assert.assertTrue(true);
				

			}else{

				driver.switchTo().alert().accept();
				logger.info(testCase + "Passed---- test passed with invalid Initial Amount");
				driver.switchTo().defaultContent();
				addacc.clickManager();
				Thread.sleep(3000);
				MyScreenRecorder.stopRecording();
				Assert.assertTrue(true);
				

			}

		} else {
			logger.info(testCase + "Failed -- something went wrong");
			captureScreen(driver, testCase +  " Add new Account");
			logger.info("Screen Shot Is Taken");			
			driver.switchTo().defaultContent();
			addacc.clickManager();
			Thread.sleep(3000);
			MyScreenRecorder.stopRecording();
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

	@DataProvider(name = "addAccountData")
	String[][] getData() throws IOException {
		String path = System.getProperty("user.dir") + "/src/test/java/com/inetbanking/testData/LoginData.xlsx";

		int rownum = XLUtils.getRowCount(path, "AddAccount");
		int colcount = XLUtils.getCellCount(path, "AddAccount", 1);

		System.out.println(rownum);

		String addAccountData[][] = new String[rownum][colcount];

		for (int i = 1; i <= rownum; i++) {
			for (int j = 0; j < colcount; j++) {
				addAccountData[i - 1][j] = XLUtils.getCellData(path, "AddAccount", i, j);// 1 0
				System.out.print(XLUtils.getCellData(path, "AddAccount", i, j));
				
			}

		}

		System.out.print(addAccountData);
		return addAccountData;
	}
	
}
