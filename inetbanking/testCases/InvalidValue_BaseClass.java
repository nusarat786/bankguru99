package com.inetbanking.testCases;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import com.inetbanking.pageObjects.EditCustomerPage;
import com.inetbanking.pageObjects.LoginPage;
import com.inetbanking.utilities.MyScreenRecorder;
import com.inetbanking.utilities.ReadConfig;


public class InvalidValue_BaseClass {

ReadConfig readconfig=new ReadConfig();
	
	public String baseURL=readconfig.getApplicationURL();
	public String username=readconfig.getUsername();
	public String password=readconfig.getPassword();
	
	List<String> chromOptionList = Arrays.asList("no-sandbox", "ignore-certificate-errors", "disable-extensions", "disable-infobars");
	ChromeOptions chromeOptions = new ChromeOptions();
	public static WebDriver driver;
	
	public static Logger logger;
	
	@Parameters("browser")
	@BeforeClass
	public void setup(String br) throws InterruptedException
	{	
		
		logger = Logger.getLogger("ebanking");
		PropertyConfigurator.configure("Log4j.properties");
		
		if(br.equals("chrome"))
		{
			
			System.setProperty("webdriver.chrome.driver",readconfig.getChromePath());
			chromeOptions.addArguments("--remote-allow-origins=*");
//			chromeOptions.addExtensions(new File(System.getProperty("user.dir") + "/gighmmpiobklfepjocnamgkkbiglidom-5.4.1-Crx4Chrome.com.crx"));
//			chromeOptions.addExtensions(new File(System.getProperty("user.dir") + "/cfhdojbkjhnklbpkdaibdccddilifddb.crx"));
			//chromeOptions.addExtensions(new File("C:\\Users\\Nusarat.Haveliwala\\Desktop\\New folder (2)\\inetbankingV1\\addblock.crx"));
	
			//String dirt = System.getProperty("user.dir");
			chromeOptions.addArguments("load-extension="+"C:\\Users\\Nusarat.Haveliwala\\Desktop\\New folder (2)\\inetbankingV1\\addblock.crx");
			
			DesiredCapabilities capabilities = new DesiredCapabilities();
			capabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
			
			driver=new ChromeDriver();
			
		}
		else if(br.equals("firefox"))
		{
			System.setProperty("webdriver.gecko.driver",readconfig.getFirefoxPath());
			driver = new FirefoxDriver();
		}
		else if(br.equals("ie"))
		{
			System.setProperty("webdriver.ie.driver",readconfig.getIEPath());
			driver = new InternetExplorerDriver();
		}
		
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		driver.manage().window().maximize();

		driver.get(baseURL);
		


		
		
		LoginPage lp = new LoginPage(driver);

		logger.info("Trying to login");

		lp.setUserName(username);
		logger.info("User name is provided");

		lp.setPassword(password);
		logger.info("Passsword is provided");

		lp.clickSubmit();
		logger.info("Submit Button is clicked");

		logger.info("Stop Execution for 3000s");
		Thread.sleep(3000);

		EditCustomerPage editcust = new EditCustomerPage(driver);

		logger.info("Handling Add Loading");
	 
		editcust.clickEditCustomer(); 
		
		logger.info("Stop Execution for 3000s");
		Thread.sleep(3000);
		
		
		logger.info("Edit customer button is clicked");

		logger.info("Stop Execution for 3000s");
		Thread.sleep(3000);
		
		
		
		
		driver.navigate().refresh(); logger.info("Page Is Refreshed to handle edit");
		

		logger.info("Stop Execution for 3000s");
		Thread.sleep(3000);

		driver.switchTo().defaultContent();
		logger.info("focus come to refresehd page");

		EditCustomerPage editcust0 = new EditCustomerPage(driver);

				
			editcust0.clickManager();
			logger.info("Manager Link Is Clicked");

			logger.info("Stop Execution for 3000s");
			Thread.sleep(3000);
		
	}
	
	@AfterClass
	public void tearDown() throws Exception
	{
		
		driver.quit();
	}
	
	public void captureScreen(WebDriver driver, String tname) throws IOException {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File target = new File(System.getProperty("user.dir") + "/Screenshots/" + tname + ".png");
		FileUtils.copyFile(source, target);
		System.out.println("Screenshot taken");
	}
	
	public String randomestring()
	{
		String generatedstring=RandomStringUtils.randomAlphabetic(8);
		return(generatedstring);
	}
	
	public static String randomeNum() {
		String generatedString2 = RandomStringUtils.randomNumeric(4);
		return (generatedString2);
	}
}

