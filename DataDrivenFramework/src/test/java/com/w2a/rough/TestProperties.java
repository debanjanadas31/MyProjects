package com.w2a.rough;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class TestProperties {

	public static void main(String[] args) throws IOException {
		
		System.out.println (System.getProperty("user.dir"));
		Properties config = new Properties();
		Properties or = new Properties();
		FileInputStream fis1 = new FileInputStream (System.getProperty("user.dir") + "/src/test/resources/properties/Config.properties");
		FileInputStream fis2 = new FileInputStream (System.getProperty("user.dir") + "/src/test/resources/properties/OR.properties");
		config.load(fis1);
		or.load(fis2);
		
		//driver.findElement(By.cssSelector(OR.getProperty("bmlBTN").click());
		
		System.out.println ("The browser is:" + config.getProperty("browser"));
		System.out.println (or.getProperty("bmlBTN"));

	}

}
