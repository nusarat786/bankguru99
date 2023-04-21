package com.inetbanking.testCases;

import java.io.IOException;

import org.openqa.selenium.NoAlertPresentException;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.inetbanking.pageObjects.AccountWithdrawalPage;
import com.inetbanking.pageObjects.FundTransferPage;
import com.inetbanking.utilities.XLUtils;

public class TC_0020_Fund_Transfer_Invalid_Value_Test extends InvalidValue_BaseClass {


	@Test(dataProvider = "FundTransferData")
	public void TC_0020_Fund_Transfer_Invalid_Value(
			String subTcNo, 
			String title,
			String payeraccNo, 
			String payeeaccNo,
			String amount, 
			String description
			
			) throws InterruptedException, IOException {

		logger.info("TC0020 Fund Transfer Invalid Value Test Case ");
		
		
		
		
		
FundTransferPage fundtransfer = new FundTransferPage(driver);
		
		fundtransfer.clickFundTransfer();
		logger.info("Fund Transfer  Link Is Clicked");
		
		logger.info("providing acc....");
		fundtransfer.enterPayerAccountNo(payeraccNo);
		
		fundtransfer.enterPayeeAccountNo(payeeaccNo);
		
		logger.info("providing amount....");
		fundtransfer.enterAmmount(amount);
		
		logger.info("providing description....");
		fundtransfer.enterDescription(description);
		

		
	
		if(payeraccNo.equals("empty") ) {
			logger.info("clearing payer account ");
			fundtransfer.PayerAccount.clear();
		}
		
		if(payeeaccNo.equals("empty") ) {
			logger.info("clearing payee account ");
			fundtransfer.PayeeAccount.clear();
		}
		
		if(amount.equals("empty") ) {
			logger.info("clearing amount ");
			fundtransfer.amount.clear();
		}
		
		if(description.equals("empty") ) {
			logger.info("clearing description ");
			fundtransfer.description.clear();
		}
		
		
		logger.info("sleep for 3000 second");
		Thread.sleep(3000);
		
		
		fundtransfer.accsubmit();
		logger.info("form is submitted ");
		
		logger.info("sleep for 3000 second");
		Thread.sleep(3000);
		
		
		
		String testCase = subTcNo + " _ " + title  + " _ ";
		logger.info("Sub Test Case Is : " + testCase);
		
		logger.info("Testing....");
		
		if (isAlertPresent()) {

			String alertText = driver.switchTo().alert().getText();

			if (alertText.equalsIgnoreCase("Transfer Failed. Account Balance low!!")) {

				// String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());
				
				// captureScreen(driver,"edit Customer01" + timeStamp);
				driver.switchTo().alert().accept();
				
			
				logger.info(testCase +  "test case passed.... amount is greater than balance");
				Thread.sleep(3000);
				
		
				driver.switchTo().defaultContent();
		
				Thread.sleep(3000);
				fundtransfer.clickManager();
				Assert.assertTrue(true);
				
				
			} else if (alertText.contains("does not exist!!!")) {
				
				driver.switchTo().alert().accept();
				logger.info(testCase + "test case passed.... payee or payer account not valid");
				
				Thread.sleep(3000);
				
				
				driver.switchTo().defaultContent();
				
				Thread.sleep(3000);
				fundtransfer.clickManager();
				Assert.assertTrue(true);
	
			} else if (alertText.equalsIgnoreCase("Please fill all fields")) {
				
				driver.switchTo().alert().accept();
				logger.info(testCase + "test case passed.... blank or invalid filed");
				
				Thread.sleep(3000);
				
				
				driver.switchTo().defaultContent();
				
				Thread.sleep(3000);
				fundtransfer.clickManager();
				Assert.assertTrue(true);
	
			}else if (alertText.equalsIgnoreCase("Payers account No and Payees account No Must Not be Same!!!")) {
				
				driver.switchTo().alert().accept();
				logger.info(testCase + "test case passed.... blank or invalid filed");
				
				Thread.sleep(3000);
				
				
				driver.switchTo().defaultContent();
				
				Thread.sleep(3000);
				fundtransfer.clickManager();
				Assert.assertTrue(true);
	
			}else {
				
				driver.switchTo().alert().accept();
				logger.info(testCase + "test case failed something unexpected ");
				captureScreen(driver, testCase + "Fund Transfer Alert Mismatch ");
				driver.switchTo().defaultContent();
			
				Thread.sleep(3000);
				System.out.println(alertText);
				
				fundtransfer.clickManager();
				Assert.assertTrue(false);
							
			}

		} else {
			
			logger.info(testCase + "test case failed.... Invalid Value Passed");
			captureScreen(driver, testCase + "Fund Transfer Error");
			logger.info("ScreenShot Taken");
			driver.switchTo().defaultContent();
			Thread.sleep(3000);
			fundtransfer.clickManager();
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
	
	@DataProvider(name = "FundTransferData")
	String[][] getData() throws IOException {
		String path = System.getProperty("user.dir") + "/src/test/java/com/inetbanking/testData/LoginData.xlsx";

		int rownum = XLUtils.getRowCount(path, "FundTransfer");
		int colcount = XLUtils.getCellCount(path, "FundTransfer", 1);

		System.out.println(rownum);
		String FundTransferData[][] = new String[rownum][colcount];

		for (int i = 1; i <= rownum; i++) {
			for (int j = 0; j < colcount; j++) {
				FundTransferData[i - 1][j] = XLUtils.getCellData(path, "FundTransfer", i, j);// 1 0
				System.out.print(XLUtils.getCellData(path, "FundTransfer", i, j));

			}

		}

		System.out.print(FundTransferData);
		return FundTransferData;
	}
	
	
}
