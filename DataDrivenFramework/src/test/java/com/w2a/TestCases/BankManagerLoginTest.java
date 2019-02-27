package com.w2a.TestCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.openqa.selenium.By;
import com.w2a.base.TestBase;


public class BankManagerLoginTest extends TestBase{
	
	@Test
	
	public void BankManagerLogin() throws InterruptedException
	{
		driver.findElement (By.cssSelector(or.getProperty("bmlBTN"))).click();
		Thread.sleep(3000);
		
		Assert.assertTrue(isElementPresent(By.cssSelector(or.getProperty("addCustBtn"))),"Login Not Successful");
		log.debug ("Login successfully executed");
		
	}

}
