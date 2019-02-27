//Initialization:
//	1. WebDriver
//	2. Properties
//	3. Logs - lof4j jar in POM file, Application logs for manual logs, Selenium log file for automated logs for our requests and response, 
//	log4j.properties, logger class, 
//  4. ExtentReports
//  5. DB
//  6. Excel
//  7. ReportNG
//  8. Jenkins

package com.w2a.base;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.w2a.utilities.ExcelReader;



public class TestBase {
	
	public static WebDriver driver;
	public static Properties config = new Properties();
	public static Properties or = new Properties();
	public static FileInputStream fis1;
	public static FileInputStream fis2;
	public static Logger log= Logger.getLogger("devpinoyLogger");
	public static ExcelReader excel=new ExcelReader(System.getProperty("user.dir") + "/src/test/resources/excel/AddCustomerData.xls");
	public static WebDriverWait wait;
	
	@BeforeSuite
	public void SetUp() throws IOException
	{
		if(driver==null)
		{
			
			try {
				fis1 = new FileInputStream (System.getProperty("user.dir") + "/src/test/resources/properties/Config.properties");
			    } 
			catch (FileNotFoundException e) 
			{
				e.printStackTrace();
			}
			try {
				config.load(fis1);
				log.debug("Config file loaded");
			    } 
			catch (FileNotFoundException e) 
			{
				
				e.printStackTrace();
			}
			
			try {
				fis2 = new FileInputStream (System.getProperty("user.dir") + "/src/test/resources/properties/OR.properties");
			    } 
			catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				or.load(fis2);
				log.debug("OR file loaded");
			    } 
			catch (FileNotFoundException e) 
			{
				
				e.printStackTrace();
			}
			
		}
		if (config.getProperty("browser").equals("chrome"))
		{
			System.setProperty("webdriver.chrome.driver", "/Users/sahiluppal/Downloads/chromedriver");
			driver= new ChromeDriver();
			log.debug("Chrome launched");
		}
		else if (config.getProperty("browser").equals("firefox"))
		{
			System.setProperty("webdriver.gecko.driver", "/Users/sahiluppal/Downloads/geckodriver");
			driver= new FirefoxDriver();
		}
		else if (config.getProperty("browser").equals("safari"))
		{
			System.setProperty("webdriver.safari.driver", "/Users/sahiluppal/Downloads/SafariDriver.safariextz");
			driver= new SafariDriver();
		}
		driver.get(config.getProperty("testsiteurl"));
		//driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Integer.parseInt(config.getProperty("implicitwait")), TimeUnit.SECONDS);
		log.debug("Navigated to Test site"+ config.getProperty("testsiteurl"));
		wait=new WebDriverWait(driver,5);
	}
	
	public boolean isElementPresent(By by) {
	try {
		
	       driver.findElement(by);
	       return true;
	}
	
	catch(NoSuchElementException e)
	{
		return false;
	}
}
	@AfterSuite
    public void TearDown()
    {
        if (driver!=null)
        {	
		driver.quit();
        }
        log.debug ("Test Execution completed!!!");
    }
}
