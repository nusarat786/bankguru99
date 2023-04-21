package com.inetbanking.testCases;

import java.io.IOException;

import org.openqa.selenium.NoAlertPresentException;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.inetbanking.pageObjects.DeleteCustomerPage;
import com.inetbanking.pageObjects.EditAccountPage;
import com.inetbanking.utilities.XLUtils;

public class TC_0014_Delete_Account_InavalidValue_Test extends InvalidValue_BaseClass {

	@Test(dataProvider = "DeleteAccountData")
	public void deleteCustomerScearch(
			String subTcNo, 
			String title,
			String cusId
			
			) throws InterruptedException, IOException {

		logger.info("TC0014 Deelete Account Inavlid Value Test");


		EditAccountPage editacc = new EditAccountPage(driver);
		logger.info("Delete Account Page Object Is Created");
		
		editacc.clickDeleteAccount();
		logger.info("Delete Account Is Clicked");
		
		logger.info("providing Acc No. ....");
		editacc.enterAccNo(cusId);
		
	
		
		if(cusId.equalsIgnoreCase("empty")) {
			logger.info("Blank Value Test");
			editacc.AccountNo.clear();
		}
		
		editacc.submitAccNo();
		logger.info("Acc No. Is Provided");
		
		logger.info("Sleep for 3000 ms");
		Thread.sleep(3000);
		
		String testCase = subTcNo + " _ " + title  + " _ ";
		logger.info("Sub Test Case Is : " + testCase);
		
		logger.info("Testing....");
		

		if (isAlertPresent() == true) {
			String alertText = driver.switchTo().alert().getText();

			System.out.println(alertText);
			if (alertText.equalsIgnoreCase("Do you really want to delete this Account?")) {

				// accept delete confirm
				driver.switchTo().alert().accept();// close alert
				Thread.sleep(3000);

				if (isAlertPresent() == true) {
					
					// accept delete confirm
					
					String alerText2 = driver.switchTo().alert().getText();
					
					System.out.println(cusId + ": " + alerText2);
					if (alerText2.equalsIgnoreCase("Account does not exist")) {

						driver.switchTo().alert().accept();
						logger.info(testCase +  "Test Passed --- Customer does not exist confirmed");
						driver.switchTo().defaultContent();
						Assert.assertTrue(true);
						editacc.clickManager();
						Thread.sleep(3000);

					} else {
						//System.out.println(alerText2);
						Thread.sleep(3000);
						driver.switchTo().alert().accept();
						logger.info(testCase +  "Failed -- value accepted in incorrect fileds");
						captureScreen(driver, "Delete Account --alert text mismatch");
						Assert.assertTrue(true);
						driver.switchTo().defaultContent();
						editacc.clickManager();
						Thread.sleep(3000);

					}

				} else {
					
					logger.info( testCase +  "Failed -- something went wrong");
					captureScreen(driver, testCase + "Delete Customer--went wrong after alert ");
					driver.switchTo().defaultContent();
					editacc.clickManager();
					Thread.sleep(3000);
					Assert.assertTrue(false);

				}
			}else if (alertText.equalsIgnoreCase("Please fill all fields")) {
				logger.info("Test");
				driver.switchTo().alert().accept();
				logger.warn(testCase +  "Test Passed --- Invalid Argument Is confirmed");
				driver.switchTo().defaultContent();
				Assert.assertTrue(true);
				editacc.clickManager();
				Thread.sleep(3000);

			}else {

				driver.switchTo().alert().accept();
				logger.info(testCase +  "Passed---- test passed with failed args");
				driver.switchTo().defaultContent();
				editacc.clickManager();
				Thread.sleep(3000);
				Assert.assertTrue(true);
				

			}

		} else {
			logger.info("Failed -- something went wrong");
			captureScreen(driver,testCase + "Delete Customer--went wrong in alert ");
			driver.switchTo().defaultContent();
			editacc.clickManager();
			Thread.sleep(3000);
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

	@DataProvider(name = "DeleteAccountData")
	String[][] getData() throws IOException {
		String path = System.getProperty("user.dir") + "/src/test/java/com/inetbanking/testData/LoginData.xlsx";

		int rownum = XLUtils.getRowCount(path, "DeleteAccount");
		int colcount = XLUtils.getCellCount(path, "DeleteAccount", 1);

		System.out.println(rownum);

		String DeleteAccountData[][] = new String[rownum][colcount];

		for (int i = 1; i <= rownum; i++) {
			for (int j = 0; j < colcount; j++) {
				DeleteAccountData[i - 1][j] = XLUtils.getCellData(path, "DeleteAccount", i, j);// 1 0
				System.out.print(XLUtils.getCellData(path, "DeleteAccount", i, j));
			}
		}

		System.out.print(DeleteAccountData);
		return DeleteAccountData;
	}
}
