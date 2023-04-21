package com.inetbanking.testCases;

import java.io.IOException;

import org.openqa.selenium.NoAlertPresentException;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.inetbanking.pageObjects.CustomizedStatementPage;
import com.inetbanking.utilities.XLUtils;

public class TC_0026_Customized_Statement_Invalid_Value_Test extends InvalidValue_BaseClass {

	@Test(dataProvider = "CutomizedSt")
	public void TC_0026_Customized_Statement_Invalid_Value (
			String subTcNo, 
			String title, 
			String AccNo, 
			String fromDate,
			String toDate, 
			String minimumAmount,
			String transNo
			
			) throws InterruptedException, IOException {
		
		logger.info("TC_0026_Customized_Statement_Invalid_Value Is Statrting");
		
		
		
		
		
		
		String[] toDatePart = toDate.split("/");

		String tomonth = toDatePart[0];
		if (tomonth.length() == 1) {
		    tomonth = "0" + tomonth;
		}

		String today = toDatePart[1];
		if (today.length() == 1) {
		    today = "0" + today;
		}

		String toyear = toDatePart[2] + toDatePart[3];

		String formattedDate = tomonth + " " + today + " " + toyear;
		System.out.println(formattedDate);
		
		logger.info("to date value converted to mm dd yyyy");
		
		 String[] fParts = fromDate.split("/");
		    
		    String fMonth = fParts[0];
		    if (fMonth.length() == 1) {
		        fMonth = "0" + fMonth;
		    }
		    
		    String fDay = fParts[1];
		    if (fDay.length() == 1) {
		        fDay = "0" + fDay;
		    }
		    
		    String fYear = fParts[2] + fParts[3];
		    
		   
		logger.info("from date value converted to mm dd yyyy");
		    
		CustomizedStatementPage custst = new CustomizedStatementPage(driver);
		logger.info("Customized Statement Page Object Is Created ");
		
		
		boolean res=driver.getPageSource().contains("Customized");
		
		

		
		if(driver.getTitle().equalsIgnoreCase("Guru99 Bank Customised Statement Page"))
		{
		 driver.navigate().back();
		 Thread.sleep(3000);
			custst.clickManager();
			Thread.sleep(3000);
		}
		
		
		
		custst.clickCustomizedStatement();
		logger.info("Customized Statement Link Is Clicked");
		
		
		custst.enterAccNo(AccNo);
		logger.info("Customized Statement Link Is Clicked");
		
		custst.enterFromDate(tomonth,today,toyear);
		logger.info("Enter From Date");
		
		custst.enterToDate(fMonth,fDay,fYear);
		logger.info("Enter To Date");
		
		custst.enterMinimumAmount(minimumAmount);
		logger.info("Enter Minimum Amount");
		
		custst.enterNumberOfTransaction(transNo);
		logger.info("Enter No Of Transaction ");
		
		Thread.sleep(3000);
		logger.info("Sleep For 3000");
		
		
		custst.submitAccNo();
		logger.info("Submit Acc No.");
		
		
		Thread.sleep(3000);
		logger.info("Sleep For 3000");
		
		
		String testCase = subTcNo + " _ " + title  + " _ ";
		logger.info("Sub Test Case Is : " + testCase);
		
		logger.info("Validating");
		if (isAlertPresent() == true) {
				
					
					String alerText2 = driver.switchTo().alert().getText();
					logger.info("Getting Alert Text From Alert");
					
					if (alerText2.equalsIgnoreCase("Account does not exist")) {

						
						driver.switchTo().alert().accept();
						logger.warn(testCase + "Test Passed --- Account does not exist confirmed");
						driver.switchTo().defaultContent();
						custst.clickManager();
						Thread.sleep(3000);
						Assert.assertTrue(true);

					}else if (alerText2.equalsIgnoreCase("Please fill all fields")) {

						driver.switchTo().alert().accept();
						logger.warn(testCase + "Test Passed --- Invalid/ blank value confirmed");
						driver.switchTo().defaultContent();
						custst.clickManager();
						Thread.sleep(3000);
						Assert.assertTrue(true);
						
					}else {
						//System.out.println(alerText2);
						Thread.sleep(3000);
						driver.switchTo().alert().accept();
						logger.info(testCase + "Failed -- value accepted in incorrect fileds");
						captureScreen(driver, testCase + "Customized Statement Invalid Value --alert text mismatch");
						driver.switchTo().defaultContent();
						custst.clickManager();
						Thread.sleep(3000);
						Assert.assertTrue(false);
					}
		} else {
			logger.info(testCase + "Failed -- Customized Statement takin invalid input");
			String temp = testCase + " Invalid Value Accepted";
			captureScreen(driver,temp );
			Thread.sleep(4000);
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

	@DataProvider(name = "CutomizedSt")
	String[][] getData() throws IOException {
		String path = System.getProperty("user.dir") + "/src/test/java/com/inetbanking/testData/LoginData.xlsx";

		int rownum = XLUtils.getRowCount(path, "Custumized");
		int colcount = XLUtils.getCellCount(path, "Custumized", 1);

		System.out.println(rownum);

		String deleteCustomerData[][] = new String[rownum][colcount];

		for (int i = 1; i <= rownum; i++) {
			for (int j = 0; j < colcount; j++) {
				deleteCustomerData[i - 1][j] = XLUtils.getCellData(path, "Custumized", i, j);// 1 0
				System.out.print(XLUtils.getCellData(path, "Custumized", i, j));
				
			}
		}

		System.out.print(deleteCustomerData);
		return deleteCustomerData;
	}
	
}
