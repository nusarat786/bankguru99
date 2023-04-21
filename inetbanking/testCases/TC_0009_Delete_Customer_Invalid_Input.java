package com.inetbanking.testCases;

import java.io.IOException;

import org.openqa.selenium.NoAlertPresentException;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.inetbanking.pageObjects.DeleteCustomerPage;
import com.inetbanking.pageObjects.LoginPage;
import com.inetbanking.utilities.XLUtils;

public class TC_0009_Delete_Customer_Invalid_Input extends InvalidValue_BaseClass {

	@Test(dataProvider = "DeleteCustomerData")
	public void deleteCustomerScearch(
			String subTcNo,
			String title,
			String cusId) throws InterruptedException, IOException {

		logger.info("TC009 Deelete Customer Invalid Value Test");


		DeleteCustomerPage deletecust = new DeleteCustomerPage(driver);
		logger.info("Delete Customer Page Object Is Created");

		deletecust.clickDeleteCustomer();
		logger.info("Delete Customer Is Clicked");

		logger.info("Stop Execution for 3000s");
		Thread.sleep(3000);

		logger.info("providing customer id....");
		deletecust.enterCustId(cusId);

		
		deletecust.submitCustId();
		logger.info("Cus Id Is Provided");
		
		String testCase = subTcNo + " _ " + title  + " _ ";
		logger.info("Sub Test Case Is : " + testCase);
		
		logger.info("Testing....");

		
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
					
					System.out.println(cusId + ": " + alerText2);
					if (alerText2.equalsIgnoreCase("Customer does not Exist!!")) {

						driver.switchTo().alert().accept();
						logger.warn(testCase + "Test Passed --- Customer does not exist confirmed");
						driver.switchTo().defaultContent();
						deletecust.clickManager();
						Thread.sleep(3000);
						Assert.assertTrue(true);
						

					} else {
						//System.out.println(alerText2);
						Thread.sleep(3000);
						driver.switchTo().alert().accept();
						logger.info(testCase + "Failed -- value accepted in incorrect fileds");
						captureScreen(driver, testCase +  "Delete Customer --alert text mismatch");
						driver.switchTo().defaultContent();
						deletecust.clickManager();
						Thread.sleep(3000);
						Assert.assertTrue(false);
						

					}

				} else {
					
					logger.info(testCase + "Failed -- something went wrong");
					captureScreen(driver, testCase + "Delete Customer--went wrong after alert ");
					driver.switchTo().defaultContent();
					deletecust.clickManager();
					Thread.sleep(3000);
					Assert.assertTrue(false);
					

				}
			}else {

				driver.switchTo().alert().accept();
				logger.info(testCase + "Passed---- test passed with failed args");
				driver.switchTo().defaultContent();
				deletecust.clickManager();
				Thread.sleep(3000);
				Assert.assertTrue(true);
				

			}

		} else {
			logger.info(testCase + "Failed -- something went wrong");
			captureScreen(driver, testCase + "Delete Customer--went wrong in alert ");
			driver.switchTo().defaultContent();
			deletecust.clickManager();
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

	@DataProvider(name = "DeleteCustomerData")
	String[][] getData() throws IOException {
		String path = System.getProperty("user.dir") + "/src/test/java/com/inetbanking/testData/LoginData.xlsx";

		int rownum = XLUtils.getRowCount(path, "DeleteCustomer");
		int colcount = XLUtils.getCellCount(path, "DeleteCustomer", 1);

		System.out.println(rownum);

		String deleteCustomerData[][] = new String[rownum][colcount];

		for (int i = 1; i <= rownum; i++) {
			for (int j = 0; j < colcount; j++) {
				deleteCustomerData[i - 1][j] = XLUtils.getCellData(path, "DeleteCustomer", i, j);// 1 0
				System.out.print(XLUtils.getCellData(path, "DeleteCustomer", i, j));

			}

		}

		System.out.print(deleteCustomerData);
		return deleteCustomerData;
	}
}
