package com.inetbanking.testCases;

import java.io.IOException;

import org.openqa.selenium.NoAlertPresentException;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.inetbanking.pageObjects.AccountWithdrawalPage;
import com.inetbanking.utilities.XLUtils;

public class TC_0018_Deposite_Invalid_Value_Test_Case extends InvalidValue_BaseClass {

	
	@Test(dataProvider = "DepositeMoneyData")
	public void TC_0018_Deposite_Money_Invalid_Value(
			String subTcNo, 
			String title,
			String accNo, 
			String amount, 
			String description
			
			) throws InterruptedException, IOException {

		logger.info("TC0018 Deposite_Money Inavlid Value");
	
		AccountWithdrawalPage editacc = new AccountWithdrawalPage(driver);
		logger.info("Deposit Page Is Created");
		
		editacc.clickWidrawal();
		logger.info("Deposite  Link Is Clicked");
		
		
		
		System.out.println(accNo);
		
		logger.info("providing Acc No. ....");
		editacc.enterAccNo(accNo);
		
		logger.info("providing amount....");
		editacc.enterAmmount(amount);
		
		logger.info("providing description....");
		editacc.enterDescription(description);
		
		
		
	
		if(accNo.equals("empty") ) {
			logger.info("clearing account ");
			editacc.AccountNo.clear();
		}
		
		if(amount.equals("empty") ) {
			logger.info("clearing amount");
			editacc.amount.clear();
		}
		
		if(description.equals("empty") ) {
			logger.info("clearing description ");
			editacc.description.clear();
		}
		
		logger.info("sleep for 3000 second");
		Thread.sleep(3000);
		
		editacc.accsubmit();
		logger.info("form is submitted ");

		
		logger.info("sleep for 3000 second");
		Thread.sleep(3000);
		
		String testCase = subTcNo + " _ " + title  + " _ ";
		logger.info("Sub Test Case Is : " + testCase);
		
		logger.info("validation started....");
		
		
		if (isAlertPresent()) {

			String alertText = driver.switchTo().alert().getText();

			if (alertText.equalsIgnoreCase("Transaction Failed. Account Balance Low!!!")) {

				// String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());
				
				// captureScreen(driver,"edit Customer01" + timeStamp);
				driver.switchTo().alert().accept();
				
			
				logger.info(testCase +  "test case passed.... amount is greater than balance");
				Thread.sleep(3000);
				
		
				driver.switchTo().defaultContent();
				
				Thread.sleep(3000);
				editacc.clickManager();
				Assert.assertTrue(true);

			} else if (alertText.equalsIgnoreCase("Please fill all fields")) {
				
				driver.switchTo().alert().accept();
				logger.info(testCase +  "test case passed.... blank or invalid filed");
				
				Thread.sleep(3000);
				
				
				driver.switchTo().defaultContent();
				
				Thread.sleep(3000);
				editacc.clickManager();
				Assert.assertTrue(true);
	
			} else {
				
				driver.switchTo().alert().accept();
				logger.info(testCase +  "test case passed.... account not valid ");
				
				driver.switchTo().defaultContent();
				
				Thread.sleep(3000);
				System.out.println(alertText);
				
				editacc.clickManager();
				Assert.assertTrue(true);
							
			}

		} else {
			
			logger.info(testCase +  "test case failed.... something went wrong");
			captureScreen(driver,testCase +  "Deposite Money");
			driver.switchTo().defaultContent();
			driver.navigate().back();
			Assert.assertTrue(false);
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
	
	@DataProvider(name = "DepositeMoneyData")
	String[][] getData() throws IOException {
		String path = System.getProperty("user.dir") + "/src/test/java/com/inetbanking/testData/LoginData.xlsx";

		int rownum = XLUtils.getRowCount(path, "DepositeMoney");
		int colcount = XLUtils.getCellCount(path, "DepositeMoney", 1);

		System.out.println(rownum);
		String DepositeMoneyData[][] = new String[rownum][colcount];

		for (int i = 1; i <= rownum; i++) {
			for (int j = 0; j < colcount; j++) {
				
				DepositeMoneyData[i - 1][j] = XLUtils.getCellData(path, "DepositeMoney", i, j);// 1 0
				System.out.print(XLUtils.getCellData(path, "DepositeMoney", i, j));

			}

		}

		System.out.print(DepositeMoneyData);
		return DepositeMoneyData;
	}
}
